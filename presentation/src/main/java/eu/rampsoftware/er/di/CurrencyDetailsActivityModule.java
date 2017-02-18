package eu.rampsoftware.er.di;


import android.content.Context;

import dagger.Module;
import dagger.Provides;
import eu.rampsoftware.er.data.CurrencyRepository;
import eu.rampsoftware.er.domain.usecases.GetCurrenciesRatesDate;
import eu.rampsoftware.er.domain.usecases.GetCurrenciesUseCase;
import eu.rampsoftware.er.domain.usecases.GetCurrencySeriesUseCase;
import eu.rampsoftware.er.viewmodel.currencies.CurrencyListViewModel;
import eu.rampsoftware.er.viewmodel.details.CurrencyDetailsViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Dagger module responsible for providing {@link CurrencyDetailsActivitySubComponent} dependencies. Scope
 * of provided dependencies must match the scope of component, which is {@link CurrencyDetailsActivityScope}.
 */
@Module
public class CurrencyDetailsActivityModule implements ICurrencyDetailsActivityModule {

    private final Context mContext;

    public CurrencyDetailsActivityModule(final Context context) {
        mContext = context;
    }


    @Provides
    @CurrencyDetailsActivityScope
    @Override
    public GetCurrencySeriesUseCase provideGetCurrenciesUseCase(final CurrencyRepository currencyRepository) {
        return new GetCurrencySeriesUseCase(Schedulers.io(), AndroidSchedulers.mainThread(),
                currencyRepository);
    }


    @Provides
    @CurrencyDetailsActivityScope
    @Override
    public CurrencyDetailsViewModel provideCurrencyDetailsViewModel(final GetCurrencySeriesUseCase getSeriesUseCase){
        return new CurrencyDetailsViewModel( getSeriesUseCase );
    }

}
