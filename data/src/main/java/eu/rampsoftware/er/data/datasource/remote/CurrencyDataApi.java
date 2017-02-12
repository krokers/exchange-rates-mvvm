package eu.rampsoftware.er.data.datasource.remote;

import eu.rampsoftware.er.data.datasource.remote.dto.CurrencyDto;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ramps on 2017-02-05.
 */

public interface CurrencyDataApi {

    @GET("/types")
    Observable<Response<CurrencyDto>> getCurrencies(@Query("base") String baseCurrency);
}
