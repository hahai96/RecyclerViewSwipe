package info.androidhive.recyclerviewswipe.list_swipe_drag_and_drop.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import info.androidhive.recyclerviewswipe.R;

/**
 * Created by ha_hai on 9/13/2018.
 */

public class ListItemDecorationHelper extends RecyclerView.ItemDecoration {

    Drawable mDevide;
    private int mSpaceHeight;
    private Context mContext;

    public ListItemDecorationHelper(Context context, int spaceHeight) {
        this.mContext = context;
        this.mSpaceHeight = spaceHeight;

        mDevide = mContext.getResources().getDrawable(R.drawable.item_row_devide);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getLayoutManager().getLayoutDirection() == LinearLayout.HORIZONTAL) {
            outRect.right = mSpaceHeight;
        } else if (parent.getLayoutManager().getLayoutDirection() == LinearLayout.VERTICAL) {
            outRect.bottom = mSpaceHeight;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = parent.getPaddingLeft() + child.getPaddingLeft() + params.leftMargin;
            int right = parent.getWidth() - left - params.rightMargin;
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mSpaceHeight;

            mDevide.setBounds(left, bottom, right, top);
            mDevide.draw(c);
        }
    }
}