package com.yiweiyi.www.view.personal;

import com.yiweiyi.www.bean.personal.UserInfoBean;

/**
 * @Author: zsh
 * 2020/10/11
 * desc:用户信息View
 */
public interface UserInfoView extends BasePersonalView{
    void onUserInfoSuccess(UserInfoBean baseBean);
}
