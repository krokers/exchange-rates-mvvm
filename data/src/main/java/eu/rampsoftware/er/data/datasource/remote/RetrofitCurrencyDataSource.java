package eu.rampsoftware.er.data.datasource.remote;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import eu.rampsoftware.er.data.CurrencyData;

import eu.rampsoftware.er.data.datasource.CurrencyDataSource;
import eu.rampsoftware.er.data.datasource.remote.dto.CurrencyList;
import eu.rampsoftware.er.data.datasource.remote.mapper.CurrencyListMapper;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class RetrofitCurrencyDataSource implements CurrencyDataSource {

    private final String mAppId;
    private CurrencyDataApi mCurrencyDataApi;

    private SimpleDateFormat mDateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public RetrofitCurrencyDataSource(final CurrencyDataApi currencyDataApi,
                                      String appId){
        mCurrencyDataApi = currencyDataApi;
        mAppId = appId;
    }

    @Override
    public Observable<CurrencyData> getCurrencies(Date date) {

        final Observable<Response<CurrencyList>> request = mCurrencyDataApi.getCurrencies(mDateFormatter.format(date), mAppId);
        return request
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(entryListDtoResponse -> Observable.just(entryListDtoResponse.body()))
                .flatMap(currencyList -> Observable.just(CurrencyListMapper.toCurrencyData(currencyList)));
    }

    @Override
    public void storeCurrencies(final CurrencyData currencyData) {
        throw new RuntimeException("Not yet implemented");
    }


}
