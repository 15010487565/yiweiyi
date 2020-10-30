package com.yiweiyi.www.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author: zsh
 * 2020/9/24
 * desc:用于网格布局添加间隔
 */
public class GridDecorationLpp extends RecyclerView.ItemDecoration {


    private int spanCount;
    private int spacing;
    private int topSpacing;
    private boolean includeEdge;

    public GridDecorationLpp(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.topSpacing = spacing;
        this.includeEdge = includeEdge;
    }

    public GridDecorationLpp(int spanCount, int spacing, int topSpacing,  boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.topSpacing = topSpacing;
        this.includeEdge = includeEdge;
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int column = position % this.spanCount;
        if (this.includeEdge) {
            outRect.left = this.spacing - column * this.spacing / this.spanCount;
            outRect.right = (column + 1) * this.spacing / this.spanCount;
            if (position < this.spanCount) {
                outRect.top = this.topSpacing;
            }

            outRect.bottom = this.topSpacing;
        } else {
            outRect.left = column * this.spacing / this.spanCount;
            outRect.right = this.spacing - (column + 1) * this.spacing / this.spanCount;
            if (position >= this.spanCount) {
                outRect.top = this.topSpacing;
            }
        }

    }

}
