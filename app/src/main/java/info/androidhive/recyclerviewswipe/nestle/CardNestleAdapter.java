package info.androidhive.recyclerviewswipe.nestle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import info.androidhive.recyclerviewswipe.R;
import info.androidhive.recyclerviewswipe.list_androidhive_swipe_drag_and_drop.model.Item;

/**
 * Created by ha_hai on 9/19/2018.
 */
public class CardNestleAdapter extends RecyclerView.Adapter<CardNestleAdapter.CardHolder> {
    private final Context context;
    private List<Item> items;

    public CardNestleAdapter(List<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_nestle, parent, false);
        return new CardHolder(v);
    }

    @Override
    public void onBindViewHolder(CardHolder holder, int position) {
        Item item = items.get(position);

        Glide.with(context)
                .load(item.getThumbnail())
                .into(holder.imgCard);

        holder.txtName.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public class CardHolder extends RecyclerView.ViewHolder {

        ImageView imgCard;
        TextView txtName;

        public CardHolder(View itemView) {
            super(itemView);

            imgCard = itemView.findViewById(R.id.img_nestle_icon);
            txtName = itemView.findViewById(R.id.txt_nestle_name);
        }
    }
}