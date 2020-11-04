package com.yiweiyi.www.adapter.store;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiweiyi.www.R;
import com.yiweiyi.www.model.LikeModel;
import com.yiweiyi.www.utils.ImageUtils;

import java.util.List;

/**
 * @Author: zsh
 * 2020/9/27
 * desc:联系人
 */
public class LikeAdapter extends BaseQuickAdapter<LikeModel.DataBean.ListBean, BaseViewHolder> {

    public LikeAdapter(int layoutResId, @Nullable List<LikeModel.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, LikeModel.DataBean.ListBean item) {
        ImageView head_img = helper.getView(R.id.head_img);
        ImageUtils.setImage(head_img, item.getAvatar(), 3000, R.mipmap.ic_launcher);

        helper.setText(R.id.tv_name,item.getNickname());
    }
}
