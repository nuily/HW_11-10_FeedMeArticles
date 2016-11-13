package nyc.c4q.huilin.feedmejobs.network;

import nyc.c4q.huilin.feedmejobs.Pojos.EngadgetResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface EngadgetService {

    String ACCESS_TOKEN = "6ef4712688694af89180f0f1b10d9b0c";

@GET("v1/articles?source=engadget&sortBy=top&apiKey="+ACCESS_TOKEN)
    Call<EngadgetResponse> getArticles();

}
