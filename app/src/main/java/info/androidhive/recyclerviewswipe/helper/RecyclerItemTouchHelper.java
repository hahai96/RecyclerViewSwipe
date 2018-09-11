package info.androidhive.recyclerviewswipe.helper;

/**
 * Created by ha_hai on 5/17/2018.
 */

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import info.androidhive.recyclerviewswipe.CartListAdapter;

/**
 * Created by ravi on 29/09/17.
 */

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {
    private RecyclerItemTouchHelperListener listener;
    CartListAdapter adapter;

    public RecyclerItemTouchHelper(int dragDirs, int swipeDirs, RecyclerItemTouchHelperListener listener, CartListAdapter adapter) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
        this.adapter = adapter;
    }


    /**
    Phương thức này được gọi khi viewHolder có sự thay đổi, như swiped hay draged
     actionState có một trong các giá trị
     + ACTION_STATE_IDLE: trạng thái không hoạt động
     + ACTION_STATE_SWIPE
     + ACTION_STATE_DRAG
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null) {
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                final View foregroundView = ((CartListAdapter.MyViewHolder) viewHolder).viewForeground;
                getDefaultUIUtil().onSelected(foregroundView);

            } else if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                ItemTouchHelperViewHolder itemViewHolder = (ItemTouchHelperViewHolder) viewHolder;
                itemViewHolder.onItemSelected();
            }
        }
    }

    /**
     * Phương thức này được gọi rất nhiều lần, cập nhật animation khi ta swipe, drag view
     */
    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            final View foregroundView = ((CartListAdapter.MyViewHolder) viewHolder).viewForeground;
            getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
                    actionState, isCurrentlyActive);

        } else if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            final View viewRoot = ((CartListAdapter.MyViewHolder) viewHolder).viewRoot;
            getDefaultUIUtil().onDraw(c, recyclerView, viewRoot, dX, dY,
                    actionState, isCurrentlyActive);
        }
    }

    /**
     Phương thức này cũng như onChildDrawOver nhưng nó vẽ đối tượng bên dưới
     */
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {

        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            final View foregroundView = ((CartListAdapter.MyViewHolder) viewHolder).viewForeground;
            getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
                    actionState, isCurrentlyActive);

        } else if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            final View viewRoot = ((CartListAdapter.MyViewHolder) viewHolder).viewRoot;
            getDefaultUIUtil().onDraw(c, recyclerView, viewRoot, dX, dY,
                    actionState, isCurrentlyActive);
        }
    }


    /**
     * Phương thức này được gọi cuối cùng, sau khi người dùng đã tương tác (swiped, dragged) xong với view
     * Xóa tất cả sự thay đổi của view sau khi thực hiện xong các phương thức
     * onSelectedChange(), onChildDrawOver(), onChildDraw()
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final View foregroundView = ((CartListAdapter.MyViewHolder) viewHolder).viewForeground;
        getDefaultUIUtil().clearView(foregroundView);

        ItemTouchHelperViewHolder itemViewHolder = (ItemTouchHelperViewHolder) viewHolder;
        itemViewHolder.onItemClear();
    }

    /**
     * Được gọi khi mà viewholder được user swiped
     * Nếu bạn trả về hướng tương đối (START, END) bởi phương thức getMovementFlags
     * thì phương thức này cũng sử dụng hướng tương đối. Ngược lại, nó sử dụng hướng tuyệt đối
     * Nếu bạn không hỗ trợ swipe, phương thức này sẽ không được gọi
     * ItemTouchHelper sẽ giữ 1 tham chiếu tới view cho đến khi nó được detach khỏi RecyclerView,
     * ngay khi nó được detach, ItemTouchHelper sẽ gọi onClear
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());
    }

    /**
     * Chuyển đổi flags thành hướng tuyệt đối, có nghĩa là (START và END) thành (LEFT và RIGHT)
     * tùy thuộc vào hướng của RecyclerView
     */
    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    //interface for on Swipe
    public interface RecyclerItemTouchHelperListener {
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
    }


    /**
     * Trả về flags tổng hợp xác định hướng di chuyển trong mỗi trạng thái (idle, swipe, drag)
     * Thay vì thực hiện cờ này theo cách thủ công, bạn có thể sử dụng makeMovementFlags hoặc makeFlag
     * Cờ này bao gồm 3 bộ 8 bit, 8bit đầu là của trạng thái IDLE, 8bit tiếp theo cho SWIPE, còn lại là DRAG.
     * Mỗi 8bit có thể được xây dựng bằng toán tử OR trong ItemTouchHelper
     * EX: makeFlag(ACTION_STATE_IDLE, RIGHT) | makeFlag(ACTION_STATE_SWIPE, LEFT | RIGHT);
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        adapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    /**
     * Có nên bắt đầu Drag khi mà view được nhấn lâu hay không
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

}
