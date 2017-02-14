package eu.rampsoftware.er.data.datasource;

import java.util.Date;

import eu.rampsoftware.er.data.CurrencyData;
import io.reactivex.Observable;

public interface CurrencyDataSource {

    Observable<CurrencyData> getCurrencies(Date date);
}
