package eu.rampsoftware.er.di;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.rampsoftware.er.data.CurrencyRepository;
import eu.rampsoftware.er.data.PreferencesData;
import eu.rampsoftware.er.data.datasource.CurrencyDataSource;
import eu.rampsoftware.er.data.datasource.remote.CurrencyDataApi;
import eu.rampsoftware.er.properties.ApplicationProperties;
import eu.rampsoftware.er.properties.ResourcesApplicationProperties;

@Module
public class TestApplicationModule implements IApplicationModule{

    private final Context mContext;

    public TestApplicationModule(Context context){
        mContext = context;
    }

    @Provides
    @Named("example")
    public String provideExampleString() {
        return "Test Example";
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return mContext;
    }

    @Provides
    @Singleton
    public ApplicationProperties provideApplicationProperties(final Context context){
        return new ResourcesApplicationProperties(context);
    }

    @Provides
    @Override
    @Named("remote")
    public CurrencyDataSource provideRemoteCurrencyDataSource(final ApplicationProperties properties, final CurrencyDataApi currencyDataApi) {
        return null;
    }

    @Provides
    @Override
    @Named("local")
    public CurrencyDataSource provideLocalCurrencyDataSource() {
        return null;
    }

    @Provides
    @Override
    public CurrencyRepository provideCurrencyRepository(@Named("local") final CurrencyDataSource localSource, @Named("remote") final CurrencyDataSource remoteSource) {
        return null;
    }

    @Override
    @Provides
    public PreferencesData providePreferencesData(final ApplicationProperties properties) {
        return null;
    }
}
