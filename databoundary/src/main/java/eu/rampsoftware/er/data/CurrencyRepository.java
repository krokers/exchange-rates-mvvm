package eu.rampsoftware.er.data;


import java.util.List;

import io.reactivex.Observable;

public interface CurrencyRepository {

    Observable<List<CurrencyData>> getCurrencies(java.util.Currency baseCurrency);
}
