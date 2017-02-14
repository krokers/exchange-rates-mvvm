package eu.rampsoftware.er.di;

import android.content.Context;

import javax.inject.Named;

import eu.rampsoftware.er.data.CurrencyRepository;
import eu.rampsoftware.er.data.PreferencesData;
import eu.rampsoftware.er.data.datasource.CurrencyDataSource;
import eu.rampsoftware.er.data.datasource.remote.CurrencyDataApi;
import eu.rampsoftware.er.properties.ApplicationProperties;

interface IApplicationModule {
    String provideExampleString();

    Context provideContext();

    ApplicationProperties provideApplicationProperties(Context context);

    CurrencyDataSource provideRemoteCurrencyDataSource(ApplicationProperties properties, CurrencyDataApi currencyDataApi);

    CurrencyDataSource provideLocalCurrencyDataSource();

    CurrencyRepository provideCurrencyRepository(@Named("local") CurrencyDataSource localSource,
                                                 @Named("remote") CurrencyDataSource remoteSource);

    PreferencesData providePreferencesData(ApplicationProperties properties);
}
