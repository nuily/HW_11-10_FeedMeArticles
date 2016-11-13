package nyc.c4q.huilin.feedmejobs;

import nyc.c4q.huilin.feedmejobs.Pojos.BuzzResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Akasha on 11/11/16.
 */

public interface BuzzService {


    @GET("v1/articles?source=buzzfeed&sortBy=top&apiKey=4f8ad541e5a9432c9a0709367f701641")
    Call<BuzzResponse> listBuzzArticles();




//    @GET("v1/articles")
//    Call<BuzzResponse> listBuzzArticles(@Query("source") String source, @Query("sortBy") String sortBy, @Query("apiKey") String apiKey);


}
