package nyc.c4q.huilin.feedmejobs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import nyc.c4q.huilin.feedmejobs.NoteFeature.NoteItem;

/**
 * Created by huilin on 11/11/16.
 */
public class NoteEditorActivity extends AppCompatActivity {

    private NoteItem note;
    EditText editNote;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_note);
        getActionBar();

        Intent intent = this.getIntent();
        note = new NoteItem();
        note.setKey(intent.getStringExtra("key"));
        note.setText(intent.getStringExtra("text"));

        editNote = (EditText) findViewById(R.id.note_writespace);
        editNote.setText(note.getText());

        // Place cursor at the end of the exiting text value
        editNote.setSelection(note.getText().length());
    }

    private void saveAndFinish() {
        editNote = (EditText) findViewById(R.id.note_writespace);
        String noteText = editNote.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("key", note.getKey());
        intent.putExtra("text", noteText);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
