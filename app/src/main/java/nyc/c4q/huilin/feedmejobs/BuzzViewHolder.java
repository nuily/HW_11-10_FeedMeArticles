package nyc.c4q.huilin.feedmejobs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import nyc.c4q.huilin.feedmejobs.Pojos.BuzzArticles;

/**
 * Created by huilin on 10/30/16.
 */

public class BuzzViewHolder extends RecyclerView.ViewHolder {

    private final View view;
    private final ImageView thumbnail;
    private final TextView buzzAuthor;
    private final TextView buzzTitle;
    private final TextView buzzDescription;
    private final TextView buzzUrl;
    private final TextView buzzPubDate;


    public BuzzViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        view = itemView;
        thumbnail = (ImageView) view.findViewById(R.id.article_thumbnail);
        buzzAuthor = (TextView) view.findViewById(R.id.author_name);
        buzzTitle = (TextView) view.findViewById(R.id.article_title);
        buzzDescription = (TextView) view.findViewById(R.id.article_description);
        buzzUrl = (TextView) view.findViewById(R.id.article_link);
        buzzPubDate = (TextView) view.findViewById(R.id.article_date);
    }


    private static View inflateView(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.buzz_layout, parent, false);
    }


    public void bind(BuzzArticles buzzArticles) {
        buzzAuthor.setText(buzzArticles.getAuthor());
        buzzTitle.setText(buzzArticles.getTitle());
        buzzDescription.setText(buzzArticles.getDescription());
        buzzUrl.setText(buzzArticles.getUrl());
        buzzPubDate.setText(buzzArticles.getPublishedAt());
    }



}
