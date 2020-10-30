package com.yiweiyi.www.view.search;

import com.yiweiyi.www.bean.search.SearchRecordsBean;

/**
 * @Author: zsh
 * 2020/10/8
 * desc:搜索记录
 */
public interface SearchRecordsView extends BaseSearchView{
    void onSearchRecordsSuccess(SearchRecordsBean baseBean);
}
