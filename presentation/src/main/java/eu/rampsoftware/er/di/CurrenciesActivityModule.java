package eu.rampsoftware.er.di;


import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.rampsoftware.er.data.PreferencesData;
import eu.rampsoftware.er.data.preferences.SharedPreferencesData;
import eu.rampsoftware.er.properties.ApplicationProperties;

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
