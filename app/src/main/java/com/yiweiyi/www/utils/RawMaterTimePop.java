package com.yiweiyi.www.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiweiyi.www.R;
import com.yiweiyi.www.adapter.raw.YearPopAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author: zsh
 * 2020/9/28
 * desc:原料年份
 */
public class RawMaterTimePop {

    @BindView(R.id.recycler_rv)
    RecyclerView recyclerRv;
    private Context mContext;
    private View view;
    private PopupWindow popupWindow;//视窗
    private YearPopAdapter mYearPopAdapter;
    private DialogCallBackListener dialogCallBackListener;


    public RawMaterTimePop(Context mContext) {
        this.mContext = mContext;
        initPop();
        initView();
        initData();
        initListener();
    }

    private void initView() {

    }

    public void setDialogCallBackListener(DialogCallBackListener dialogCallBackListener) {
        this.dialogCallBackListener = dialogCallBackListener;
    }

    /**
     * 初始化窗口
     */
    private void initPop() {

        view = View.inflate(mContext, R.layout.pop_raw_time, null);

        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);

        ButterKnife.bind(this, view);

        popupWindow.setBackgroundDrawable(null);

        popupWindow.setAnimationStyle(R.style.Popanim);
    }

    private void initListener() {
        mYearPopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (dialogCallBackListener != null)
                    dialogCallBackListener.year(mYearPopAdapter.getItem(position));
                dismissWinsow();
            }
        });
    }


    private void initData() {
        recyclerRv.setLayoutManager(new LinearLayoutManager(mContext));
        List<String> dataList = new ArrayList<>();
        mYearPopAdapter = new YearPopAdapter(R.layout.item_year_pop, dataList);
        //添加Android自带的分割线
        recyclerRv.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        recyclerRv.setAdapter(mYearPopAdapter);
    }

    /**
     * 关闭窗口
     */
    public void dismissWinsow() {


        if (popupWindow == null)
            return;
        popupWindow.dismiss();


    }

    /**
     * 窗口显示状态
     *
     * @return
     */
    public boolean isShowingWindow() {

        if (popupWindow != null)
            return popupWindow.isShowing();

        return false;
    }

    /**
     * 显示
     */
    public void show(View relyOnView, List<String> year) {

        if (popupWindow == null)
            return;
        popupWindow.showAsDropDown(relyOnView);

        mYearPopAdapter.replaceData(year);
    }


    public interface DialogCallBackListener {

        void year(String year);

    }

}
