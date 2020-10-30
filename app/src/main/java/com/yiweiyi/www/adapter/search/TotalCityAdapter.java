package com.yiweiyi.www.adapter.search;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiweiyi.www.bean.search.CommonAreasListBean;

import java.util.List;

/**
 * @Author: zsh
 * 2020/10/9
 * desc:搜索城市列表适配器
 */
public class TotalCityAdapter extends BaseQuickAdapter<CommonAreasListBean.DataBean, BaseViewHolder> {

    public TotalCityAdapter(int layoutResId, @Nullable List<CommonAreasListBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CommonAreasListBean.DataBean item) {

    }
}
