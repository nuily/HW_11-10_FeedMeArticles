package nyc.c4q.huilin.feedmejobs.NoteFeature;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import nyc.c4q.huilin.feedmejobs.R;

/**
 * Created by huilin on 11/8/16.
 */

public class NotesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private final View view;
    private final TextView title;
    private final TextView txtDisplay;
    private final TextView date;
    private final Button save;


    // using ViewGroup (extends View) because it's passed in through the method calling this method
    public NotesViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        view = itemView;
        title = (TextView) view.findViewById(R.id.note_title);
        txtDisplay = (TextView) view.findViewById(R.id.note_display);
        date = (TextView) view.findViewById(R.id.dateTV);
        save = (Button) view.findViewById(R.id.save_notes);
        itemView.setOnClickListener(this);
    }


    private static View inflateView(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.note_layout, parent, false);
    }


    public void bind(NoteItem noteItem) {
        txtDisplay.setText(noteItem.getText());
        date.setText(noteItem.getKey());

    }

    @Override
    public void onClick(View view) {
        // Gets item position
        int position = getAdapterPosition();

        //Check if an item was deleted, or if user clicked it before UI removed it
        if (position != RecyclerView.NO_POSITION) {
            NoteItem noteItem = new NotesAdapter().getNoteList().get(position);
        }
    }
}
