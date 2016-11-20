package nyc.c4q.huilin.feedmejobs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.huilin.feedmejobs.NoteFeature.NoteItem;
import nyc.c4q.huilin.feedmejobs.NoteFeature.NotesAdapter;
import nyc.c4q.huilin.feedmejobs.NoteFeature.NotesDataSource;
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

import static nyc.c4q.huilin.feedmejobs.NoteFeature.NoteItem.getNew;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1111;
    private RecyclerView recyclerView;
    private NoteItem note;
    private NotesDataSource notesDataSource;
//    private NotesAdapter notesAdapter;
    private DataAdapter dataAdapter;
    private static final String ENGADGET_KEY = BuildConfig.ENGADGET_KEY;
    private static final String BUZZFEED_KEY = BuildConfig.BUZZFEED_KEY;
    private List<BuzzArticles> buzzArticlesList;
    private List<Object> completeList = new ArrayList<>();
    private static Retrofit retrofit;
    public static final String BASE_URL = "https://newsapi.org/";
    private static final String TAG = "MainActivity";
    private List<Article> articleList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initNotes();
//        initRV();
        initRetrofit();
        getEngadgetArticles();
        getBuzzfeedArticles();
        dataAdapter.setItems(completeList);
        initRV();

    }

    private void initRV() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(dataAdapter);
//        recyclerView.setAdapter(notesAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_create) {
            createNote();
        }
        return super.onOptionsItemSelected(item);
    }

    private void createNote() {
        NoteItem note = getNew();
        Intent intent = new Intent(this, NoteEditorActivity.class);
        intent.putExtra(NoteEditorActivity.KEY, note.getKey());
        intent.putExtra(NoteEditorActivity.TEXT, note.getText());
        startActivityForResult(intent, REQUEST_CODE);
    }

//    // put this in an onclick
//    NoteItem note = NoteItem.get(position);
//    Intent intent = new Intent(this, NoteEditorActivity.class);
//    intent.putExtra("key", note.getKey());
//    intent.putExtra("text", note.getText());
//    startActivityForResult(intent, REQUEST_CODE);

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            note = new NoteItem();
            note.setKey(data.getStringExtra(NoteEditorActivity.KEY));
            note.setText(data.getStringExtra(NoteEditorActivity.TEXT));
            notesDataSource.add(note);
            notesDataSource.viewAll();
            dataAdapter.updateAdapter(buzzArticlesList, articleList);

        }
    }

    public void initNotes() {
        notesDataSource = new NotesDataSource(getApplicationContext());
        completeList.addAll(notesDataSource.findAll());
        dataAdapter = new DataAdapter(getApplicationContext(), notesDataSource);
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
        Call<EngadgetResponse> call = service.getArticles(ENGADGET_KEY);
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

        Call<BuzzResponse> buzzArticleCall = buzzService.listBuzzArticles(BUZZFEED_KEY);

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