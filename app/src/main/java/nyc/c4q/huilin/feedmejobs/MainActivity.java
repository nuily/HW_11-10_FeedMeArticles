package nyc.c4q.huilin.feedmejobs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import nyc.c4q.huilin.feedmejobs.NoteFeature.NoteItem;
import nyc.c4q.huilin.feedmejobs.NoteFeature.NotesAdapter;
import nyc.c4q.huilin.feedmejobs.NoteFeature.NotesDataSource;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1111;
    private RecyclerView recyclerView;
    private NoteItem note;
    private NotesDataSource notesDataSource;
    private NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesDataSource = new NotesDataSource(getApplicationContext());
        notesAdapter = new NotesAdapter(getApplicationContext());
        initRV();


//        NoteItem note = notes.get(0);
//        note.setText("Updated!");
//        notesDataSource.update(note);
//
//        notes = notesDataSource.findAll();
//        note = notes.get(0);
//        Log.i("NOTES", note.getKey() + ": " + note.getText());


    }

    private void initRV() {
//        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        recyclerView.setAdapter(new DataAdapter());

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(notesAdapter);
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
        NoteItem note = NoteItem.getNew();
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
            note.setText(data.getStringExtra(NoteEditorActivity.KEY));
            note.setText(data.getStringExtra(NoteEditorActivity.TEXT));
            notesDataSource.update(note);
            notesAdapter.notifyDataSetChanged();
            notesDataSource.viewAll();

        }
    }
}
