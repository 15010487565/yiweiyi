package com.yiweiyi.www.view.search;

import com.yiweiyi.www.bean.search.CommonAreasListBean;

/**
 * @Author: zsh
 * 2020/10/9
 * desc:返回搜索厂家包含地区
 */
public interface CommonAreasListView extends BaseSearchView {
    void onCommonAreasListSuccess(CommonAreasListBean baseBean);
}
