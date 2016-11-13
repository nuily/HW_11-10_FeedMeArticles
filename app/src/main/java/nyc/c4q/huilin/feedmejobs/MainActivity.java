package nyc.c4q.huilin.feedmejobs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.huilin.feedmejobs.Pojos.BuzzArticles;
import nyc.c4q.huilin.feedmejobs.Pojos.BuzzResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private static Retrofit retrofit;
    private static Retrofit.Builder builder;

    private List<BuzzArticles> buzzArticlesList;


    private static final String buzzUrl = "https://newsapi.org/";
    private static final String TAG = "Main Activity";
    private final String SOURCE = "buzzfeed";
    private final String SORT_BY = "latest";
    private final String API_KEY = BuildConfig.API_KEY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buzzArticlesList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        getBuzzfeedArticles();
//        llm = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(llm);
    }


    public void getBuzzfeedArticles() {
        Retrofit builder = new Retrofit.Builder()
                .baseUrl(buzzUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();
        BuzzService buzzService = builder.create(BuzzService.class);

        Call<BuzzResponse> buzzArticleCall = buzzService.listBuzzArticles();

//        Call<BuzzResponse> buzzArticleCall = buzzService.listBuzzArticles(SOURCE, SORT_BY, API_KEY);
        buzzArticleCall.enqueue(new Callback<BuzzResponse>() {
            @Override
            public void onResponse(Call<BuzzResponse> call, Response<BuzzResponse> response) {
                BuzzResponse buzzResp = response.body();
                buzzArticlesList = buzzResp.getBuzzArticles();
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(new DataAdapter(getApplicationContext(),buzzArticlesList));
                Log.i("LIST:" ,buzzArticlesList.size()+"");
                Log.d(TAG, "Success!" + response);
            }

            @Override
            public void onFailure(Call<BuzzResponse> call, Throwable t) {
                Log.d(TAG, "Failure: womp, womp" + t);
            }
        });

    }
}