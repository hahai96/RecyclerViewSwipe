package info.androidhive.recyclerviewswipe.list_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import info.androidhive.recyclerviewswipe.R;
import info.androidhive.recyclerviewswipe.list_demo.adapter.RecyclerListAdapter;
import info.androidhive.recyclerviewswipe.list_demo.utils.ListItemDecorationHelper;
import info.androidhive.recyclerviewswipe.list_demo.utils.ListItemTouchListListener;
import info.androidhive.recyclerviewswipe.list_demo.utils.OnDragListener;
import info.androidhive.recyclerviewswipe.list_demo.utils.ListRecyclerItemTouchHelper;

public class ListActivity extends AppCompatActivity implements ListItemTouchListListener, OnDragListener {

    ArrayList<String> items;
    RecyclerListAdapter adapter;
    RecyclerView recycler_list;
    ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        items = new ArrayList<>();
        items.addAll(Arrays.asList(getResources().getStringArray(R.array.list_item)));

        recycler_list = findViewById(R.id.recycler_list);

        recycler_list.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler_list.setLayoutManager(layoutManager);
        recycler_list.setItemAnimator(new DefaultItemAnimator());

        recycler_list.addItemDecoration(new ListItemDecorationHelper(this, 2));

        ItemTouchHelper.Callback callback = new ListRecyclerItemTouchHelper(this);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recycler_list);

        adapter = new RecyclerListAdapter(items, this, this);
        recycler_list.setAdapter(adapter);
    }

    @Override
    public void onSwipe(int position, int direction) {
        if (direction == ItemTouchHelper.LEFT) {
            items.remove(position);
            adapter.notifyItemRemoved(position);
        }
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
