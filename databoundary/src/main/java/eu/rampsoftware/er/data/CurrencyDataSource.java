package eu.rampsoftware.er.data;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Ramps on 2017-02-05.
 */

public interface CurrencyDataSource {
    Observable<List<CurrencyData>> getCurrencies();
}
