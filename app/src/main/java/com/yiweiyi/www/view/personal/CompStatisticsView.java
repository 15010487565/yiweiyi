package com.yiweiyi.www.view.personal;

import com.yiweiyi.www.bean.personal.CompStatisticsBean;

/**
 * @author: zsh
 * 2020/10/13 0013
 * @Description:商家管理页统计数据
 */
public interface CompStatisticsView extends BasePersonalView {
    void onCompStatisticsSuccess(CompStatisticsBean baseBean);
}
