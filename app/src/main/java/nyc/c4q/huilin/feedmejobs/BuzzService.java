package nyc.c4q.huilin.feedmejobs;

import nyc.c4q.huilin.feedmejobs.Pojos.BuzzResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Akasha on 11/11/16.
 */

public interface BuzzService {

   // @GET("v1/articles?source=buzzfeed&sortBy=latest&apiKey=fe7aadb863534e0084b1079b95d5ebad")


    @GET("v1/articles{source}{sortBy}{apiKey}")
    Call<BuzzResponse> listBuzzArticles(@Query("source") String source, @Query("sortBy") String sortBy, @Query("apiKey") String apiKey);


}
