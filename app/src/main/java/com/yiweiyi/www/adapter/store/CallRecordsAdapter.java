package com.yiweiyi.www.adapter.store;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiweiyi.www.R;
import com.yiweiyi.www.base.CommonData;
import com.yiweiyi.www.model.CallRecordsModel;
import com.yiweiyi.www.utils.DateUtils;
import com.yiweiyi.www.utils.ImageUtils;
import com.yiweiyi.www.view.CircleImageView;

import java.util.List;

/**
 * @Author: zsh
 * 2020/9/25
 * desc: 靠谱 谁看过我 通话记录
 */
public class CallRecordsAdapter extends BaseQuickAdapter<CallRecordsModel.DataBean.ListBean, BaseViewHolder> {

    public CallRecordsAdapter(int layoutResId, @Nullable List<CallRecordsModel.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CallRecordsModel.DataBean.ListBean item) {

        helper.setText(R.id.name_tv,item.getNickname());

        helper.setText(R.id.time_tv, DateUtils.getTine(item.getCreate_time()));

        helper.setText(R.id.phone_tv,item.getPhone());

        CircleImageView headImg = helper.getView(R.id.head_img);
        String avatar = item.getAvatar();

        if (avatar != null && avatar.startsWith("http")){
            ImageUtils.setImage(headImg, avatar, 3000, R.mipmap.ic_launcher);

        }else {
            ImageUtils.setImage(headImg, CommonData.mainUrl + avatar, 3000, R.mipmap.ic_launcher);
        }

    }
}
