package nyc.c4q.huilin.feedmejobs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by huilin on 10/30/16.
 */

public class DataViewHolder extends RecyclerView.ViewHolder {

    private final View view;
    private final TextView drinkName;
    private final TextView alcoholName;


    // using ViewGroup (extends View) because it's passed in through the method calling this method
    public DataViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        view = itemView;
        drinkName = (TextView) view.findViewById(R.id.drink_name);
        alcoholName = (TextView) view.findViewById(R.id.drink_alco);
    }


    private static View inflateView(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.view_layout, parent, false);
    }


    public void bind(Data data) {
        drinkName.setText(data.getDrinkName());
        alcoholName.setText(data.getAlcoholType());
    }

    public TextView getDrinkName() {
        return drinkName;
    }

    public TextView getAlcoholName() {
        return alcoholName;
    }
}
