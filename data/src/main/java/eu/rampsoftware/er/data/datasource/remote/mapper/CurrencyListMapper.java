package eu.rampsoftware.er.data.datasource.remote.mapper;

import java.util.Date;

import eu.rampsoftware.er.data.CurrencyData;
import eu.rampsoftware.er.data.datasource.remote.dto.CurrencyList;

public class CurrencyListMapper {

    public static CurrencyData toCurrencyData(final CurrencyList currencyList) {
        return new CurrencyData(new Date(currencyList.getTimestamp()), currencyList.getRates(), currencyList.getBase());
    }
}
