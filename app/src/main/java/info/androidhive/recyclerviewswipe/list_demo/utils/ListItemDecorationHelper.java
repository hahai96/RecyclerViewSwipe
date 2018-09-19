package info.androidhive.recyclerviewswipe.list_demo.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

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

        outRect.bottom = 2;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int left = parent.getPaddingLeft() + child.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight() - left;
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDevide.getIntrinsicHeight();

            mDevide.setBounds(left, bottom, right, top);
            mDevide.draw(c);
        }
    }
}