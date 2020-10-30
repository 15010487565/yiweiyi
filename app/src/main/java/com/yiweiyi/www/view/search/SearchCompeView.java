package com.yiweiyi.www.view.search;

import com.yiweiyi.www.bean.search.SearchCompeBean;

/**
 * @Author: zsh
 * 2020/10/8
 * desc:搜索接口View
 */
public interface SearchCompeView extends BaseSearchView {
    void onSearchCompeSuccess(SearchCompeBean baseBean);
}
