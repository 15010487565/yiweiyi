package com.yiweiyi.www.adapter.store;

import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiweiyi.www.R;
import com.yiweiyi.www.utils.ImageUtils;
import com.yiweiyi.www.utils.PrfUtils;

import java.util.List;

/**
 * @Author: zsh
 * 2020/9/24
 * desc:系列列表适配器
 */
public class ImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ImageAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        ImageView imageView = helper.getView(R.id.iv);
        Log.e("TAG_图片",""+item);
        //设置宽度值
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        int widthPixe = PrfUtils.getWidthPixel();
        params.width = widthPixe;
        params.height = widthPixe;
        imageView.setLayoutParams(params);
        ImageUtils.setImage(imageView, item, 0, R.mipmap.ic_launcher);
    }
}
