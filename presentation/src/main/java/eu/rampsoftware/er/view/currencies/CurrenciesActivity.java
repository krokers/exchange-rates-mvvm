package eu.rampsoftware.er.view.currencies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import eu.rampsoftware.er.ExchangeRatesApplication;
import eu.rampsoftware.er.R;
import eu.rampsoftware.er.data.CurrencyRepository;
import eu.rampsoftware.er.data.repository.CachingCurrencyRepository;
import eu.rampsoftware.er.di.CurrenciesActivityModule;
import eu.rampsoftware.er.domain.usecases.GetCurrenciesUseCase;
import eu.rampsoftware.er.properties.ApplicationProperties;
import eu.rampsoftware.er.viewmodel.currencies.CurrencyListViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CurrenciesActivity extends AppCompatActivity {

    private CurrencyListViewModel mViewModel;

    @Inject
    ApplicationProperties mApplicationProperties;

    @Inject
    CurrencyRepository mCurrencyRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currencies);
        ((ExchangeRatesApplication)getApplication())
                .getApplicationComponent()
                .newCurrenciesActivitySubComponent(new CurrenciesActivityModule())
                .inject(this);
        mViewModel = new CurrencyListViewModel(
                new GetCurrenciesUseCase(AndroidSchedulers.mainThread(), Schedulers.io(), mCurrencyRepository));
        mViewModel.onLoad();
    }

    private String logTag() {
        return getClass().getSimpleName();
    }


    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        mViewModel.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        mViewModel.onRestoreInstanceState(savedInstanceState);
        super.onRestoreInstanceState(savedInstanceState);
    }
}
