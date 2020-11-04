package com.yiweiyi.www.base;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.yiweiyi.www.R;

import butterknife.OnClick;

/**
 * @Author: zsh
 * 2020/9/25
 * desc:activity基类 功能带有头部 bar
 */
public abstract class TitleBaseActivity extends BaseActivity  {
    public static int MENU_DEFAULT_NOTID = 0;

    /**
     * 状态栏占位
     */
    private View viewTop;

    /**
     * 标题栏
     */
    private ConstraintLayout toolbar;

    /**
     * 返回
     */
    private ImageView toolbar_iv_back;

    /**
     * 标题,标题栏右边文本menu
     */
    private TextView toolbar_tv_title, toolbar_tv_menu;

    /**
     * 标题栏右边图片menu
     */
    private ImageView toolbar_iv_menu;

    /**
     * 子布局
     */
    private FrameLayout childLayout;

    /**
     * bar-line
     */
    private View view_line;

    public boolean hideToolBar = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_base);

        initBaseBar();
        initBaseListener();
    }

    /**
     * 监听
     */
    private void initBaseListener() {

        //if (hideToolBar) return;

        //返回
        if (toolbar_iv_back != null)
            toolbar_iv_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    baseBack(v);

                }
            });

        //menu文本点击事件
        if (toolbar_tv_menu != null)
            toolbar_tv_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    baseMenuTextClickListener(v);
                }
            });

        //menu图片点击事件
        if (toolbar_iv_menu != null)
            toolbar_iv_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    baseMenuImgClickListener(v);
                }
            });
    }

    /**
     * 初始化Bar
     */
    private void initBaseBar() {

        viewTop = findViewById(R.id.viewTop);

        viewTop.getLayoutParams().height = QMUIStatusBarHelper.getStatusbarHeight(mContext);

        toolbar = findViewById(R.id.toolbar);

//        if (hideToolBar) {
//
//            setBaseToolBarVisible(false,false);
//            return;
//        }

        toolbar_iv_back = findViewById(R.id.toolbar_iv_back);

        toolbar_tv_title = findViewById(R.id.toolbar_tv_title);

        toolbar_tv_menu = findViewById(R.id.toolbar_tv_menu);

        toolbar_iv_menu = findViewById(R.id.toolbar_iv_menu);

        childLayout = findViewById(R.id.childLayout);

        childLayout.addView(getChildLayout());

        view_line = findViewById(R.id.view_line);

    }

    @OnClick({R.id.toolbar_iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_iv_back:
                finish();
                break;
        }
    }

    /**
     * 设置标题
     *
     * @param title 标题 默认无文字
     */
    public void setBaseTitle(String title) {

        if (toolbar_tv_title == null) return;

        toolbar_tv_title.setText(title);

    }
    /**
     * 设置标题颜色
     *
     * @param color 色值
     */
    public void setBaseTitleColor(int color) {

        if (toolbar_tv_title == null) return;

        toolbar_tv_title.setTextColor(color);

    }

    /**
     * 设置标题栏右边menu文本
     */
    public void setGoneBaseLeftMenu() {
        if (toolbar_iv_back.getVisibility() == View.VISIBLE)
            toolbar_iv_back.setVisibility(View.GONE);

    }

    /**
     * 设置标题栏右边menu文本
     *
     * @param menu 右边menu文本 默认无文字
     */
    public void setBaseRightMenu(String menu) {

        if (toolbar_tv_menu == null) return;

        toolbar_tv_menu.setText(menu);

    }

    /**
     * 设置标题栏右边menu文本颜色
     *
     * @param color : 色值
     */
    public void setBaseRightMenuColor(int color) {

        if (toolbar_iv_menu == null) return;

        toolbar_tv_menu.setTextColor(color);

    }

    /**
     * 设置标题栏右边menu图片
     *
     * @param id 图片资源id
     */
    public void setBaseRightImgMenu(int id) {

        if (id == MENU_DEFAULT_NOTID) return;

        if (toolbar_iv_menu == null) return;


        if (toolbar_iv_menu.getVisibility() == View.GONE)
            toolbar_iv_menu.setVisibility(View.VISIBLE);

        toolbar_tv_menu.setVisibility(View.GONE);

        try {
            toolbar_iv_menu.setImageResource(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 设置标题，标题栏右边文本，右边图片资源传0表示不设置
     *
     * @param title 标题
     * @param menu  标题栏右边文本
     * @param id    右边图片资源
     */
    public void setBaseTitle_Or_MenuText(String title, String menu, int id) {

        setBaseTitle(title);
        setBaseRightMenu(menu);
        setBaseRightImgMenu(id);

    }

    /**
     * 隐藏或显示 标题栏 状态栏占位视图 默认都显示
     *
     * @param visible   标题栏
     * @param isViewTop 状态栏占位视图
     */
    public void setBaseToolBarVisible(boolean visible, boolean isViewTop) {


        if (toolbar == null || viewTop == null) return;

        toolbar.setVisibility(visible ? View.VISIBLE : View.GONE);

        viewTop.setVisibility(isViewTop ? View.VISIBLE : View.GONE);

    }

    /**
     * 显示或隐藏bar下划线
     *
     * @param display
     */
    public void setBaseBarLineView(boolean display) {

        if (view_line == null)
            return;

        view_line.setVisibility(display ? View.VISIBLE : View.GONE);

    }

    /**
     * 设置深色bar
     */
    public void setBaseBarDarkColor() {

        QMUIStatusBarHelper.setStatusBarDarkMode(this);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorF2F2F2));
        viewTop.setBackgroundColor(getResources().getColor(R.color.colorF2F2F2));
        setBaseBarLineView(false);
    }
    /**
     * 设置橘色bar
     */
    public void setBaseBarOrangeColor() {

        QMUIStatusBarHelper.setStatusBarDarkMode(this);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorFD7033));
        viewTop.setBackgroundColor(getResources().getColor(R.color.colorFD7033));
        setBaseBarLineView(false);
    }


    /**
     * 获取子view
     *
     * @return 子布局
     */
    public abstract View getChildLayout();

    /**
     * 返回事件由子类实现
     */
    public abstract void baseBack(View v);


    /**
     * menu文本点击事件由子类实现
     */
    public abstract void baseMenuTextClickListener(View v);

    /**
     * menu图片点击事件由子类实现
     */
    public abstract void baseMenuImgClickListener(View v);

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    public RecyclerView.ItemDecoration getRecyclerViewDivider(@DrawableRes int drawableId) {
        DividerItemDecoration itemDecoration = new DividerItemDecoration(TitleBaseActivity.this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(drawableId));
        return itemDecoration;
    }
}
