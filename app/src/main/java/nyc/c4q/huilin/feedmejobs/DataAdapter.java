package nyc.c4q.huilin.feedmejobs;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.huilin.feedmejobs.Pojos.BuzzArticles;
import nyc.c4q.huilin.feedmejobs.Pojos.Article;

/**
 * Created by huilin on 10/30/16.
 */
public class DataAdapter extends RecyclerView.Adapter {

//    Context context;
    private List<BuzzArticles> buzzArticles;

    public DataAdapter(Context context) {
        this.context = context;
        this.buzzArticles = new ArrayList<>();
    }

//    public DataAdapter() {
//        this.buzzArticles = new ArrayList<>();
//    }

    public void setBuzzArticles(List<BuzzArticles> buzzArticles) {
        this.buzzArticles = buzzArticles;
        notifyDataSetChanged();
    }
    private List<Article> articleList;
    private Context context;

    public DataAdapter(Context context, List<Article> articleList){
        this.articleList = articleList;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BuzzViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        BuzzViewHolder buzzViewHolder = (BuzzViewHolder) holder;
//        BuzzArticles buzzArticle = buzzArticles.get(position);
//        buzzViewHolder.bind(buzzArticle);
        DataViewHolder dataViewHolder = (DataViewHolder) holder;
        Article article = articleList.get(position);
        dataViewHolder.bind(article);
    }


    @Override
    public int getItemCount() {
//        return buzzArticles.size();
        return articleList.size();
    }
}
