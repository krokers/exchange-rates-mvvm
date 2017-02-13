package eu.rampsoftware.er.di;

import eu.rampsoftware.er.data.CurrencyRepository;
import eu.rampsoftware.er.data.PreferencesData;
import eu.rampsoftware.er.domain.usecases.GetCurrenciesRatesDate;
import eu.rampsoftware.er.domain.usecases.GetCurrenciesUseCase;
import eu.rampsoftware.er.viewmodel.currencies.CurrencyListViewModel;

/**
 * Created by Ramps on 2017-02-13.
 */

interface ICurrenciesActivityModule {
    GetCurrenciesUseCase provideGetCurrenciesUseCase(CurrencyRepository mCurrencyRepository,
                                                     PreferencesData mPreferencesData);

    GetCurrenciesRatesDate provideGetCurrenciesRatesDate(PreferencesData preferencesData);

    CurrencyListViewModel provideCurrencyListViewModel(GetCurrenciesUseCase currenciesUseCase,
                                                       GetCurrenciesRatesDate getCurrenciesRatesDate);
}
