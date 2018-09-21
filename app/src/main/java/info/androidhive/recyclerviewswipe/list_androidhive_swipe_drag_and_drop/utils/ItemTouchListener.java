package info.androidhive.recyclerviewswipe.list_androidhive_swipe_drag_and_drop.utils;

import android.support.v7.widget.RecyclerView;

/**
 * Created by ha_hai on 5/20/2018.
 */

public interface ItemTouchListener {

    void onItemMove(int fromPosition, int toPosition);

    void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
}
