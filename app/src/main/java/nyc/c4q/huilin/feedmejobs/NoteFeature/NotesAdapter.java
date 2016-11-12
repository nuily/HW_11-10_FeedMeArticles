package nyc.c4q.huilin.feedmejobs.NoteFeature;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by huilin on 11/10/16.
 */

public class NotesAdapter extends RecyclerView.Adapter {

    Context context;
    private NotesDataSource notesDataSource;
    public List<NoteItem> noteList;

    public NotesAdapter() {
    }

    public NotesAdapter(Context context) {
        this.context = context;
        notesDataSource = new NotesDataSource(context);
        noteList = notesDataSource.findAll();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NotesViewHolder(parent);

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NotesViewHolder notesViewHolder = (NotesViewHolder) holder;
        NoteItem noteItem = noteList.get(position);
        notesViewHolder.bind(noteItem);
    }


    @Override
    public int getItemCount() {
        return noteList.size();
    }


    public List<NoteItem> getNoteList() {
        return noteList;
    }
}
