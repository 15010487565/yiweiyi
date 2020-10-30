package com.yiweiyi.www.adapter.main;

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
 * desc:系列列表适配器
 */
public class SeriesTextAdapter extends BaseQuickAdapter<HomeCategoryBean.DataBean.ListBean, BaseViewHolder> {

    public SeriesTextAdapter(int layoutResId, @Nullable List<HomeCategoryBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HomeCategoryBean.DataBean.ListBean item) {
        helper.setText(R.id.series_tv, item.getName());
    }
}
