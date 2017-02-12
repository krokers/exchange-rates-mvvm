package eu.rampsoftware.er.di;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Provides;
import eu.rampsoftware.er.data.CurrencyDataSource;
import eu.rampsoftware.er.data.CurrencyRepository;
import eu.rampsoftware.er.data.datasource.remote.CurrencyDataApi;
import eu.rampsoftware.er.properties.ApplicationProperties;

/**
 * Created by Ramps on 2017-02-12.
 */

interface IApplicationModule {
    String provideExampleString();

    Context provideContext();

    ApplicationProperties provideApplicationProperties(Context context);

    CurrencyDataSource provideRemoteCurrencyDataSource(ApplicationProperties properties, CurrencyDataApi currencyDataApi);

    CurrencyDataSource provideLocalCurrencyDataSource();

    CurrencyRepository provideCurrencyRepository(@Named("local") CurrencyDataSource localSource,
                                                 @Named("remote") CurrencyDataSource remoteSource);
}
