package com.yiweiyi.www.model;

import com.yiweiyi.www.api.ApiManager;
import com.yiweiyi.www.base.BaseBean;
import com.yiweiyi.www.bean.personal.BusinessPhoneListBean;
import com.yiweiyi.www.bean.personal.FreeEntryBean;
import com.yiweiyi.www.bean.personal.UserInfoBean;
import com.yiweiyi.www.presenter.PersonalPresenter;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author: zsh
 * 2020/9/30
 * desc:
 */
public class PersonalModel {
    PersonalPresenter.FeedbackInterface mFeedbackInterface;
    PersonalPresenter.FreeEntryInterface mFreeEntryInterface;
    PersonalPresenter.AboutUsInterface mAboutUsInterface;
    PersonalPresenter.UserInfoInterface mUserInfoInterface;
    PersonalPresenter.SetCompeNameInterface mSetCompeNameInterface;
    PersonalPresenter.SetCompeLogoInfoInterface mSetCompeLogoInfoInterface;
    PersonalPresenter.BusinessPhoneListInterface mBusinessPhoneListInterface;
    PersonalPresenter.SetUserNameInterface mSetUserNameInterface;
    PersonalPresenter.SetUserHeaderInfoInterface mSetUserHeaderInfoInterface;


    public PersonalModel(PersonalPresenter.FeedbackInterface mFeedbackInterface,
                         PersonalPresenter.FreeEntryInterface mFreeEntryInterface,
                         PersonalPresenter.AboutUsInterface mAboutUsInterface,
                         PersonalPresenter.UserInfoInterface mUserInfoInterface,
                         PersonalPresenter.SetCompeNameInterface mSetCompeNameInterface,
                         PersonalPresenter.SetCompeLogoInfoInterface mSetCompeLogoInfoInterface,
                         PersonalPresenter.BusinessPhoneListInterface mBusinessPhoneListInterface,
                         PersonalPresenter.SetUserNameInterface mSetUserNameInterface,
                         PersonalPresenter.SetUserHeaderInfoInterface mSetUserHeaderInfoInterface) {
        this.mFeedbackInterface = mFeedbackInterface;
        this.mFreeEntryInterface = mFreeEntryInterface;
        this.mAboutUsInterface = mAboutUsInterface;
        this.mUserInfoInterface = mUserInfoInterface;
        this.mSetCompeNameInterface = mSetCompeNameInterface;
        this.mSetCompeLogoInfoInterface = mSetCompeLogoInfoInterface;
        this.mBusinessPhoneListInterface = mBusinessPhoneListInterface;
        this.mSetUserNameInterface = mSetUserNameInterface;
        this.mSetUserHeaderInfoInterface = mSetUserHeaderInfoInterface;
    }

    public void feedback(String user_id,
                         String content) {
        ApiManager.getInstance().feedback(user_id, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBean>() {
                    @Override
                    public void onCompleted() {
                        mFeedbackInterface.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mFeedbackInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        mFeedbackInterface.onNext(baseBean);
                    }

                });
    }

    public void freeEntry() {
        ApiManager.getInstance().freeEntry()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FreeEntryBean>() {
                    @Override
                    public void onCompleted() {
                        mFreeEntryInterface.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mFreeEntryInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(FreeEntryBean baseBean) {
                        mFreeEntryInterface.onNext(baseBean);
                    }

                });
    }

    public void aboutUs() {
        ApiManager.getInstance().aboutUs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FreeEntryBean>() {
                    @Override
                    public void onCompleted() {
                        mAboutUsInterface.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mAboutUsInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(FreeEntryBean baseBean) {
                        mAboutUsInterface.onNext(baseBean);
                    }

                });
    }

    public void userInfo(String user_id) {
        ApiManager.getInstance().userInfo(user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserInfoBean>() {
                    @Override
                    public void onCompleted() {
                        mUserInfoInterface.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mUserInfoInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(UserInfoBean baseBean) {
                        mUserInfoInterface.onNext(baseBean);
                    }

                });
    }

    public void setCompeName(String user_id, String name) {
        ApiManager.getInstance().setCompeName(user_id, name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBean>() {
                    @Override
                    public void onCompleted() {
                        mSetCompeNameInterface.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mSetCompeNameInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        mSetCompeNameInterface.onNext(baseBean);
                    }

                });
    }

    public void setCompeLogo(String user_id, String logo) {
        ApiManager.getInstance().setCompeLogo(user_id, logo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBean>() {
                    @Override
                    public void onCompleted() {
                        mSetCompeLogoInfoInterface.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mSetCompeLogoInfoInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        mSetCompeLogoInfoInterface.onNext(baseBean);
                    }

                });
    }

    public void businessPhoneList(String user_id) {
        ApiManager.getInstance().businessPhoneList(user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BusinessPhoneListBean>() {
                    @Override
                    public void onCompleted() {
                        mBusinessPhoneListInterface.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mBusinessPhoneListInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(BusinessPhoneListBean baseBean) {
                        mBusinessPhoneListInterface.onNext(baseBean);
                    }

                });
    }
    public void setUserName(String user_id, String nickname) {
        ApiManager.getInstance().setUserName(user_id, nickname)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBean>() {
                    @Override
                    public void onCompleted() {
                        mSetUserNameInterface.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mSetUserNameInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        mSetUserNameInterface.onNext(baseBean);
                    }

                });
    }
    public void setUserHeader(String user_id, String avatar) {
        ApiManager.getInstance().setUserHeader(user_id, avatar)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBean>() {
                    @Override
                    public void onCompleted() {
                        mSetUserHeaderInfoInterface.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mSetUserHeaderInfoInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        mSetUserHeaderInfoInterface.onNext(baseBean);
                    }

                });
    }
}
