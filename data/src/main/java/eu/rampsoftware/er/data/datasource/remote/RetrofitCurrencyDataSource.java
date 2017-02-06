package eu.rampsoftware.er.data.datasource.remote;

import java.util.List;

import eu.rampsoftware.er.data.datasource.CurrencyDataSource;
import eu.rampsoftware.er.data.model.CurrencyData;
import io.reactivex.Observable;

/**
 * Created by Ramps on 2017-02-05.
 */

public class RetrofitCurrencyDataSource implements CurrencyDataSource {



    @Override
    public Observable<List<CurrencyData>> getCurrencies() {
        return null;
    }
}
