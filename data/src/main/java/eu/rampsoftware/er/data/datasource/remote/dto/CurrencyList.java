package eu.rampsoftware.er.data.datasource.remote.dto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

import io.reactivex.ObservableSource;

public class CurrencyList {

    @SerializedName("disclaimer")
    @Expose
    private String disclaimer;
    @SerializedName("license")
    @Expose
    private String license;
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("rates")
    @Expose
    private HashMap<String, Double> rates;

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public HashMap<String, Double> getRates() {
        return rates;
    }

    public void setRates(HashMap<String, Double> rates) {
        this.rates = rates;
    }


}