package eu.rampsoftware.er.data;


import java.util.Date;
import java.util.List;

import io.reactivex.Observable;

public interface CurrencyRepository {

    Observable<CurrencyData> getCurrencies(final Date date);
}
