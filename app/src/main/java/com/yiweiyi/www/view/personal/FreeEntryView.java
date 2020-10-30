package com.yiweiyi.www.view.personal;

import com.yiweiyi.www.bean.personal.FreeEntryBean;

/**
 * @Author: zsh
 * 2020/9/30
 * desc:获取入驻提示
 */
public interface FreeEntryView extends BasePersonalView{
    void onFreeEntrySuccess(FreeEntryBean baseBean);
}
