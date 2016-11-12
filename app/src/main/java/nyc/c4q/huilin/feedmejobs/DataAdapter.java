package nyc.c4q.huilin.feedmejobs;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

/**
 * Created by huilin on 10/30/16.
 */
public class DataAdapter extends RecyclerView.Adapter {

    private List<Data> drinks = Arrays.asList(
            new Data("Dark and Stormy", "Rum"),
            new Data("Pina Colada", "Bacardi"),
            new Data("Amaretto Sour", "Almond Liquer"),
            new Data("Cocoloso", "Coconut Ciroc"),
            new Data("Tequila Sunrise", "Tequila"),
            new Data("Screwdriver", "Vodka and Orange juice"),
            new Data("Long Island Iced tea", "Rum"),
            new Data("Scotch and Coconut water", "Johnny Walker Whiskey"),
            new Data("Whisky Ginger", "whisky"),
            new Data("Incredible Hulk", "Red bull and Hypnotiq"),
            new Data("Coquito", "Bacardi"),
            new Data("Jungle Juice", "lots o'stuff"),
            new Data("Lychee Soju", "Soju"),
            new Data("Lychee Martini", "Lychee, Gin")
    );


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataViewHolder(parent);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DataViewHolder dataViewHolder = (DataViewHolder) holder;
        Data cocktails = drinks.get(position);
        dataViewHolder.bind(cocktails);
    }


    @Override
    public int getItemCount() {
        return drinks.size();
    }
}
