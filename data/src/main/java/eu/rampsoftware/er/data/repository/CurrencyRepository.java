package eu.rampsoftware.er.data.repository;

import java.util.List;


import eu.rampsoftware.er.data.datasource.CurrencyDataSource;
import eu.rampsoftware.er.data.model.CurrencyData;
import io.reactivex.Observable;

public class CurrencyRepository {
    private CurrencyDataSource mLocalSource;
    private CurrencyDataSource mRemoteSource;
    public Observable<List<CurrencyData>> getCurrencies(java.util.Currency baseCurrency ){
        return null;
    }
}
