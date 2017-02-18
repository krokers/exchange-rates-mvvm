package eu.rampsoftware.er.di;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import eu.rampsoftware.er.data.CurrencyRepository;
import eu.rampsoftware.er.data.PreferencesData;
import eu.rampsoftware.er.domain.usecases.GetCurrenciesRatesDate;
import eu.rampsoftware.er.domain.usecases.GetCurrenciesUseCase;
import eu.rampsoftware.er.navigation.Navigator;
import eu.rampsoftware.er.viewmodel.currencies.CurrencyListViewModel;

@Module
class TestCurrenciesActivityModule implements ICurrenciesActivityModule {
    @Override
    @Provides
    public Activity provideActivity() {
        return null;
    }

    @Provides
    @Override
    public GetCurrenciesUseCase provideGetCurrenciesUseCase(final CurrencyRepository mCurrencyRepository, final PreferencesData mPreferencesData) {
        return null;
    }

    @Provides
    @Override
    public GetCurrenciesRatesDate provideGetCurrenciesRatesDate(final PreferencesData preferencesData) {
        return null;
    }

    @Provides
    @Override
    public CurrencyListViewModel provideCurrencyListViewModel(final Navigator navigator, final GetCurrenciesUseCase currenciesUseCase, final GetCurrenciesRatesDate getCurrenciesRatesDate) {
        return null;
    }

    @Override
    @Provides
    public Navigator provideNavigator(final Activity context) {
        return null;
    }
}
