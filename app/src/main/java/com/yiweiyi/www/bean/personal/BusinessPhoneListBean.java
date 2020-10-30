package com.yiweiyi.www.bean.personal;

import com.yiweiyi.www.base.BaseBean;

import java.util.List;

/**
 * @Author: zsh
 * 2020/10/11
 * desc:
 */
public class BusinessPhoneListBean extends BaseBean {

    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
