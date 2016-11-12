package nyc.c4q.huilin.feedmejobs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

import nyc.c4q.huilin.feedmejobs.Pojos.BuzzArticles;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import nyc.c4q.huilin.feedmejobs.Pojos.BuzzResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {

    BuzzService buzzService;

    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private static Retrofit retrofit;
    private static Retrofit.Builder builder;

    private static final String buzzUrl = "https://newsapi.org/";
    private static final String TAG = "Main Activity";
    private final String SOURCE = "?source=buzzfeed";
    private final String SORT_BY = "&sortBy=latest";
    private final String API_KEY = BuildConfig.API_KEY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRV();

        builder = new Retrofit.Builder()
                .baseUrl(buzzUrl)
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();
        buzzService = retrofit.create(BuzzService.class);

        Call<BuzzResponse> buzzArticleCall = buzzService.listBuzzArticles(SOURCE, SORT_BY, API_KEY);
        buzzArticleCall.enqueue(new Callback<BuzzResponse>() {
            @Override
            public void onResponse(Call<BuzzResponse> call, Response<BuzzResponse> response) {
                List<BuzzResponse> buzzResp = (List<BuzzResponse>) response.body();
              //  List<BuzzArticles> buzzArticles = buzzResp.getBuzzArticles();
               // initRV();
//                recyclerView.setAdapter(adapter);
//               adapter.setBuzzArticles();
//               adapter.notifyDataSetChanged();
                Log.d(TAG, "Success!" + response);
            }

            @Override
            public void onFailure(Call<BuzzResponse> call, Throwable t) {
                Log.d(TAG, "Failure: womp, womp" + t);
            }
        });
    }


    private void initRV() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new DataAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}

