package eu.rampsoftware.er.di;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.rampsoftware.er.data.CurrencyRepository;
import eu.rampsoftware.er.data.PreferencesData;
import eu.rampsoftware.er.data.datasource.CurrencyDataSource;
import eu.rampsoftware.er.data.datasource.local.DatabaseCurrencyDataSource;
import eu.rampsoftware.er.data.datasource.remote.CurrencyDataApi;
import eu.rampsoftware.er.data.datasource.remote.RetrofitCurrencyDataSource;
import eu.rampsoftware.er.data.preferences.SharedPreferencesData;
import eu.rampsoftware.er.data.repository.CachingCurrencyRepository;
import eu.rampsoftware.er.properties.ApplicationProperties;
import eu.rampsoftware.er.properties.ResourcesApplicationProperties;

/**
 * Dagger module responsible for providing {@link ApplicationComponent} scope dependencies.
 */
@Module
public class ApplicationModule implements IApplicationModule {

    private final Context mContext;

    public ApplicationModule(Context context){
        mContext = context;
    }

    @Override
    @Provides
    @Named("example")
    @Singleton
    public String provideExampleString() {
        return "Production Example";
    }

    @Override
    @Provides
    @Singleton
    public Context provideContext(){
        return mContext;
    }

    @Override
    @Provides
    @Singleton
    public ApplicationProperties provideApplicationProperties(final Context context){
        return new ResourcesApplicationProperties(context);
    }

    @Override
    @Provides
    @Singleton
    @Named("remote")
    public CurrencyDataSource provideRemoteCurrencyDataSource(ApplicationProperties properties, final CurrencyDataApi currencyDataApi){
        return new RetrofitCurrencyDataSource(currencyDataApi, properties.getOerAppId());
    }

    @Override
    @Provides
    @Singleton
    @Named("local")
    public CurrencyDataSource provideLocalCurrencyDataSource(){
        return new DatabaseCurrencyDataSource();
    }

    @Override
    @Provides
    @Singleton
    public CurrencyRepository provideCurrencyRepository(@Named("local") final CurrencyDataSource localSource,
                                                        @Named("remote") final CurrencyDataSource remoteSource){
        return new CachingCurrencyRepository(localSource, remoteSource);
    }

    @Provides
    @Singleton
    @Override
    public PreferencesData providePreferencesData(ApplicationProperties properties){
        return new SharedPreferencesData(mContext.getSharedPreferences(
                properties.sharedPreferencesName(), Context.MODE_PRIVATE));
    }
}
