package com.yiweiyi.www.adapter.search;

import android.graphics.Color;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiweiyi.www.R;
import com.yiweiyi.www.bean.search.ProximitySearchBean;
import com.yiweiyi.www.utils.TextUtils;

import java.util.List;

/**
 * @Author: zsh
 * 2020/9/24
 * desc:搜索列表适配器
 */
public class SearchAdapter extends BaseQuickAdapter<ProximitySearchBean.DataBean.ListBean, BaseViewHolder> {

    private String keyword;

    public SearchAdapter(int layoutResId, @Nullable List<ProximitySearchBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ProximitySearchBean.DataBean.ListBean item) {
        helper.setText(R.id.title_tv,
                TextUtils.matcherSearchText(Color.parseColor("#078BF1"), item.getKey_name(), keyword));
        if (item.getP_name() == null) {
            helper.getView(R.id.close_img).setVisibility(View.GONE);
        } else if (item.getP_name().isEmpty()) {
            helper.getView(R.id.close_img).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.close_img).setVisibility(View.VISIBLE);
            helper.setText(R.id.close_img, item.getP_name());
        }
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
