package nyc.c4q.huilin.feedmejobs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nyc.c4q.huilin.feedmejobs.pojos.Article;

/**
 * Created by huilin on 10/30/16.
 */

public class DataViewHolder extends RecyclerView.ViewHolder {

    private View view;
    private TextView articleName;
    private TextView authorText;
    private TextView articleText;
    public ImageView articleImage;


    public DataViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        view = itemView;
        articleName = (TextView) view.findViewById(R.id.article_title_tv);
        authorText = (TextView) view.findViewById(R.id.author_tv);
        articleText = (TextView) view.findViewById(R.id.article_text_tv);
        articleImage = (ImageView) view.findViewById(R.id.background_image);
    }


    private static View inflateView(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.view_layout, parent, false);
    }


    public void bind(Article data) {
        articleName.setText(data.getTitle());
        authorText.setText(data.getAuthor());
//        articleText.setText(data.getDescription());
        Picasso.with(view.getContext()).load(data.getUrlToImage()).into(articleImage);
    }

    public TextView getarticleName() {
        return articleName;
    }

    public TextView getAlcoholName() {
        return articleText;
    }
}
