package nyc.c4q.huilin.feedmejobs;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import nyc.c4q.huilin.feedmejobs.NoteFeature.NoteItem;
import nyc.c4q.huilin.feedmejobs.NoteFeature.NotesDataSource;
import nyc.c4q.huilin.feedmejobs.NoteFeature.NotesViewHolder;
import nyc.c4q.huilin.feedmejobs.Pojos.Article;
import nyc.c4q.huilin.feedmejobs.Pojos.BuzzArticles;

/**
 * Created by huilin on 10/30/16.
 */
public class DataAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Object> items;
    private final int BUZZFEED = 0;
    private final int ENGADGET = 1;
    private final int SHAREDPREF = 2;
    private NotesDataSource notesDataSource;

//     List<Object> items
    public DataAdapter(Context context, NotesDataSource notesDataSource) {
        this.notesDataSource = notesDataSource;
        this.context = context;

    }

    public void setItems(List<Object> items) {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof BuzzArticles) {
            return BUZZFEED;
        } else if (items.get(position) instanceof Article) {
            return ENGADGET;
        } else {
            return SHAREDPREF;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case BUZZFEED:
                ViewGroup v1 = (ViewGroup) layoutInflater.inflate(R.layout.buzz_layout, parent, false);
                viewHolder = new BuzzViewHolder(v1);
                break;
            case ENGADGET:
                ViewGroup v2 = (ViewGroup) layoutInflater.inflate(R.layout.view_layout, parent, false);
                viewHolder = new DataViewHolder(v2);
                break;
            case SHAREDPREF:
                ViewGroup v3 = (ViewGroup) layoutInflater.inflate(R.layout.note_layout, parent, false);
                viewHolder = new NotesViewHolder(v3);
                break;
        }
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case BUZZFEED:
                BuzzViewHolder buzzViewHolder = (BuzzViewHolder) holder;
                BuzzArticles buzzArticle = (BuzzArticles) items.get(position);
                buzzViewHolder.bind(buzzArticle);
                break;
            case ENGADGET:
                DataViewHolder dataViewHolder = (DataViewHolder) holder;
                Article article = (Article) items.get(position);
                dataViewHolder.bind(article);
                break;
            case SHAREDPREF:
                NotesViewHolder notesViewHolder = (NotesViewHolder) holder;
                NoteItem noteItem =  (NoteItem) items.get(position);
                notesViewHolder.bind(noteItem);
        }

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateAdapter(List<BuzzArticles> buzzArticlesList, List<Article> articleList) {
        items.clear();
        items.addAll((notesDataSource.findAll()));
        items.addAll(buzzArticlesList);
        items.addAll(articleList);
        notifyDataSetChanged();
    }
}
