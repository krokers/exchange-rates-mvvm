package eu.rampsoftware.er.data.datasource;

import java.util.List;

import eu.rampsoftware.er.data.CurrencyData;
import io.reactivex.Observable;

/**
 * Created by Ramps on 2017-02-05.
 */

public interface CurrencyDataSource {
    Observable<List<CurrencyData>> getCurrencies();
}
