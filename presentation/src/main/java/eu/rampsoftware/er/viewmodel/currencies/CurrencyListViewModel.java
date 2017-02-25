package eu.rampsoftware.er.viewmodel.currencies;

import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import eu.rampsoftware.er.BR;
import eu.rampsoftware.er.data.CurrencyData;
import eu.rampsoftware.er.domain.usecases.GetCurrenciesRatesDate;
import eu.rampsoftware.er.domain.usecases.GetCurrenciesUseCase;
import eu.rampsoftware.er.navigation.Navigator;
import eu.rampsoftware.er.viewmodel.BaseDisposableViewModel;
import eu.rampsoftware.er.viewmodel.BaseViewModel;

import static com.fernandocejas.arrow.checks.Preconditions.checkNotNull;

public class CurrencyListViewModel extends BaseDisposableViewModel implements BaseViewModel {

    private final GetCurrenciesUseCase mCurrenciesUseCase;
    private final GetCurrenciesRatesDate mGetCurrenciesRatesDate;
    private final Navigator mNavigator;
    private ObservableList<CurrencyItemViewModel> mCurrencies;
    private long DAY_MILLIS = 24 * 3600 * 1000;
    private boolean mIsProgressVisible;
    private Date mReadDate;
    private SimpleDateFormat mDateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public CurrencyListViewModel(Navigator navigator, GetCurrenciesUseCase currenciesUseCase, GetCurrenciesRatesDate getCurrenciesRatesDate) {
        mCurrencies = new ObservableArrayList<>();
        mCurrenciesUseCase = checkNotNull(currenciesUseCase);
        mGetCurrenciesRatesDate = checkNotNull(getCurrenciesRatesDate);
        mNavigator = checkNotNull(navigator);
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
    public void onLoad(final Bundle bundle) {
        setProgressVisible(true);
        addDisposable(
                mGetCurrenciesRatesDate.run()
                        .doOnError(throwable -> setProgressVisible(false))
                        .subscribe(dateOptional -> {
                            mReadDate = dateOptional.or(mReadDate);
                            onDate(mReadDate);
                        })
        );
    }

    private void onDate(final Date date) {
        addDisposable(
                mCurrenciesUseCase.run(new GetCurrenciesUseCase.CurrenciesParam(date))
                        .doFinally(() -> setProgressVisible(false))
                        .subscribe(this::onCurrencyDataLoaded)
        );

    }

    private void onCurrencyDataLoaded(final CurrencyData currencyData) {
        final Map<String, Double> currencies = currencyData.getCurrencies();
        final Set<String> currencyKeys = currencies.keySet();
        mCurrencies.clear();
        for (String currencyCode : currencyKeys) {
            final Double value = currencies.get(currencyCode);
            mCurrencies.add(new CurrencyItemViewModel(mNavigator, currencyCode, value));
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
}
