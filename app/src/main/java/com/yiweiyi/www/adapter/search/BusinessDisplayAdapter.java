package com.yiweiyi.www.adapter.search;

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
import com.yiweiyi.www.bean.search.SearchCompeBean;

import java.util.List;

/**
 * @Author: zsh
 * 2020/9/25
 * desc:商家列表适配器
 */
public class BusinessDisplayAdapter extends BaseQuickAdapter<SearchCompeBean.DataBean.ShopListBean, BaseViewHolder> {

    public BusinessDisplayAdapter(int layoutResId, @Nullable List<SearchCompeBean.DataBean.ShopListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SearchCompeBean.DataBean.ShopListBean item) {
        helper.setText(R.id.compe_name_tv, item.getShop_name());
        helper.setText(R.id.name_tv, item.getHead());
        helper.setText(R.id.phone_tv, item.getPhone().get(0));
        helper.setText(R.id.title_tv, item.getProfile());
        helper.setText(R.id.address_tv, item.getArea());
        RoundedCorners roundedCorners = new RoundedCorners(2);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions override = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
        Glide.with(helper.getView(R.id.head_img))
                .asDrawable()
                .load(CommonData.mainUrl + item.getLogo())
//                .centerCrop()
                .apply(override)
                .into((ImageView) helper.getView(R.id.head_img));
        helper.addOnClickListener(R.id.phone_tv)
                .addOnClickListener(R.id.more_number_tv);
    }
}
