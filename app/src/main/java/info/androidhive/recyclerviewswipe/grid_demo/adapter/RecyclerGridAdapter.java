package info.androidhive.recyclerviewswipe.grid_demo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import info.androidhive.recyclerviewswipe.R;
import info.androidhive.recyclerviewswipe.grid_demo.utils.GridItemTouchHelperViewHolder;
import info.androidhive.recyclerviewswipe.grid_demo.utils.OnDragGridListener;

/**
 * Created by ha_hai on 9/13/2018.
 */
public class RecyclerGridAdapter extends RecyclerView.Adapter<RecyclerGridAdapter.MyHolder> {
    private Context context;
    private ArrayList<String> items;
    private OnDragGridListener dragListener;

    public RecyclerGridAdapter(ArrayList<String> items, Context context, OnDragGridListener dragListener) {
        this.items = items;
        this.context = context;
        this.dragListener = dragListener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent,
                                       int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        String item = items.get(position);

        holder.txtList.setText(item);
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder implements GridItemTouchHelperViewHolder {

        TextView txtList;
        ImageView imgPressList;

        public MyHolder(View itemView) {
            super(itemView);

            txtList = itemView.findViewById(R.id.txtNameList);
            imgPressList = itemView.findViewById(R.id.imgPressList);

           imgPressList.setOnTouchListener(new View.OnTouchListener() {
               @Override
               public boolean onTouch(View view, MotionEvent motionEvent) {
                   if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                       dragListener.onStartDrag(MyHolder.this);
                   }
                   return false;
               }
           });
        }

        @Override
        public void onSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onClear() {
            itemView.setBackgroundColor(0);
        }
    }
}