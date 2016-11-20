package nyc.c4q.huilin.feedmejobs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;

import nyc.c4q.huilin.feedmejobs.NoteFeature.NoteItem;

/**
 * Created by huilin on 11/11/16.
 */
public class NoteEditorActivity extends AppCompatActivity {

    public static final String KEY = "key";
    public static final String TEXT = "text";
    private NoteItem note;
    EditText editNote;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_note);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = this.getIntent();
        note = new NoteItem();
        note.setKey(intent.getStringExtra(KEY));
        note.setText(intent.getStringExtra(TEXT));

        editNote = (EditText) findViewById(R.id.note_writespace);
        editNote.setText(note.getText());

        // Place cursor at the end of the exiting text value
        editNote.setSelection(note.getText().length());
    }

    private void saveAndFinish() {
        editNote = (EditText) findViewById(R.id.note_writespace);
        String noteText = editNote.getText().toString();
//        note.setText(editNote.getText().toString());
//        note.setKey(note.getKey());

        Intent intent = new Intent();
        intent.putExtra(KEY, note.getKey());
        intent.putExtra(TEXT, noteText);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            saveAndFinish();
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        saveAndFinish();
    }
}
