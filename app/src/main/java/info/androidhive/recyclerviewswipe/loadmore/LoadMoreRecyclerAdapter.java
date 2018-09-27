package info.androidhive.recyclerviewswipe.loadmore;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.recyclerviewswipe.R;

/**
 * Created by ha_hai on 9/20/2018.
 */
public class LoadMoreRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private List<App> items;

    public static final int TYPE_PROGRESS = 0;
    public static final int TYPE_VIEW = 1;

    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private OnLoadMoreListener onLoadMoreListener;


    public LoadMoreRecyclerAdapter(Context context, RecyclerView recyclerView) {
        this.context = context;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_VIEW) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_load_more, parent, false);
            return new LoadMoreHolder(v);
        } else if (viewType == TYPE_PROGRESS) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_load_more_progress, parent, false);
            return new ProgressHolder(v);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof LoadMoreHolder) {
            App item = items.get(position);

            ((LoadMoreHolder) holder).imgLoadMore.setImageDrawable(item.getIcon());
            ((LoadMoreHolder) holder).txtName.setText(item.getName());
            ((LoadMoreHolder) holder).txtPackageName.setText(item.getPackageName());
        } else if (holder instanceof ProgressHolder) {
            ((ProgressHolder) holder).mPrLoadMore.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public void setApps(ArrayList<App> items) {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) == null) {
            return TYPE_PROGRESS;
        } else {
            return TYPE_VIEW;
        }
    }

    public void setLoaded() {
        isLoading = false;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    public class LoadMoreHolder extends RecyclerView.ViewHolder {

        ImageView imgLoadMore;
        TextView txtName, txtPackageName;

        public LoadMoreHolder(View itemView) {
            super(itemView);

            imgLoadMore = itemView.findViewById(R.id.imgLoadMoreIcon);
            txtName = itemView.findViewById(R.id.txtLoadMoreName);
            txtPackageName = itemView.findViewById(R.id.txtLoadMorePackageName);
        }
    }

    public class ProgressHolder extends RecyclerView.ViewHolder {

        private ProgressBar mPrLoadMore;

        public ProgressHolder(View itemView) {
            super(itemView);

           mPrLoadMore = itemView.findViewById(R.id.prLoadMore);
        }
    }
}