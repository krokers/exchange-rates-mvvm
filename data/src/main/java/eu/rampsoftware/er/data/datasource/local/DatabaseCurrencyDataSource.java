package eu.rampsoftware.er.data.datasource.local;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import eu.rampsoftware.er.data.CurrencyData;
import eu.rampsoftware.er.data.datasource.CurrencyDataSource;
import eu.rampsoftware.er.data.datasource.local.model.ValueEntity;
import io.reactivex.Observable;
import io.realm.RealmResults;

public class DatabaseCurrencyDataSource extends RealmManagerBase implements CurrencyDataSource {
    private static final long DAY_MILLIS = 24 * 3600 * 1000;

    public DatabaseCurrencyDataSource() {
        super();
    }

    @Override
    public Observable<CurrencyData> getCurrencies(final Date date) {
        final long midnight = midnight(date.getTime());
        return readFromRealm(realm -> {
            final RealmResults<ValueEntity> items = realm.where(ValueEntity.class)
                    .equalTo(ValueEntity.TIMESTAMP, midnight)
                    .findAll();
            if (items == null || items.size() == 0) {
                return Observable.empty();
            }
            Map<String, Double> currencies = new HashMap<>();
            for (ValueEntity item : items) {
                currencies.put(item.getCode(), item.getValue());
            }
            return Observable.just(new CurrencyData(new Date(midnight), currencies, ""));
        });
    }

    @Override
    public void storeCurrencies(final CurrencyData currencyData) {
        executeInTransaction(realm -> {
            final long time = midnight(currencyData.getDate().getTime());
            final Map<String, Double> currencies = currencyData.getCurrencies();
            for (String currencyCode : currencies.keySet()) {
                ValueEntity currencyValue = realm.where(ValueEntity.class)
                        .equalTo(ValueEntity.TIMESTAMP, time)
                        .equalTo(ValueEntity.CODE, currencyCode)
                        .findFirst();
                if (null == currencyValue) {
                    currencyValue = realm.copyToRealm(new ValueEntity(currencies.get(currencyCode), time, currencyCode));
                }
            }
        });
    }

    private long midnight(final long timestamp) {
        return (timestamp / DAY_MILLIS) * DAY_MILLIS;
    }

}
