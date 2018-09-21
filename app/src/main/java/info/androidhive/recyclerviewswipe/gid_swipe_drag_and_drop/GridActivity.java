package info.androidhive.recyclerviewswipe.gid_swipe_drag_and_drop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import info.androidhive.recyclerviewswipe.R;
import info.androidhive.recyclerviewswipe.gid_swipe_drag_and_drop.adapter.RecyclerGridAdapter;
import info.androidhive.recyclerviewswipe.gid_swipe_drag_and_drop.utils.GridItemTouchListListener;
import info.androidhive.recyclerviewswipe.gid_swipe_drag_and_drop.utils.GridRecyclerItemTouchHelper;
import info.androidhive.recyclerviewswipe.gid_swipe_drag_and_drop.utils.OnDragGridListener;

public class GridActivity extends AppCompatActivity implements GridItemTouchListListener, OnDragGridListener {

    ArrayList<String> items;
    RecyclerGridAdapter adapter;
    RecyclerView recycler_list;
    ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        items = new ArrayList<>();
        items.addAll(Arrays.asList(getResources().getStringArray(R.array.list_item)));

        recycler_list = findViewById(R.id.recycler_grid);

        recycler_list.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recycler_list.setLayoutManager(layoutManager);
        recycler_list.setItemAnimator(new DefaultItemAnimator());

        ItemTouchHelper.Callback callback = new GridRecyclerItemTouchHelper(this);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recycler_list);

        adapter = new RecyclerGridAdapter(items, this, this);
        recycler_list.setAdapter(adapter);
    }

    @Override
    public void onSwipe(int position, int direction) {

    }

    @Override
    public void onMove(int fromPosition, int toPosition) {
        Collections.swap(items, fromPosition, toPosition);
        adapter.notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder holder) {
        itemTouchHelper.startDrag(holder);
    }
}
