package eu.rampsoftware.er.di;

import eu.rampsoftware.er.data.CurrencyRepository;
import eu.rampsoftware.er.domain.usecases.GetCurrencySeriesUseCase;
import eu.rampsoftware.er.viewmodel.details.CurrencyDetailsViewModel;

interface ICurrencyDetailsActivityModule {
    GetCurrencySeriesUseCase provideGetCurrenciesUseCase(CurrencyRepository currencyRepository);

    CurrencyDetailsViewModel provideCurrencyDetailsViewModel(GetCurrencySeriesUseCase getSeriesUseCase);
}
