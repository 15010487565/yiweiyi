package com.yiweiyi.www.adapter.store;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiweiyi.www.R;

import java.util.List;

/**
 * @author: zsh
 * 2020/10/12 0012
 * @Description:
 */
public class ContactHeaderItemAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ContactHeaderItemAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.area_tv, item);
    }
}
