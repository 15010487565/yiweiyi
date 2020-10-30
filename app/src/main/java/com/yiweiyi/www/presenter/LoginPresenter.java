package com.yiweiyi.www.presenter;

import com.yiweiyi.www.base.BaseBean;
import com.yiweiyi.www.bean.login.SigninBean;
import com.yiweiyi.www.model.LoginModel;
import com.yiweiyi.www.view.login.BaseLoginView;
import com.yiweiyi.www.view.login.SendVerifiCodeView;
import com.yiweiyi.www.view.login.SigninView;

/**
 * @Author: zsh
 * 2020/9/29
 * desc:
 */
public class LoginPresenter {
    BaseLoginView mBaseLoginView;
    LoginModel mLoginModel;
    SendVerifiCodeView mSendVerifiCodeView;
    SigninView mSigninView;

    public LoginPresenter(BaseLoginView mBaseLoginView) {
        this.mBaseLoginView = mBaseLoginView;
        if (mBaseLoginView instanceof SendVerifiCodeView) {
            mSendVerifiCodeView = (SendVerifiCodeView) mBaseLoginView;
        }
        if (mBaseLoginView instanceof SigninView) {
            mSigninView = (SigninView) mBaseLoginView;
        }
        mLoginModel = new LoginModel(mSendVerifiCodeInterface, mSigninInterface);
    }

    /**
     * 获取验证码
     */
    public void sendVerifiCode(String phone) {
        mLoginModel.sendVerifiCode(phone);
    }

    /**
     * 登陆
     */
    public void signin(String phone, String code) {
        mLoginModel.signin(phone, code);
    }

    SendVerifiCodeInterface mSendVerifiCodeInterface = new SendVerifiCodeInterface() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mSendVerifiCodeView != null) {
                mSendVerifiCodeView.onError(e);
            }
        }

        @Override
        public void onNext(BaseBean baseBean) {
            if (mSendVerifiCodeView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mSendVerifiCodeView.onSendVerifiCodeSuccess(baseBean);
                } else {
                    mSendVerifiCodeView.onError(baseBean.getMsg());
                }
            }
        }
    };

    SigninInterface mSigninInterface = new SigninInterface() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mSigninView != null) {
                mSigninView.onError(e);
            }
        }

        @Override
        public void onNext(SigninBean baseBean) {
            if (mSigninView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mSigninView.onSigninSuccess(baseBean);
                } else {
                    mSigninView.onError(baseBean.getMsg());
                }
            }
        }
    };

    public interface SendVerifiCodeInterface extends CommonInterface<BaseBean> {
    }

    public interface SigninInterface extends CommonInterface<SigninBean> {
    }
}
