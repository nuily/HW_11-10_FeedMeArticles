package nyc.c4q.huilin.feedmejobs.network;

import nyc.c4q.huilin.feedmejobs.Pojos.BuzzResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Akasha on 11/11/16.
 */

public interface BuzzService {


    @GET("v1/articles?source=buzzfeed&sortBy=top")
    Call<BuzzResponse> listBuzzArticles(@Query("apiKey") String apiKey);




//    @GET("v1/articles")
//    Call<BuzzResponse> listBuzzArticles(@Query("source") String source, @Query("sortBy") String sortBy, @Query("apiKey") String apiKey);


}
