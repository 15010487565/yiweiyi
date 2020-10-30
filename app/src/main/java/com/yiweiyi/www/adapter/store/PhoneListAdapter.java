package com.yiweiyi.www.adapter.store;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @Author: zsh
 * 2020/9/27
 * desc:联系人
 */
public class PhoneListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public PhoneListAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {

    }
}
