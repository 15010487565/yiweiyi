package com.yiweiyi.www.adapter.compe;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiweiyi.www.R;
import com.yiweiyi.www.base.CommonData;
import com.yiweiyi.www.bean.compe.ReliableListBean;

import java.util.List;

/**
 * @Author: zsh
 * 2020/9/27
 * desc:靠谱列表适配器
 */
public class ReliableAdapter extends BaseQuickAdapter<ReliableListBean.DataBean.ListBean, BaseViewHolder> {

    public ReliableAdapter(int layoutResId, @Nullable List<ReliableListBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ReliableListBean.DataBean.ListBean item) {
        helper.setText(R.id.name_tv, item.getNickname());

        RequestOptions roundOptions = new RequestOptions()
                .transform(new RoundedCorners(10));
        Glide.with(helper.getView(R.id.img))
                .asDrawable()
                .load(CommonData.mainUrl + item.getAvatar())
                .apply(roundOptions)
                .into((ImageView) helper.getView(R.id.img));
    }
}
