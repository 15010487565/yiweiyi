package com.yiweiyi.www.adapter.personal;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiweiyi.www.R;
import com.yiweiyi.www.bean.raw.RawMaterialBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Author: zsh
 * 2020/9/28
 * desc:原料行情列表
 */
public class RawMaterialAdapter extends BaseQuickAdapter<RawMaterialBean.DataBean.ListBean, BaseViewHolder> {
    private Context mContext;

    public RawMaterialAdapter(int layoutResId, @Nullable List<RawMaterialBean.DataBean.ListBean> data, Context context) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, RawMaterialBean.DataBean.ListBean item) {
        String date = new SimpleDateFormat("MM月dd日").format(
                new java.util.Date(item.getDate() * 1000));
        helper.setText(R.id.date_tv, date);
        helper.setText(R.id.price_tv, String.valueOf(item.getPrice()));
        if (item.getWave() > 0) {
            helper.setText(R.id.number_tv, "+" + item.getWave());
            helper.getView(R.id.number_tv)
                    .setBackground(mContext.getResources().getDrawable(R.drawable.shape_yuanjiao_6_fd7033));
        } else {
            helper.setText(R.id.number_tv, String.valueOf(item.getWave()));
            helper.getView(R.id.number_tv)
                    .setBackground(mContext.getResources().getDrawable(R.drawable.shape_yuanjiao_6_64ac24));
        }

    }
}
