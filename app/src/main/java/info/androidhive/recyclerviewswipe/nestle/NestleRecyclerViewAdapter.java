package info.androidhive.recyclerviewswipe.nestle;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.HashMap;
import java.util.List;

import info.androidhive.recyclerviewswipe.R;
import info.androidhive.recyclerviewswipe.list_androidhive_swipe_drag_and_drop.model.Item;

/**
 * Created by ha_hai on 9/19/2018.
 */
public class NestleRecyclerViewAdapter extends RecyclerView.Adapter<NestleRecyclerViewAdapter.NestleHolder> {
    private final Context context;
    private HashMap<Integer, List<Item>> items;
    private RecyclerView.RecycledViewPool mViewPool;

    public NestleRecyclerViewAdapter(HashMap<Integer, List<Item>> items, Context context) {
        this.items = items;
        this.context = context;

        mViewPool = new RecyclerView.RecycledViewPool();
    }

    @Override
    public NestleHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_nestle, parent, false);
        return new NestleHolder(v);
    }

    @Override
    public void onBindViewHolder(NestleHolder holder, int position) {
        List<Item> cardItems = items.get(position);
        //TODO Fill in your logic for binding the view.

//        SnapHelper snapHelper = new LinearSnapHelper();
//        snapHelper.attachToRecyclerView(holder.rcvCardView);

        SnapHelper snapHelperStart = new GravitySnapHelper(Gravity.START);
        snapHelperStart.attachToRecyclerView(holder.rcvCardView);

        holder.rcvCardView.setHasFixedSize(true);
        holder.rcvCardView.setRecycledViewPool(mViewPool);
        holder.rcvCardView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.rcvCardView.setItemAnimator(new DefaultItemAnimator());
        holder.rcvCardView.addItemDecoration(new NestleDivideHelper(context, 20));
        CardNestleAdapter adapter = new CardNestleAdapter(cardItems, context);
        holder.rcvCardView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public class NestleHolder extends RecyclerView.ViewHolder {

        RecyclerView rcvCardView;

        public NestleHolder(View itemView) {
            super(itemView);

            rcvCardView = itemView.findViewById(R.id.rcv_card_item);
        }
    }
}