package nyc.c4q.huilin.feedmejobs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.huilin.feedmejobs.network.EngadgetService;
import nyc.c4q.huilin.feedmejobs.pojos.Article;
import nyc.c4q.huilin.feedmejobs.pojos.EngadgetResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://newsapi.org/";
    private static final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private List<Article> articleList;

    //I believe Adapter should go here.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        articleList = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL) //base url
                .addConverterFactory(GsonConverterFactory.create())  //for JSON to POJO else don't need
                .build();


        EngadgetService service = retrofit.create(EngadgetService.class);
        Call<EngadgetResponse> call = service.getArticles();
        call.enqueue(new Callback<EngadgetResponse>() {
            @Override
            public void onResponse(Call<EngadgetResponse> call, Response<EngadgetResponse> response) {
                EngadgetResponse engadgetResponse = response.body();
                articleList = engadgetResponse.getArticles();
                initRV(articleList);
                Log.d(TAG, "There was a success" + response);
                Toast.makeText(MainActivity.this, "THIS WORKS!!!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<EngadgetResponse> call, Throwable t) {

            }
        });



    }

    private void initRV(List<Article> articleList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new DataAdapter(getApplicationContext(),articleList));
    }
}
