package eu.rampsoftware.er.view.currencies;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import eu.rampsoftware.er.BR;
import eu.rampsoftware.er.ExchangeRatesApplication;
import eu.rampsoftware.er.R;
import eu.rampsoftware.er.data.CurrencyRepository;
import eu.rampsoftware.er.data.PreferencesData;
import eu.rampsoftware.er.di.CurrenciesActivityModule;
import eu.rampsoftware.er.domain.usecases.GetCurrenciesRatesDate;
import eu.rampsoftware.er.domain.usecases.GetCurrenciesUseCase;
import eu.rampsoftware.er.properties.ApplicationProperties;
import eu.rampsoftware.er.viewmodel.currencies.CurrencyListViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CurrenciesActivity extends AppCompatActivity {

    @Inject
    ApplicationProperties mApplicationProperties;
    @Inject
    CurrencyRepository mCurrencyRepository;
    @Inject
    PreferencesData mPreferencesData;
    private CurrencyListViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((ExchangeRatesApplication) getApplication())
                .getApplicationComponent()
                .newCurrenciesActivitySubComponent(new CurrenciesActivityModule(this))
                .inject(this);
        mViewModel = new CurrencyListViewModel(
                new GetCurrenciesUseCase(Schedulers.io(),AndroidSchedulers.mainThread(),
                        mCurrencyRepository,
                        mPreferencesData),
                new GetCurrenciesRatesDate(Schedulers.io(), AndroidSchedulers.mainThread(),  mPreferencesData)
        );
        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_currencies);
        binding.setVariable(BR.model, mViewModel);
        mViewModel.onLoad();
    }

}
