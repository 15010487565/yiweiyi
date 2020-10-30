package com.yiweiyi.www.adapter.store;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiweiyi.www.R;
import com.yiweiyi.www.model.DetailsModel;
import com.yiweiyi.www.utils.ImageUtils;

import java.util.List;

/**
 * @Author: zsh
 * 2020/9/27
 * desc:联系人
 */
public class GalleryAdapter extends BaseQuickAdapter<DetailsModel.DataBean.LikeBean, BaseViewHolder> {

    public GalleryAdapter(int layoutResId, @Nullable List<DetailsModel.DataBean.LikeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DetailsModel.DataBean.LikeBean item) {
        ImageView head_img = helper.getView(R.id.head_img);
        ImageUtils.setImage(head_img, item.getAvatar(), 3000, R.mipmap.ic_launcher);
    }
}
