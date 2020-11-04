package com.yiweiyi.www.model;

import java.util.List;

/**
 * Created by gs on 2020/11/3.
 */
public class PhoneListModel {

    /**
     * code : 1
     * msg : 成功
     * data : ["18701111963","18701090729","010-63872605","010-63872623","010-83607588","010-83607688"]
     */

    private String code;
    private String msg;
    private List<String> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
