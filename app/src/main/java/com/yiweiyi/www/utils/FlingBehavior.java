package com.yiweiyi.www.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;

import java.lang.reflect.Field;

import static android.view.KeyEvent.ACTION_DOWN;

/**
 * @Author: zsh
 * 2020/9/24
 * desc:AppBarLayout属性(解决Tab吸顶抖动)
 */
public class FlingBehavior extends AppBarLayout.Behavior {

    public FlingBehavior() {
        super();
    }

    public FlingBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);

//        setDragCallback(new BaseDragCallback() {
//            @Override
//            public boolean canDrag(@NonNull AppBarLayout appBarLayout) {
//                return true;
//            }
//        });
    }


    /**
     * 当准备开始嵌套滚动时调用
     * <p>
     * fling上滑appbar然后迅速fling下滑recycler时, HeaderBehavior的mScroller并未停止, 会导致上下来回晃动
     *
     * @param coordinatorLayout 父布局CoordinatorLayout
     * @param child             使用此Behavior的AppBarLayout
     * @param target            发起嵌套滚动的目标View(即AppBarLayout下面的ScrollView或RecyclerView)
     * @param dx                用户在水平方向上滑动的像素数
     * @param dy                用户在垂直方向上滑动的像素数
     * @param consumed          输出参数，consumed[0]为水平方向应该消耗的距离，consumed[1]为垂直方向应该消耗的距离
     */
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dx, int dy, int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        //type==1时处于非惯性滑动
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, AppBarLayout child, MotionEvent ev) {
        if (ev.getAction() == ACTION_DOWN) {
            Object scroller = getSuperSuperField(this, "scroller");
            if (scroller != null && scroller instanceof OverScroller) {
                OverScroller overScroller = (OverScroller) scroller;
                overScroller.abortAnimation();
            }
        }

        return super.onInterceptTouchEvent(parent, child, ev);
    }

    private Object getSuperSuperField(Object paramClass, String paramString) {
        Field field = null;
        Object object = null;
        try {
            field = paramClass.getClass().getSuperclass().getSuperclass().getSuperclass().getDeclaredField(paramString);
            field.setAccessible(true);
            object = field.get(paramClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

}
