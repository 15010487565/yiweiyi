package com.yiweiyi.www.view.search;

import com.yiweiyi.www.bean.search.ProximitySearchBean;

/**
 * @Author: zsh
 * 2020/10/8
 * desc:
 */
public interface ProximitySearchView extends BaseSearchView {
    void onProximitySearchSuccess(ProximitySearchBean baseBean);
}
