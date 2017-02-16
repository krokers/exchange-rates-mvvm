package eu.rampsoftware.er.view.currencies;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import javax.inject.Inject;

import eu.rampsoftware.er.BR;
import eu.rampsoftware.er.ExchangeRatesApplication;
import eu.rampsoftware.er.R;
import eu.rampsoftware.er.di.CurrenciesActivityModule;
import eu.rampsoftware.er.di.CurrenciesActivitySubComponent;
import eu.rampsoftware.er.view.BaseActivity;
import eu.rampsoftware.er.viewmodel.currencies.CurrencyListViewModel;

public class CurrenciesActivity extends BaseActivity<CurrenciesActivitySubComponent> {

    @Inject
    CurrencyListViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_currencies);
        binding.setVariable(BR.model, mViewModel);
        if (!isRetained(savedInstanceState)) {
            mViewModel.onLoad();
        }
    }

    @Override
    protected void injectDependencies(final CurrenciesActivitySubComponent activityComponent) {
        activityComponent.inject(this);
    }

    protected CurrenciesActivitySubComponent newComponent() {
        return ((ExchangeRatesApplication) getApplication())
                .getApplicationComponent()
                .newCurrenciesActivitySubComponent(new CurrenciesActivityModule(this));
    }

}
