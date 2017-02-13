package eu.rampsoftware.er.viewmodel.currencies;

import android.databinding.BaseObservable;
import android.databinding.ObservableList;

import java.util.Date;

import eu.rampsoftware.er.data.CurrencyData;
import eu.rampsoftware.er.domain.usecases.GetCurrenciesUseCase;
import eu.rampsoftware.er.viewmodel.BaseViewModel;
import io.reactivex.observers.DisposableObserver;

public class CurrencyListViewModel extends BaseObservable implements BaseViewModel {

    private final GetCurrenciesUseCase mCurrenciesUseCase;
    private ObservableList<CurrencyItemViewModel> mCurrencies;

    public CurrencyListViewModel(GetCurrenciesUseCase currenciesUseCase) {
        mCurrenciesUseCase = currenciesUseCase;
    }

    public ObservableList<CurrencyItemViewModel> getCurrencies() {
        return mCurrencies;
    }

    @Override
    public void onLoad() {
        mCurrenciesUseCase.run(new DisposableObserver<CurrencyData>() {
            @Override
            public void onNext(final CurrencyData currencyData) {

            }

            @Override
            public void onError(final Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, new GetCurrenciesUseCase.CurrenciesParam(new Date()));

    }

}
