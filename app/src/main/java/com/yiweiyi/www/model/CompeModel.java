package com.yiweiyi.www.model;

import com.yiweiyi.www.api.ApiManager;
import com.yiweiyi.www.base.BaseBean;
import com.yiweiyi.www.bean.compe.CompeDetailsBean;
import com.yiweiyi.www.bean.compe.ProdCataBean;
import com.yiweiyi.www.bean.compe.ReliableListBean;
import com.yiweiyi.www.bean.compe.ShareImgBean;
import com.yiweiyi.www.presenter.CompePresenter;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author: zsh
 * 2020/10/8
 * desc:
 */
public class CompeModel {

    CompePresenter.CompeDetailsInterface mCompeDetailsInterface;
    CompePresenter.ProdCataInterface mProdCataInterface;
    CompePresenter.RealPictureInterface mRealPictureInterface;
    CompePresenter.CertificationInterface mCertificationInterface;
    CompePresenter.ShareImgInterface mShareImgInterface;
    CompePresenter.ReliableListInterface mReliableListInterface;
    CompePresenter.AddReliableInterface mAddReliableInterface;
    CompePresenter.DelReliableInterface mDelReliableInterface;

    public CompeModel(CompePresenter.CompeDetailsInterface mCompeDetailsInterface, CompePresenter.ProdCataInterface mProdCataInterface,
                      CompePresenter.RealPictureInterface mRealPictureInterface, CompePresenter.CertificationInterface mCertificationInterface,
                      CompePresenter.ShareImgInterface mShareImgInterface, CompePresenter.ReliableListInterface mReliableListInterface,
                      CompePresenter.AddReliableInterface mAddReliableInterface, CompePresenter.DelReliableInterface mDelReliableInterface) {
        this.mCompeDetailsInterface = mCompeDetailsInterface;
        this.mProdCataInterface = mProdCataInterface;
        this.mRealPictureInterface = mRealPictureInterface;
        this.mCertificationInterface = mCertificationInterface;
        this.mShareImgInterface = mShareImgInterface;
        this.mReliableListInterface = mReliableListInterface;
        this.mAddReliableInterface = mAddReliableInterface;
        this.mDelReliableInterface = mDelReliableInterface;
    }

    public void compeDetails(int shop_id,
                             String user_id) {
        ApiManager.getInstance().compeDetails(shop_id, user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CompeDetailsBean>() {
                    @Override
                    public void onCompleted() {
                        mCompeDetailsInterface.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mCompeDetailsInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(CompeDetailsBean baseBean) {
                        mCompeDetailsInterface.onNext(baseBean);
                    }

                });
    }

    public void prodCata(int shop_id,
                         String album_name) {
        ApiManager.getInstance().prodCata(shop_id, album_name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProdCataBean>() {
                    @Override
                    public void onCompleted() {
                        mProdCataInterface.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mProdCataInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(ProdCataBean baseBean) {
                        mProdCataInterface.onNext(baseBean);
                    }

                });
    }

    public void realPicture(int shop_id,
                            String album_name) {
        ApiManager.getInstance().realPicture(shop_id, album_name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProdCataBean>() {
                    @Override
                    public void onCompleted() {
                        mRealPictureInterface.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRealPictureInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(ProdCataBean baseBean) {
                        mRealPictureInterface.onNext(baseBean);
                    }

                });
    }

    public void certification(int shop_id,
                              String album_name) {
        ApiManager.getInstance().certification(shop_id, album_name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProdCataBean>() {
                    @Override
                    public void onCompleted() {
                        mCertificationInterface.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mCertificationInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(ProdCataBean baseBean) {
                        mCertificationInterface.onNext(baseBean);
                    }

                });
    }

    public void shareImg(int shop_id) {
        ApiManager.getInstance().shareImg(shop_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ShareImgBean>() {
                    @Override
                    public void onCompleted() {
                        mShareImgInterface.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mShareImgInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(ShareImgBean baseBean) {
                        mShareImgInterface.onNext(baseBean);
                    }

                });
    }

    public void reliableList(int shop_id) {
        ApiManager.getInstance().reliableList(shop_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReliableListBean>() {
                    @Override
                    public void onCompleted() {
                        mReliableListInterface.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mReliableListInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(ReliableListBean baseBean) {
                        mReliableListInterface.onNext(baseBean);
                    }

                });
    }
    public void addReliable(int shop_id,String user_id) {
        ApiManager.getInstance().addReliable(shop_id,user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBean>() {
                    @Override
                    public void onCompleted() {
                        mAddReliableInterface.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mAddReliableInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        mAddReliableInterface.onNext(baseBean);
                    }

                });
    }
    public void delReliable(int shop_id,String user_id) {
        ApiManager.getInstance().delReliable(shop_id,user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBean>() {
                    @Override
                    public void onCompleted() {
                        mDelReliableInterface.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mDelReliableInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        mDelReliableInterface.onNext(baseBean);
                    }

                });
    }

}
