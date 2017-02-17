package eu.rampsoftware.er.data.datasource.local.model;

import io.realm.RealmObject;

public class ValueEntity extends RealmObject {

    public static final String TIMESTAMP = "timestamp";
    public static final String CODE = "code";
    private Double value;
    private long timestamp;
    private String code;

    public ValueEntity(){}
    public ValueEntity(final Double value, final long timestamp, final String currencyCode) {
        this.value = value;
        this.timestamp = timestamp;
        this.code = currencyCode;
    }

    public Double getValue() {
        return value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getCode() {
        return code;
    }
}
