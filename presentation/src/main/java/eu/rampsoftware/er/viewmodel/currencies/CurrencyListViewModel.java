package eu.rampsoftware.er.viewmodel.currencies;

import android.databinding.BaseObservable;
import android.databinding.ObservableList;
import android.os.Bundle;

import java.util.Date;
import java.util.List;

import eu.rampsoftware.er.data.CurrencyData;
import eu.rampsoftware.er.domain.pojo.CurrencyHome;
import eu.rampsoftware.er.domain.usecases.GetCurrenciesUseCase;
import eu.rampsoftware.er.viewmodel.BaseViewModel;
import io.reactivex.observers.DisposableObserver;

public class CurrencyListViewModel extends BaseObservable implements BaseViewModel {

    private final GetCurrenciesUseCase mCurrenciesUseCase;
    private ObservableList<CurrencyItemViewModel> mCurrencies;

    public ObservableList<CurrencyItemViewModel> getCurrencies() {
        return mCurrencies;
    }

    public CurrencyListViewModel(GetCurrenciesUseCase currenciesUseCase){
        mCurrenciesUseCase = currenciesUseCase;
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

    @Override
    public void onSaveInstanceState(final Bundle bundle) {

    }

    @Override
    public void onRestoreInstanceState(final Bundle bundle) {

    }
}
