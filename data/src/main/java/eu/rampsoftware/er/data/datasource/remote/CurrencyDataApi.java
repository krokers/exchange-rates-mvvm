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

    String BASE_URL = "https://openexchangerates.org/api/";

    @GET(BASE_URL + "/types")
    Observable<Response<CurrencyDto>> getTypes(@Query("base") String baseCurrency);
}
