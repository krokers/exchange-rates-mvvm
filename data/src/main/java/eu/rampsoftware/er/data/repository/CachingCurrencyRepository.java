package eu.rampsoftware.er.data.repository;

import java.util.List;

import eu.rampsoftware.er.data.CurrencyData;
import eu.rampsoftware.er.data.CurrencyRepository;
import eu.rampsoftware.er.data.datasource.CurrencyDataSource;
import io.reactivex.Observable;

public class CachingCurrencyRepository implements CurrencyRepository {
    private CurrencyDataSource mLocalSource;
    private CurrencyDataSource mRemoteSource;

    public Observable<List<CurrencyData>> getCurrencies(java.util.Currency baseCurrency) {
        return null;
    }
}
