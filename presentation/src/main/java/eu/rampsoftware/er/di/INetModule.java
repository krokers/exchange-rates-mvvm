package eu.rampsoftware.er.di;

import com.google.gson.Gson;

import eu.rampsoftware.er.data.datasource.remote.CurrencyDataApi;
import eu.rampsoftware.er.properties.ApplicationProperties;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

interface INetModule {
    Gson provideGson();

    OkHttpClient provideOkHttpClient(ApplicationProperties propertiesManager);

    Retrofit provideRetrofit(Gson gson, ApplicationProperties propertiesManager, OkHttpClient client);

    CurrencyDataApi provideCurrencyDataApi(Retrofit retrofit);
}
