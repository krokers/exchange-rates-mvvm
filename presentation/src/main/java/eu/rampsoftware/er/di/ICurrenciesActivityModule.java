package eu.rampsoftware.er.di;

import android.app.Activity;

import javax.inject.Singleton;

import dagger.Provides;
import eu.rampsoftware.er.data.CurrencyRepository;
import eu.rampsoftware.er.data.PreferencesData;
import eu.rampsoftware.er.domain.usecases.GetCurrenciesRatesDate;
import eu.rampsoftware.er.domain.usecases.GetCurrenciesUseCase;
import eu.rampsoftware.er.navigation.Navigator;
import eu.rampsoftware.er.viewmodel.currencies.CurrencyListViewModel;

interface ICurrenciesActivityModule {
    Activity provideActivity();

    GetCurrenciesUseCase provideGetCurrenciesUseCase(CurrencyRepository mCurrencyRepository,
                                                     PreferencesData mPreferencesData);

    GetCurrenciesRatesDate provideGetCurrenciesRatesDate(PreferencesData preferencesData);

    CurrencyListViewModel provideCurrencyListViewModel(final Navigator navigator, GetCurrenciesUseCase currenciesUseCase,
                                                       GetCurrenciesRatesDate getCurrenciesRatesDate);

    Navigator provideNavigator(Activity context);
}
