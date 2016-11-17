package nyc.c4q.huilin.feedmejobs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.huilin.feedmejobs.Pojos.Article;
import nyc.c4q.huilin.feedmejobs.Pojos.BuzzArticles;
import nyc.c4q.huilin.feedmejobs.Pojos.BuzzResponse;
import nyc.c4q.huilin.feedmejobs.Pojos.EngadgetResponse;
import nyc.c4q.huilin.feedmejobs.network.BuzzService;
import nyc.c4q.huilin.feedmejobs.network.EngadgetService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<BuzzArticles> buzzArticlesList;
    private List<Object> completeList = new ArrayList<>();
    private static Retrofit retrofit;
    public static final String BASE_URL = "https://newsapi.org/";
    private static final String TAG = "MainActivity";


    private RecyclerView recyclerView;
    private List<Article> articleList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRetrofit();
        getEngadgetArticles();
        getBuzzfeedArticles();
        initRV(completeList);

    }

    private void initRV(List<Object> iHopeForLuck) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new DataAdapter(getApplicationContext(), iHopeForLuck));
    }

    public void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())  //for JSON to POJO else don't need
                .build();
    }

    public void getEngadgetArticles() {
        articleList = new ArrayList<>();

        EngadgetService service = retrofit.create(EngadgetService.class);
        Call<EngadgetResponse> call = service.getArticles();
        call.enqueue(new Callback<EngadgetResponse>() {
            @Override
            public void onResponse(Call<EngadgetResponse> call, Response<EngadgetResponse> response) {
                EngadgetResponse engadgetResponse = response.body();
                articleList = engadgetResponse.getArticles();
                completeList.addAll(articleList);
                Log.d(TAG, "There was a success" + response);
            }

            @Override
            public void onFailure(Call<EngadgetResponse> call, Throwable t) {

            }
        });

    }


    public void getBuzzfeedArticles() {
        Retrofit builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        BuzzService buzzService = builder.create(BuzzService.class);

        Call<BuzzResponse> buzzArticleCall = buzzService.listBuzzArticles();

        buzzArticleCall.enqueue(new Callback<BuzzResponse>() {
            @Override
            public void onResponse(Call<BuzzResponse> call, Response<BuzzResponse> response) {

                if (response.isSuccessful()) {
                    BuzzResponse buzzResp = response.body();
                    buzzArticlesList = buzzResp.getBuzzArticles();
                    completeList.addAll(buzzArticlesList);
                    Log.d(TAG, "Success!" + response);
                }

            }

            @Override
            public void onFailure(Call<BuzzResponse> call, Throwable t) {
                Log.d(TAG, "Failure: womp, womp" + t);
            }
        });

    }
}