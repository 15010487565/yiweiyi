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

import java.util.List;

/**
 * @Author: zsh
 * 2020/9/26
 * desc:产品图册九宫格
 */
public class ProductsCatalogueAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ProductsCatalogueAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        RoundedCorners roundedCorners = new RoundedCorners(1);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions override = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
        Glide.with(helper.getView(R.id.head_img))
                .asDrawable()
                .load(CommonData.mainUrl + item)
                .apply(override)
                .into((ImageView) helper.getView(R.id.head_img));
    }
}
