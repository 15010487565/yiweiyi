package com.yiweiyi.www.adapter.main;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiweiyi.www.R;
import com.yiweiyi.www.bean.main.HomeCategoryBean;

import java.util.List;

/**
 * @Author: zsh
 * 2020/9/24
 * desc:全部系列
 */
public class AllSeriesAdrpter extends BaseQuickAdapter<HomeCategoryBean.DataBean, BaseViewHolder> {

    public AllSeriesAdrpter(int layoutResId, @Nullable List<HomeCategoryBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HomeCategoryBean.DataBean item) {
        helper.setText(R.id.series_tv, item.getName());
        if (item.isSelect()) {
            helper.getView(R.id.item_cl).setBackgroundColor(Color.parseColor("#F2F2F2"));
        }else
        {
            helper.getView(R.id.item_cl).setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }
}
