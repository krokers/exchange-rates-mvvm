package eu.rampsoftware.er.di;


import android.content.Context;

import dagger.Module;

/**
 * Dagger module responsible for providing {@link CurrenciesActivitySubComponent} dependencies. Scope
 * of provided dependencies must match the scope of component, which is {@link CurrenciesActivityScope}.
 */
@Module
public class CurrenciesActivityModule {

    private final Context mContext;

    public CurrenciesActivityModule(final Context context) {
        mContext = context;
    }


}
