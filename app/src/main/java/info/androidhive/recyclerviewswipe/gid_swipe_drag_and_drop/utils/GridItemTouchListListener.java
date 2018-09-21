package info.androidhive.recyclerviewswipe.gid_swipe_drag_and_drop.utils;

/**
 * Created by ha_hai on 9/13/2018.
 */

public interface GridItemTouchListListener {

    void onSwipe(int position, int direction);

    void onMove(int fromPosition, int toPosition);
}
