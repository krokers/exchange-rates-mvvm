package eu.rampsoftware.er.viewmodel.currencies;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.fernandocejas.arrow.optional.Optional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import eu.rampsoftware.er.BR;
import eu.rampsoftware.er.data.CurrencyData;
import eu.rampsoftware.er.domain.usecases.GetCurrenciesRatesDate;
import eu.rampsoftware.er.domain.usecases.GetCurrenciesUseCase;
import eu.rampsoftware.er.viewmodel.BaseViewModel;
import io.reactivex.observers.DisposableObserver;

public class CurrencyListViewModel extends BaseObservable implements BaseViewModel {

    private final GetCurrenciesUseCase mCurrenciesUseCase;
    private final GetCurrenciesRatesDate mGetCurrenciesRatesDate;
    private ObservableList<CurrencyItemViewModel> mCurrencies;
    private long DAY_MILLIS = 24 * 3600 * 1000;
    private boolean mIsProgressVisible;
    private Date mReadDate;
    private SimpleDateFormat mDateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public CurrencyListViewModel(GetCurrenciesUseCase currenciesUseCase, GetCurrenciesRatesDate getCurrenciesRatesDate) {
        mCurrencies = new ObservableArrayList<>();
        mCurrenciesUseCase = currenciesUseCase;
        mGetCurrenciesRatesDate = getCurrenciesRatesDate;
        setReadDate(new Date());
    }

    public void setReadDate(final Date readDate) {
        mReadDate = readDate;
        notifyPropertyChanged(BR.dateText);
    }

    @Bindable
    public boolean isProgressVisible() {
        return mIsProgressVisible;
    }

    public void setProgressVisible(final boolean progressVisible) {
        mIsProgressVisible = progressVisible;
        notifyPropertyChanged(BR.progressVisible);
    }

    @Bindable
    public ObservableList<CurrencyItemViewModel> getCurrencies() {
        return mCurrencies;
    }

    @Override
    public void onLoad() {
        setProgressVisible(true);
        mGetCurrenciesRatesDate.run(new DateObserver());
    }

    @Override
    public void dispose() {
        mGetCurrenciesRatesDate.dispose();
        mCurrenciesUseCase.dispose();
    }

    private void onDate(final Date date) {
        mCurrenciesUseCase.run(new CurrenciesDataObserver(), new GetCurrenciesUseCase.CurrenciesParam(date));
    }

    private void onCurrencyDataLoaded(final CurrencyData currencyData) {
        final Map<String, Double> currencies = currencyData.getCurrencies();
        final Set<String> currencyKeys = currencies.keySet();
        mCurrencies.clear();
        for (String currencyCode : currencyKeys) {
            final Double value = currencies.get(currencyCode);
            mCurrencies.add(new CurrencyItemViewModel(currencyCode, value));
        }

    }

    public int getItemBindingId() {
        return BR.model;
    }

    public void onNextDay() {
        setProgressVisible(true);
        setReadDate(new Date(mReadDate.getTime() + DAY_MILLIS));
        onDate(mReadDate);
    }

    public void onPreviousDay() {
        setProgressVisible(true);
        setReadDate(new Date(mReadDate.getTime() - DAY_MILLIS));
        onDate(mReadDate);
    }

    @Bindable
    public String getDateText() {
        return mDateFormatter.format(mReadDate);
    }

    public void performRefresh() {
        setProgressVisible(true);
        onDate(mReadDate);
    }

    private final class DateObserver extends DisposableObserver<Optional<Date>> {

        @Override
        public void onNext(final Optional<Date> date) {
            mReadDate = date.or(mReadDate);
            onDate(mReadDate);
        }

        @Override
        public void onError(final Throwable e) {
            setProgressVisible(false);
        }

        @Override
        public void onComplete() {
        }
    }

    private final class CurrenciesDataObserver extends DisposableObserver<CurrencyData> {
        @Override
        public void onNext(final CurrencyData currencyData) {
            onCurrencyDataLoaded(currencyData);
            setProgressVisible(false);
        }

        @Override
        public void onError(final Throwable e) {
            setProgressVisible(false);
        }

        @Override
        public void onComplete() {
        }
    }

}
