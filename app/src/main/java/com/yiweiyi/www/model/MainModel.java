package com.yiweiyi.www.model;

import com.yiweiyi.www.api.ApiManager;
import com.yiweiyi.www.bean.main.HomeCategoryBean;
import com.yiweiyi.www.presenter.MainPresenter;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author: zsh
 * 2020/9/25
 * desc:主页
 */
public class MainModel {
    MainPresenter.HomeCategoryInterface homeCategoryInterface;

    public MainModel(MainPresenter.HomeCategoryInterface homeCategoryInterface) {
        this.homeCategoryInterface = homeCategoryInterface;
    }


    public void homeCategory() {
        ApiManager.getInstance().homeCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HomeCategoryBean>() {
                    @Override
                    public void onCompleted() {
                        homeCategoryInterface.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        homeCategoryInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(HomeCategoryBean baseBean) {
                        homeCategoryInterface.onNext(baseBean);
                    }
                });
    }
}
