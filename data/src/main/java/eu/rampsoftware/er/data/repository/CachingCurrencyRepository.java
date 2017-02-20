package eu.rampsoftware.er.data.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import eu.rampsoftware.er.data.CurrencyData;
import eu.rampsoftware.er.data.CurrencyRepository;
import eu.rampsoftware.er.data.SingleValue;
import eu.rampsoftware.er.data.datasource.CurrencyDataSource;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class CachingCurrencyRepository implements CurrencyRepository {
    private CurrencyDataSource mLocalSource;
    private CurrencyDataSource mRemoteSource;

    public CachingCurrencyRepository(final CurrencyDataSource localSource,
                                     final CurrencyDataSource remoteSource) {
        mLocalSource = localSource;
        mRemoteSource = remoteSource;
    }

    @Override
    public Observable<CurrencyData> getCurrencies(final Date date) {
        if (mLocalSource.containsCurrencyValues(date)) {
            return mLocalSource.getCurrencies(date);
        }
        final Observable<CurrencyData> remoteCurrencies = mRemoteSource.getCurrencies(date);
        remoteCurrencies.doOnNext(currencyData -> mLocalSource.storeCurrencies(currencyData));
        return remoteCurrencies;
    }

    @Override
    public Observable<SingleValue> getSeries(final Date from, final Date to, final String currencyCode) {
        return Observable.create(emitter -> {
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(from);

            List<Observable<CurrencyData>> cloudRequestObservables = new ArrayList<>();
            while (true) {
                final Date date = calendar.getTime();
                if (!mLocalSource.containsCurrencyValue(date, currencyCode)) {
                    cloudRequestObservables.add(
                            mRemoteSource.getCurrencies(date)
                                    .doOnNext(currencyData -> mLocalSource.storeCurrencies(currencyData)));
                }
                calendar.add(Calendar.DAY_OF_YEAR, 1);
                if (calendar.getTimeInMillis() >= to.getTime()) {
                    break;
                }
            }
            Observable.merge(cloudRequestObservables)
                    .subscribeOn(Schedulers.io())
                    .doOnComplete(() -> {
                        mLocalSource.getCurrencyValues(from, to, currencyCode)
                                .doOnNext(emitter::onNext)
                                .doOnComplete(emitter::onComplete)
                                .subscribe();
                    }).subscribe();
        });
    }
}
