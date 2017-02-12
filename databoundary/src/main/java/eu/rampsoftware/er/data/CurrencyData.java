package eu.rampsoftware.er.data;

import java.util.Date;
import java.util.Map;

public class CurrencyData {
    private final String mBaseCurrency;
    private Date mDate;
    private Map<String, Double> mCurrencies;

    public CurrencyData(final Date date, final Map<String, Double> currencies, final String baseCurrency) {
        mDate = date;
        mCurrencies = currencies;
        mBaseCurrency = baseCurrency;
    }
}
