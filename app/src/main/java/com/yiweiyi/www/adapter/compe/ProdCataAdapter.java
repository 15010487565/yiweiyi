package com.yiweiyi.www.adapter.compe;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiweiyi.www.R;
import com.yiweiyi.www.base.CommonData;

import java.util.List;

/**
 * @Author: zsh
 * 2020/9/26
 * desc:产品图册列表
 */
public class ProdCataAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ProdCataAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        Glide.with(helper.getView(R.id.img))
                .asDrawable()
                .load(CommonData.mainUrl + item)
                .into((ImageView) helper.getView(R.id.img));
    }
}
