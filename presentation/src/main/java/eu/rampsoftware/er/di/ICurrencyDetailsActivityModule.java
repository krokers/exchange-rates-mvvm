package eu.rampsoftware.er.di;

import dagger.Provides;
import eu.rampsoftware.er.data.CurrencyRepository;
import eu.rampsoftware.er.domain.usecases.GetCurrencySeriesUseCase;
import eu.rampsoftware.er.viewmodel.details.CurrencyDetailsViewModel;

/**
 * Created by Ramps on 2017-02-18.
 */

interface ICurrencyDetailsActivityModule {
    GetCurrencySeriesUseCase provideGetCurrenciesUseCase(CurrencyRepository currencyRepository);

    CurrencyDetailsViewModel provideCurrencyDetailsViewModel(GetCurrencySeriesUseCase getSeriesUseCase);
}
