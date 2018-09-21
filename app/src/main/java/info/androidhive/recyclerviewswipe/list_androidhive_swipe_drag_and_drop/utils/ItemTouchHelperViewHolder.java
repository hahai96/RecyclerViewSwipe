package info.androidhive.recyclerviewswipe.list_androidhive_swipe_drag_and_drop.utils;

/**
 * Created by ha_hai on 5/20/2018.
 */

public interface ItemTouchHelperViewHolder {
    /**
     * Called when the first registers an item as being moved or swiped.
     * Implementations should update the item view to indicate it's active state.
     */
    void onItemSelected();


    /**
     * Called when the  has completed the move or swipe, and the active item
     * state should be cleared.
     */
    void onItemClear();
}
