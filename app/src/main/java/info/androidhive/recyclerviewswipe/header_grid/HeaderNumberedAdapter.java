package info.androidhive.recyclerviewswipe.header_grid;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import info.androidhive.recyclerviewswipe.R;

/**
 * Created by ha_hai on 9/21/2018.
 */
public class HeaderNumberedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private List<String> items;

    private static final int ITEM_VIEW_TYPE_HEADER = 0;
    private static final int ITEM_VIEW_TYPE_ITEM = 1;

    public HeaderNumberedAdapter(Context context, List<String> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_VIEW_TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_grid_header, parent, false);
            return new HeaderHolder(v);

        } else if (viewType == ITEM_VIEW_TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_grid_view, parent, false);
            return new ItemViewHolder(v);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String item = items.get(position);
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).txtGridView.setText(item);
        } else if (holder instanceof HeaderHolder) {
            ((HeaderHolder) holder).txtHeader.setText(item);
        }
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position).length() == 1) {
            return ITEM_VIEW_TYPE_HEADER;
        } else {
            return ITEM_VIEW_TYPE_ITEM;
        }
    }

    public boolean isHeader(int position) {
        return items.get(position).length() == 1;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView txtGridView;

        public ItemViewHolder(View itemView) {
            super(itemView);

            txtGridView = itemView.findViewById(R.id.txt_grid_view);
        }
    }

    public class HeaderHolder extends RecyclerView.ViewHolder {

        TextView txtHeader;

        public HeaderHolder(View itemView) {
            super(itemView);

            txtHeader = itemView.findViewById(R.id.txtGridHeader);
        }
    }

}