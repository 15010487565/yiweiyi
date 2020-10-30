package com.yiweiyi.www.view.login;

import com.yiweiyi.www.bean.login.SigninBean;

/**
 * @Author: zsh
 * 2020/9/29
 * desc:登陆
 */
public interface SigninView extends BaseLoginView{
    void onSigninSuccess(SigninBean baseBean);
}
