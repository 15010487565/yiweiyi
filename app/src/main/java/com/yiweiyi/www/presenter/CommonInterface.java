package com.yiweiyi.www.presenter;

/**
 * @Author: zsh
 * 2020/9/25
 * desc:
 */
public interface CommonInterface <T> {
    void onCompleted();

    void onError(String e);

    void onNext(T baseBean);
}
