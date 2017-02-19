package eu.rampsoftware.er.view.currencies;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import javax.inject.Inject;

import eu.rampsoftware.er.BR;
import eu.rampsoftware.er.ExchangeRatesApplication;
import eu.rampsoftware.er.R;
import eu.rampsoftware.er.di.CurrencyDetailsActivityModule;
import eu.rampsoftware.er.di.CurrencyDetailsActivitySubComponent;
import eu.rampsoftware.er.view.BaseActivity;
import eu.rampsoftware.er.viewmodel.details.CurrencyDetailsViewModel;

public class CurrencyDetailsActivity extends BaseActivity<CurrencyDetailsActivitySubComponent> {

    private static final String INTENT_EXTRA_PARAM_CURRENCY_CODE = "INTENT_EXTRA_PARAM_CURRENCY_CODE";
    @Inject
    CurrencyDetailsViewModel mViewModel;

    public static Intent getCallingIntent(final Context context, final String currencyCode) {
        Intent callingIntent = new Intent(context, CurrencyDetailsActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_CURRENCY_CODE, currencyCode);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_currency_details);
        binding.setVariable(BR.model, mViewModel);
        if (!isRetained(savedInstanceState)) {
            final String currencyCode = getIntent().getStringExtra(INTENT_EXTRA_PARAM_CURRENCY_CODE);
            Bundle bundle = new Bundle();
            bundle.putString(CurrencyDetailsViewModel.KEY_CURRENCY_CODE, currencyCode);
            mViewModel.onLoad(bundle);
        }
    }

    @Override
    protected void onDestroy() {
        if(!isChangingConfigurations()) {
            mViewModel.dispose();
        }
        super.onDestroy();
    }

    @Override
    protected void injectDependencies(final CurrencyDetailsActivitySubComponent activityComponent) {
        activityComponent.inject(this);
    }

    protected CurrencyDetailsActivitySubComponent newComponent() {
        return ((ExchangeRatesApplication) getApplication())
                .getApplicationComponent()
                .newCurrencyDetailsActivitySubComponent(new CurrencyDetailsActivityModule(this));
    }
}
