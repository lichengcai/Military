package com.military;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by lichengcai on 2016/11/22.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int mSpaceLeft;
    private int mSpaceRight;
    private int mSpaceTop;
    private int mSpaceBottom;

    public SpaceItemDecoration(int mSpaceLeft,int mSpaceRight,int mSpaceTop,int mSpaceBottom) {
        this.mSpaceLeft = mSpaceLeft;
        this.mSpaceRight = mSpaceRight;
        this.mSpaceTop = mSpaceTop;
        this.mSpaceBottom = mSpaceBottom;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = mSpaceLeft;
        outRect.right = mSpaceRight;
        outRect.top = mSpaceTop;
        outRect.bottom = mSpaceBottom;
    }
}
