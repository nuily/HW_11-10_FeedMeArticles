package nyc.c4q.huilin.feedmejobs.network;

import nyc.c4q.huilin.feedmejobs.Pojos.EngadgetResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EngadgetService {

@GET("v1/articles?source=engadget&sortBy=top")
    Call<EngadgetResponse> getArticles(@Query("apiKey") String apiKey);

}
