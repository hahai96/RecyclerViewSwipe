package info.androidhive.recyclerviewswipe.grid_demo.utils;

/**
 * Created by ha_hai on 9/13/2018.
 */

public interface GridItemTouchListListener {

    void onSwipe(int position, int direction);

    void onMove(int fromPosition, int toPosition);
}
