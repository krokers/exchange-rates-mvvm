package eu.rampsoftware.er.data.datasource;

import java.util.Date;

import eu.rampsoftware.er.data.CurrencyData;
import eu.rampsoftware.er.data.SingleValue;
import io.reactivex.Observable;

public interface CurrencyDataSource {

    Observable<CurrencyData> getCurrencies(Date date);

    boolean containsCurrencyValue(Date date, String currencyCode);

    boolean containsCurrencyValues(Date date);

    Observable<SingleValue> getCurrencyValues(Date startDate, Date endDate, String currencyCode);

    void storeCurrencies(CurrencyData currencyData);
}
