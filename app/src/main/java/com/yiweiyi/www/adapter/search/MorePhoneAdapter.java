package com.yiweiyi.www.adapter.search;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qmuiteam.qmui.alpha.QMUIAlphaButton;
import com.yiweiyi.www.R;

import java.util.List;

import www.xcd.com.mylibrary.help.HelpUtils;

/**
 * @Author: zsh
 * 2020/10/8
 * desc:更多电话号码弹框适配器
 */
public class MorePhoneAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MorePhoneAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.phone_tv, item);
        QMUIAlphaButton dial_bt = helper.getView(R.id.dial_bt);
        dial_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpUtils.call(mContext,item,true);
            }
        });
    }
}
