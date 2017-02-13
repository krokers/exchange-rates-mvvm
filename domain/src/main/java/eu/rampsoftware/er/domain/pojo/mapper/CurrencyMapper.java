package eu.rampsoftware.er.domain.pojo.mapper;

import java.util.ArrayList;
import java.util.List;

import eu.rampsoftware.er.data.CurrencyData;
import eu.rampsoftware.er.domain.pojo.CurrencyHome;
import io.reactivex.functions.Function;

public class CurrencyMapper {

    public static Function<? super List<CurrencyData>, ? extends List<CurrencyHome>> mapper =
            (Function<List<CurrencyData>, List<CurrencyHome>>) currencyDatas -> {
                final ArrayList<CurrencyHome> currencyHomeList = new ArrayList<>();
                for (CurrencyData currencyData : currencyDatas) {
                    currencyHomeList.add(new CurrencyHome(currencyData));
                }
                return currencyHomeList;
            };
}
