package com.yiweiyi.www.adapter.store;

import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiweiyi.www.R;
import com.yiweiyi.www.base.CommonData;
import com.yiweiyi.www.utils.ImageUtils;
import com.yiweiyi.www.utils.PrfUtils;

import java.util.List;

import www.xcd.com.mylibrary.help.HelpUtils;

/**
 * @Author: zsh
 * 2020/9/27
 * desc:联系人
 */
public class GridAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public GridAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        ImageView img = helper.getView(R.id.img);
        //取控件当前的布局参数
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) img.getLayoutParams();

        //设置宽度值
        int widthPixe = PrfUtils.getWidthPixel();
        params.width = (int) ((widthPixe - HelpUtils.imageDip2px(mContext,40))*0.333333);
        params.height = (int) ( params.width);


        img.setLayoutParams(params);

        ImageUtils.setImage(img, CommonData.mainUrl+item, 0, R.mipmap.ic_launcher);
    }
}
