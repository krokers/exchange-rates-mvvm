package eu.rampsoftware.er.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.rampsoftware.er.data.datasource.remote.CurrencyDataApi;
import eu.rampsoftware.er.properties.ApplicationProperties;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule implements INetModule{

    @Override
    @Provides
    @Singleton
    public Gson provideGson() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        return gson;
    }

    @Override
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

    @Override
    @Provides
    @Singleton
    public Retrofit provideRetrofit(Gson gson, ApplicationProperties propertiesManager, OkHttpClient client) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(propertiesManager.baseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }

    @Override
    @Provides
    @Singleton
    public CurrencyDataApi provideCurrencyDataApi(Retrofit retrofit){
        return retrofit.create(CurrencyDataApi.class);
    }
}
