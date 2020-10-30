package com.yiweiyi.www.adapter.search;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiweiyi.www.R;
import com.yiweiyi.www.bean.search.SearchRecordsBean;

import java.util.List;

/**
 * @Author: zsh
 * 2020/9/24
 * desc:搜索历史适配器
 */
public class SearchHistoryAdapter extends BaseQuickAdapter<SearchRecordsBean.DataBean, BaseViewHolder> {

    public SearchHistoryAdapter(int layoutResId, @Nullable List<SearchRecordsBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SearchRecordsBean.DataBean item) {
        helper.setText(R.id.title_tv, item.getContent());
        //设置子View的点击事件
        helper.addOnClickListener(R.id.delete_img).addOnClickListener(R.id.all_cl);
    }
}
