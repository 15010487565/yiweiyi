package com.yiweiyi.www.adapter.raw;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiweiyi.www.R;

import java.util.List;

/**
 * @author: zsh
 * 2020/10/11 0011
 * @Description:
 */
public class YearPopAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public YearPopAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.year_tv, item+"年");
    }
}
