package singh.harmeet.com.searchproject1.data.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import singh.harmeet.com.searchproject1.data.model.Result;
import singh.harmeet.com.searchproject1.util.Constants;

/**
 * Created by harmeet.assi on 4/10/2018.
 */

public interface SearchApiInterface {

    @GET(Constants.query_end_point)
    Observable<Result> getQueryResult(@Query(Constants.client_id_) String client_id,@Query(Constants.client_secret_) String client_secret,@Query(Constants.query) String query,@Query("page") String page);
}
