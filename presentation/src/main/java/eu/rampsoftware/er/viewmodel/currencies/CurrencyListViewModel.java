package eu.rampsoftware.er.viewmodel.currencies;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.fernandocejas.arrow.optional.Optional;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import eu.rampsoftware.er.data.CurrencyData;
import eu.rampsoftware.er.domain.usecases.GetCurrenciesRatesDate;
import eu.rampsoftware.er.domain.usecases.GetCurrenciesUseCase;
import eu.rampsoftware.er.viewmodel.BaseViewModel;
import io.reactivex.observers.DisposableObserver;

public class CurrencyListViewModel extends BaseObservable implements BaseViewModel {

    private final GetCurrenciesUseCase mCurrenciesUseCase;
    private final GetCurrenciesRatesDate mGetCurrenciesRatesDate;
    private ObservableList<CurrencyItemViewModel> mCurrencies;

    public CurrencyListViewModel(GetCurrenciesUseCase currenciesUseCase, GetCurrenciesRatesDate getCurrenciesRatesDate) {
        mCurrencies = new ObservableArrayList<>();
        mCurrenciesUseCase = currenciesUseCase;
        mGetCurrenciesRatesDate = getCurrenciesRatesDate;
    }

    public ObservableList<CurrencyItemViewModel> getCurrencies() {
        return mCurrencies;
    }

    @Override
    public void onLoad() {
        mGetCurrenciesRatesDate.run(new DateObserver());
    }

    private void onDate(final Optional<Date> date) {
        Date readDate = date.or(new Date());
        mCurrenciesUseCase.run(new CurrenciesDataObserver(), new GetCurrenciesUseCase.CurrenciesParam(readDate));
    }

    private void onCurrencyDataLoaded(final CurrencyData currencyData) {
        final Map<String, Double> currencies = currencyData.getCurrencies();
        final Set<String> currencyKeys = currencies.keySet();
        for (String currencyCode : currencyKeys) {
            final Double value = currencies.get(currencyCode);
            mCurrencies.add(new CurrencyItemViewModel(currencyCode, value));
        }

    }

    private final class DateObserver extends DisposableObserver<Optional<Date>> {

        @Override
        public void onNext(final Optional<Date> date) {
            onDate(date);
        }

        @Override
        public void onError(final Throwable e) {}

        @Override
        public void onComplete() {}
    }



    private final class CurrenciesDataObserver extends DisposableObserver<CurrencyData> {
        @Override
        public void onNext(final CurrencyData currencyData) {
            onCurrencyDataLoaded(currencyData);
        }

        @Override
        public void onError(final Throwable e) {
        }

        @Override
        public void onComplete() {
        }
    }



}
