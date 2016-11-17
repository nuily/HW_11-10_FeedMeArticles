package nyc.c4q.huilin.feedmejobs;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

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

    public DataAdapter(Context context, List<Object> items) {
        this.items = items;
        this.context = context;
    }


    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof BuzzArticles) {
            return BUZZFEED;
        } else if (items.get(position) instanceof Article) {
            return ENGADGET;
        }
        return -1;
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
        }

    }


    @Override
    public int getItemCount() {
        return items.size();
    }
}
