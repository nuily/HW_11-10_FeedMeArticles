package nyc.c4q.huilin.feedmejobs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import nyc.c4q.huilin.feedmejobs.pojos.Article;

/**
 * Created by huilin on 10/30/16.
 */
public class DataAdapter extends RecyclerView.Adapter {

    private List<Article> articleList;
    private Context context;

    public DataAdapter(Context context, List<Article> articleList){
        this.articleList = articleList;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DataViewHolder dataViewHolder = (DataViewHolder) holder;
        Article article = articleList.get(position);
        dataViewHolder.bind(article);
    }


    @Override
    public int getItemCount() {
        return articleList.size();
    }
}
