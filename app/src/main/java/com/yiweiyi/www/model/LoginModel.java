package com.yiweiyi.www.model;

import com.yiweiyi.www.api.ApiManager;
import com.yiweiyi.www.base.BaseBean;
import com.yiweiyi.www.bean.login.SigninBean;
import com.yiweiyi.www.presenter.LoginPresenter;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author: zsh
 * 2020/9/29
 * desc:登陆注册
 */
public class LoginModel {
    LoginPresenter.SendVerifiCodeInterface mSendVerifiCodeInterface;
    LoginPresenter.SigninInterface mSigninInterface;

    public LoginModel(LoginPresenter.SendVerifiCodeInterface mSendVerifiCodeInterface, LoginPresenter.SigninInterface mSigninInterface) {
        this.mSendVerifiCodeInterface = mSendVerifiCodeInterface;
        this.mSigninInterface = mSigninInterface;
    }

    public void sendVerifiCode(String phone) {
        ApiManager.getInstance().sendVerifiCode(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBean>() {
                    @Override
                    public void onCompleted() {
                        mSendVerifiCodeInterface.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mSendVerifiCodeInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        mSendVerifiCodeInterface.onNext(baseBean);
                    }

                });
    }

    public void signin(String phone, String code) {
        ApiManager.getInstance().signin(phone, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SigninBean>() {
                    @Override
                    public void onCompleted() {
                        mSigninInterface.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mSigninInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(SigninBean baseBean) {
                        mSigninInterface.onNext(baseBean);
                    }

                });
    }


}
