package eu.rampsoftware.er.data.datasource.local;

import java.util.Date;


import eu.rampsoftware.er.data.CurrencyData;
import eu.rampsoftware.er.data.CurrencyDataSource;
import io.reactivex.Observable;

public class DatabaseCurrencyDataSource implements CurrencyDataSource {


    @Override
    public Observable<CurrencyData> getCurrencies(final Date date) {
        return null;
    }

}
