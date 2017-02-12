package eu.rampsoftware.er.di;

import com.google.gson.Gson;

import dagger.Provides;
import eu.rampsoftware.er.data.datasource.remote.CurrencyDataApi;
import eu.rampsoftware.er.properties.ApplicationProperties;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Ramps on 2017-02-12.
 */

class TestNetModule implements INetModule {

    @Provides
    @Override
    public Gson provideGson() {
        return null;
    }

    @Provides
    @Override
    public OkHttpClient provideOkHttpClient(final ApplicationProperties propertiesManager) {
        return null;
    }

    @Provides
    @Override
    public Retrofit provideRetrofit(final Gson gson, final ApplicationProperties propertiesManager, final OkHttpClient client) {
        return null;
    }

    @Provides
    @Override
    public CurrencyDataApi provideCurrencyDataApi(final Retrofit retrofit) {
        return null;
    }
}
