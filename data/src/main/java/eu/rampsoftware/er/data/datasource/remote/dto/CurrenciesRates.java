package eu.rampsoftware.er.data.datasource.remote.dto;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Ramps on 2017-02-12.
 */

public class CurrenciesRates {
    @Expose
    private HashMap<String, Double> questions;

}
