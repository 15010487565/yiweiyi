package com.yiweiyi.www.adapter.store;

import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiweiyi.www.R;
import com.yiweiyi.www.model.CertificationMapModel;
import com.yiweiyi.www.utils.ImageUtils;
import com.yiweiyi.www.utils.PrfUtils;

import java.util.List;

/**
 * @Author: zsh
 * 2020/9/24
 * desc:系列列表适配器
 */
public class CerImageAdapter extends BaseQuickAdapter<CertificationMapModel, BaseViewHolder> {

    public CerImageAdapter(int layoutResId, @Nullable List<CertificationMapModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CertificationMapModel item) {
        ImageView imageView = helper.getView(R.id.iv);
        //设置宽度值
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        int widthPixe = PrfUtils.getWidthPixel();
        params.width = widthPixe;
        params.height = (int)(widthPixe/item.getWidth_height());
        imageView.setLayoutParams(params);
        Log.e("TAG_URL",item.getUrl());
        ImageUtils.setImage(imageView, item.getUrl(), 0, R.mipmap.ic_launcher);

    }
}
