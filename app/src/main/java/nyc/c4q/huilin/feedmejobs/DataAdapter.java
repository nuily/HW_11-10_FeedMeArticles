package nyc.c4q.huilin.feedmejobs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nyc.c4q.huilin.feedmejobs.Pojos.BuzzArticles;

/**
 * Created by huilin on 10/30/16.
 */
public class DataAdapter extends RecyclerView.Adapter {

    private List<BuzzArticles> buzzArticles;

//    public DataAdapter(List<BuzzArticles> buzzArticles) {
//        this.buzzArticles = buzzArticles;
//    }

    public DataAdapter() {
        this.buzzArticles = new ArrayList<>();
    }

    public void setBuzzArticles(List<BuzzArticles> buzzArticles) {
        this.buzzArticles = buzzArticles;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BuzzViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BuzzViewHolder buzzViewHolder = (BuzzViewHolder) holder;
        BuzzArticles buzzArticle = buzzArticles.get(position);
        buzzViewHolder.bind(buzzArticle);
    }


    @Override
    public int getItemCount() {
        return buzzArticles.size();
    }
}
