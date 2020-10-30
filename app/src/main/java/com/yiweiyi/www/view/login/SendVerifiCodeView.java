package com.yiweiyi.www.view.login;

import com.yiweiyi.www.base.BaseBean;

/**
 * @Author: zsh
 * 2020/9/29
 * desc:发送验证码
 */
public interface SendVerifiCodeView extends BaseLoginView{
    void onSendVerifiCodeSuccess(BaseBean baseBean);
}
