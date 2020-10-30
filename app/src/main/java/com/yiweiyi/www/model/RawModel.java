package com.yiweiyi.www.model;

import com.yiweiyi.www.api.ApiManager;
import com.yiweiyi.www.bean.raw.RawMaterialBean;
import com.yiweiyi.www.presenter.RawPresenter;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author: zsh
 * 2020/10/11 0011
 * @Description:
 */
public class RawModel {
    RawPresenter.RawMaterialInterface mRawMaterialInterface;

    public RawModel(RawPresenter.RawMaterialInterface mRawMaterialInterface) {
        this.mRawMaterialInterface = mRawMaterialInterface;
    }

    public void rawMaterial(int type, String year) {
        ApiManager.getInstance().rawMaterial(type, year)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RawMaterialBean>() {
                    @Override
                    public void onCompleted() {
                        mRawMaterialInterface.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRawMaterialInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(RawMaterialBean baseBean) {
                        mRawMaterialInterface.onNext(baseBean);
                    }

                });
    }

}
