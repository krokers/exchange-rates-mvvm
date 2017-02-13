package eu.rampsoftware.er.di;

import dagger.Module;
import dagger.Provides;
import eu.rampsoftware.er.data.CurrencyRepository;
import eu.rampsoftware.er.data.PreferencesData;
import eu.rampsoftware.er.domain.usecases.GetCurrenciesRatesDate;
import eu.rampsoftware.er.domain.usecases.GetCurrenciesUseCase;
import eu.rampsoftware.er.viewmodel.currencies.CurrencyListViewModel;

/**
 * Created by Ramps on 2017-02-12.
 */
@Module
class TestCurrenciesActivityModule implements ICurrenciesActivityModule {
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
    public CurrencyListViewModel provideCurrencyListViewModel(final GetCurrenciesUseCase currenciesUseCase, final GetCurrenciesRatesDate getCurrenciesRatesDate) {
        return null;
    }
}
