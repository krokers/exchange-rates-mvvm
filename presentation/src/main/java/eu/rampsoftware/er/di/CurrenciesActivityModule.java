package eu.rampsoftware.er.di;


import android.content.Context;

import dagger.Module;
import dagger.Provides;
import eu.rampsoftware.er.data.CurrencyRepository;
import eu.rampsoftware.er.data.PreferencesData;
import eu.rampsoftware.er.domain.usecases.GetCurrenciesRatesDate;
import eu.rampsoftware.er.domain.usecases.GetCurrenciesUseCase;
import eu.rampsoftware.er.viewmodel.currencies.CurrencyListViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Dagger module responsible for providing {@link CurrenciesActivitySubComponent} dependencies. Scope
 * of provided dependencies must match the scope of component, which is {@link CurrenciesActivityScope}.
 */
@Module
public class CurrenciesActivityModule implements ICurrenciesActivityModule {

    private final Context mContext;

    public CurrenciesActivityModule(final Context context) {
        mContext = context;
    }


    @Provides
    @CurrenciesActivityScope
    @Override
    public GetCurrenciesUseCase provideGetCurrenciesUseCase(final CurrencyRepository currencyRepository,
                                                            final PreferencesData preferencesData) {
        return new GetCurrenciesUseCase(Schedulers.io(), AndroidSchedulers.mainThread(),
                currencyRepository,
                preferencesData);
    }

    @Provides
    @CurrenciesActivityScope
    @Override
    public GetCurrenciesRatesDate provideGetCurrenciesRatesDate(final PreferencesData preferencesData){
        return new GetCurrenciesRatesDate(Schedulers.io(), AndroidSchedulers.mainThread(),  preferencesData);
    }

    @Provides
    @CurrenciesActivityScope
    @Override
    public CurrencyListViewModel provideCurrencyListViewModel(final GetCurrenciesUseCase currenciesUseCase,
                                                              final GetCurrenciesRatesDate getCurrenciesRatesDate){
        return new CurrencyListViewModel( currenciesUseCase, getCurrenciesRatesDate );
    }

}
