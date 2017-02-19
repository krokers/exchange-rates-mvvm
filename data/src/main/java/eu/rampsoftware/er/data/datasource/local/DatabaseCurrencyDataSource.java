package eu.rampsoftware.er.data.datasource.local;


import android.text.TextUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import eu.rampsoftware.er.data.CurrencyData;
import eu.rampsoftware.er.data.SingleValue;
import eu.rampsoftware.er.data.datasource.CurrencyDataSource;
import eu.rampsoftware.er.data.datasource.local.model.ValueEntity;
import io.reactivex.Observable;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static eu.rampsoftware.er.data.utils.DateUtils.midnight;

public class DatabaseCurrencyDataSource extends RealmManagerBase implements CurrencyDataSource {


    public DatabaseCurrencyDataSource() {
        super();
    }

    @Override
    public Observable<CurrencyData> getCurrencies(final Date date) {

        final long midnight = midnight(date.getTime());

        return readFromRealm((realm, emitter) -> {
            final RealmResults<ValueEntity> items = realm.where(ValueEntity.class)
                    .equalTo(ValueEntity.TIMESTAMP, midnight)
                    .findAll();
            if (items == null || items.size() == 0) {
                emitter.onComplete();
            }
            Map<String, Double> currencies = new HashMap<>();
            for (ValueEntity item : items) {
                currencies.put(item.getCode(), item.getValue());
            }
            emitter.onNext(new CurrencyData(new Date(midnight), currencies, ""));
            emitter.onComplete();
        });
    }

    @Override
    public boolean containsCurrencyValue(final Date date, final String currencyCode) {
        final long midnight = midnight(date.getTime());
        return readFromRealmSync((realm) -> {
            final RealmQuery<ValueEntity> query = realm.where(ValueEntity.class)
                    .equalTo(ValueEntity.TIMESTAMP, midnight);
            if (!TextUtils.isEmpty(currencyCode)) {
                query.equalTo(ValueEntity.CODE, currencyCode);
            }
            final long etriesCount = query.count();
            return etriesCount != 0;
        });
    }

    @Override
    public boolean containsCurrencyValues(final Date date) {
        return containsCurrencyValue(date, null);
    }

    @Override
    public Observable<SingleValue> getCurrencyValues(final Date startDate, final Date endDate, final String currencyCode) {
        final long startMidnight = midnight(startDate.getTime());
        final long endMidnight = midnight(endDate.getTime());
        return readFromRealm((realm, emitter) -> {
            final RealmResults<ValueEntity> items = realm.where(ValueEntity.class)
                    .between(ValueEntity.TIMESTAMP, startMidnight, endMidnight)
                    .equalTo(ValueEntity.CODE, currencyCode)
                    .findAll();

            for (ValueEntity valueEntity : items) {
                emitter.onNext(new SingleValue(new Date(valueEntity.getTimestamp()), valueEntity.getValue()));
            }
            emitter.onComplete();
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


}
