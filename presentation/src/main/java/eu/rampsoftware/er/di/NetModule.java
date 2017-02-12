package eu.rampsoftware.er.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.rampsoftware.er.data.datasource.remote.CurrencyDataApi;
import eu.rampsoftware.er.properties.ApplicationProperties;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ramps on 2017-02-12.
 */
@Module
public class NetModule {

    @Provides
    @Singleton
    public Gson provideGson() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        return gson;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(ApplicationProperties propertiesManager) {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (propertiesManager.logsEnabled()) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        return httpClient;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Gson gson, ApplicationProperties propertiesManager, OkHttpClient client) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(propertiesManager.baseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public CurrencyDataApi provideCurrencyDataApi(Retrofit retrofit){
        return retrofit.create(CurrencyDataApi.class);
    }
}
