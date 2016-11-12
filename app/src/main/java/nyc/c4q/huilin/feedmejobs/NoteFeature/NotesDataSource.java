package nyc.c4q.huilin.feedmejobs.NoteFeature;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by huilin on 11/9/16.
 */

public class NotesDataSource {


    private static final String PREF_KEY = "Notebook";
    private SharedPreferences notesPref;

    public NotesDataSource(Context context) {
        notesPref = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        NoteItem note = NoteItem.getNew();

    }

    public List<NoteItem> findAll() {

        Map<String, ?> notesMap = notesPref.getAll();

        // Map is unordered so next step is to sort dat by the keys (new first)

        SortedSet<String> keys = new TreeSet<String>(notesMap.keySet());
        // returns a listing of all the keys for all the noteList in the sharedPrefs
        // treeset automatically returns data in a sorted set

        List<NoteItem> noteList = new ArrayList<NoteItem>();

        // loop through the keys and add associated noteList to the list object
        for (String key : keys) {
            NoteItem note = new NoteItem();
            note.setKey(key);
            note.setText((String) notesMap.get(key));
            noteList.add(note);

        }

        return noteList;

    }

    public void update(NoteItem note) {
        SharedPreferences.Editor editor = notesPref.edit();
        editor.putString(note.getKey(), note.getText());
        editor.apply();

    }

    public void remove(NoteItem note) {
        if (notesPref.contains(note.getKey())) {
            SharedPreferences.Editor editor = notesPref.edit();
            editor.remove(note.getKey());
            editor.apply();
        }
    }
}
