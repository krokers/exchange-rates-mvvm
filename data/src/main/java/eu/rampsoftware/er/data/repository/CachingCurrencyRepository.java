package eu.rampsoftware.er.data.repository;

import java.util.Date;

import eu.rampsoftware.er.data.CurrencyData;
import eu.rampsoftware.er.data.CurrencyDataSource;
import eu.rampsoftware.er.data.CurrencyRepository;

import io.reactivex.Observable;

public class CachingCurrencyRepository implements CurrencyRepository {
    private CurrencyDataSource mLocalSource;
    private CurrencyDataSource mRemoteSource;

    public CachingCurrencyRepository(final CurrencyDataSource localSource, final CurrencyDataSource remoteSource) {
        mLocalSource = localSource;
        mRemoteSource = remoteSource;
    }

    public Observable<CurrencyData> getCurrencies(final Date date) {
        return mRemoteSource.getCurrencies(date);
    }
}
