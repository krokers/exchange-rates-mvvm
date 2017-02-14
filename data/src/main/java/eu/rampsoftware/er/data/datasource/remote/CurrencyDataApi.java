package eu.rampsoftware.er.data.datasource.remote;

import eu.rampsoftware.er.data.datasource.remote.dto.CurrencyList;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CurrencyDataApi {

    @GET("/historical/{date}.json")
    Observable<Response<CurrencyList>> getCurrencies(@Path("date") String date,
                                                     @Query("app_id") String appId);
}
