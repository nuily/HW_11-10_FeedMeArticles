package nyc.c4q.huilin.feedmejobs.NoteFeature;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by huilin on 11/9/16.
 */

public class NotesDataSource {


    public static final String PREF_KEY = "Notebook";
    private SharedPreferences notesPref;
    SharedPreferences.Editor editor;
    private List<NoteItem> noteList; // why did I move this outside of find all?
    private NoteItem note;


    public NotesDataSource(Context context) {
        notesPref = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        editor = notesPref.edit();
        noteList = new ArrayList<>();
    }

    public List<NoteItem> findAll() {

        Map<String, ?> notesMap = notesPref.getAll();

//            // Map is unordered so next step is to sort dat by the keys (new first)
//            SortedSet<String> keys = new TreeSet<String>(notesMap.keySet());
//            // returns a listing of all the keys for all the noteList in the sharedPrefs
//            // treeset automatically returns data in a sorted set
//
//            // loop through the keys and add associated noteList to the list object
//            for (String key : keys) {
//                NoteItem note = new NoteItem();
//                note.setKey(key);
//                note.setText((String) notesMap.get(key));
//                noteList.add(note);
        for (Map.Entry<String, ?> entry : notesMap.entrySet()) {
            NoteItem note = new NoteItem();
            note.setKey(entry.getKey());
            note.setText(entry.getValue().toString());
            noteList.add(note);

        }
        return noteList;
    }

    public NoteItem getNote() {
        return note;
    }

    public void add(NoteItem note) {

        editor.putString(note.getKey(), note.getText());
        editor.apply();

    }

    public void remove(NoteItem note) {
        if (notesPref.contains(note.getKey())) {
            editor.remove(note.getKey());
            editor.apply();

        }
    }

    public void viewAll() {
        Map<String, ?> allEntries = notesPref.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
        }
    }
}
