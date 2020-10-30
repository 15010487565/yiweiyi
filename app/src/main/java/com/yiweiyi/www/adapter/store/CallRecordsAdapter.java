package com.yiweiyi.www.adapter.store;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @Author: zsh
 * 2020/9/25
 * desc: 靠谱 谁看过我 通话记录
 */
public class CallRecordsAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public CallRecordsAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {

    }
}
