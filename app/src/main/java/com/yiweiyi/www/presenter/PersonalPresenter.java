package com.yiweiyi.www.presenter;

import com.yiweiyi.www.base.BaseBean;
import com.yiweiyi.www.bean.personal.BusinessPhoneListBean;
import com.yiweiyi.www.bean.personal.FreeEntryBean;
import com.yiweiyi.www.bean.personal.UserInfoBean;
import com.yiweiyi.www.model.PersonalModel;
import com.yiweiyi.www.view.personal.AboutUsView;
import com.yiweiyi.www.view.personal.BasePersonalView;
import com.yiweiyi.www.view.personal.BusinessPhoneListView;
import com.yiweiyi.www.view.personal.FeedbackView;
import com.yiweiyi.www.view.personal.FreeEntryView;
import com.yiweiyi.www.view.personal.SetCompeLogoView;
import com.yiweiyi.www.view.personal.SetCompeNameView;
import com.yiweiyi.www.view.personal.SetUserHeaderView;
import com.yiweiyi.www.view.personal.SetUserNameView;
import com.yiweiyi.www.view.personal.UserInfoView;

/**
 * @Author: zsh
 * 2020/9/30
 * desc:个人中心Presenter
 */
public class PersonalPresenter {

    BasePersonalView mBasePersonalView;
    PersonalModel mPersonalModel;
    FeedbackView mFeedbackView;
    FreeEntryView mFreeEntryView;
    AboutUsView mAboutUsView;
    UserInfoView mUserInfoView;
    SetCompeNameView mSetCompeNameView;
    SetCompeLogoView mSetCompeLogoView;
    BusinessPhoneListView mBusinessPhoneListView;
    SetUserNameView mSetUserNameView;
    SetUserHeaderView mSetUserHeaderView;

    public PersonalPresenter(BasePersonalView mBasePersonalView) {
        this.mBasePersonalView = mBasePersonalView;
        if (mBasePersonalView instanceof FeedbackView) {
            mFeedbackView = (FeedbackView) mBasePersonalView;
        }
        if (mBasePersonalView instanceof FreeEntryView) {
            mFreeEntryView = (FreeEntryView) mBasePersonalView;
        }
        if (mBasePersonalView instanceof AboutUsView) {
            mAboutUsView = (AboutUsView) mBasePersonalView;
        }
        if (mBasePersonalView instanceof UserInfoView) {
            mUserInfoView = (UserInfoView) mBasePersonalView;
        }
        if (mBasePersonalView instanceof SetCompeNameView) {
            mSetCompeNameView = (SetCompeNameView) mBasePersonalView;
        }
        if (mBasePersonalView instanceof SetCompeLogoView) {
            mSetCompeLogoView = (SetCompeLogoView) mBasePersonalView;
        }
        if (mBasePersonalView instanceof BusinessPhoneListView) {
            mBusinessPhoneListView = (BusinessPhoneListView) mBasePersonalView;
        }
        if (mBasePersonalView instanceof SetUserNameView) {
            mSetUserNameView = (SetUserNameView) mBasePersonalView;
        }
        if (mBasePersonalView instanceof SetUserHeaderView) {
            mSetUserHeaderView = (SetUserHeaderView) mBasePersonalView;
        }
        mPersonalModel = new PersonalModel(mFeedbackInterface,
                mFreeEntryInterface, mAboutUsInterface,
                mUserInfoInterface, mSetCompeNameInterface,
                mSetCompeLogoInfoInterface, mBusinessPhoneListInterface,
                mSetUserNameInterface, mSetUserHeaderInfoInterface);
    }

    /**
     * 提交反馈
     *
     * @param user_id 用户id
     * @param content 反馈内容
     */
    public void feedback(String user_id,
                         String content) {
        mPersonalModel.feedback(user_id, content);
    }

    /**
     * 获取入驻提示
     */
    public void freeEntry() {
        mPersonalModel.freeEntry();
    }

    /**
     * 关于我们
     */
    public void aboutUs() {
        mPersonalModel.aboutUs();
    }

    //用户基本信息接口
    public void userInfo(String user_id) {
        mPersonalModel.userInfo(user_id);
    }

    //修改厂家名称
    public void setCompeName(String user_id, String name) {
        mPersonalModel.setCompeName(user_id, name);
    }

    //修改公司 Logo
    public void setCompeLogo(String user_id, String logo) {
        mPersonalModel.setCompeLogo(user_id, logo);
    }

    //获取商家联系电话
    public void businessPhoneList(String user_id) {
        mPersonalModel.businessPhoneList(user_id);
    }

    //修改用户昵称
    public void setUserName(String user_id, String nickname) {
        mPersonalModel.setUserName(user_id, nickname);
    }

    //修改用户头像
    public void setUserHeader(String user_id, String avatar) {
        mPersonalModel.setUserHeader(user_id, avatar);
    }

    FeedbackInterface mFeedbackInterface = new FeedbackInterface() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mFeedbackView != null) {
                mFeedbackView.onError(e);
            }
        }

        @Override
        public void onNext(BaseBean baseBean) {
            if (mFeedbackView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mFeedbackView.onFeedbackSuccess(baseBean);
                } else {
                    mFeedbackView.onError(baseBean.getMsg());
                }
            }
        }
    };

    FreeEntryInterface mFreeEntryInterface = new FreeEntryInterface() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mFreeEntryView != null) {
                mFreeEntryView.onError(e);
            }
        }

        @Override
        public void onNext(FreeEntryBean baseBean) {
            if (mFreeEntryView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mFreeEntryView.onFreeEntrySuccess(baseBean);
                } else {
                    mFreeEntryView.onError(baseBean.getMsg());
                }
            }
        }
    };
    AboutUsInterface mAboutUsInterface = new AboutUsInterface() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mAboutUsView != null) {
                mAboutUsView.onError(e);
            }
        }

        @Override
        public void onNext(FreeEntryBean baseBean) {
            if (mAboutUsView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mAboutUsView.onAboutUsSuccess(baseBean);
                } else {
                    mAboutUsView.onError(baseBean.getMsg());
                }
            }
        }
    };
    UserInfoInterface mUserInfoInterface = new UserInfoInterface() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mUserInfoView != null) {
                mUserInfoView.onError(e);
            }
        }

        @Override
        public void onNext(UserInfoBean baseBean) {
            if (mUserInfoView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mUserInfoView.onUserInfoSuccess(baseBean);
                } else {
                    mUserInfoView.onError(baseBean.getMsg());
                }
            }
        }
    };
    SetCompeNameInterface mSetCompeNameInterface = new SetCompeNameInterface() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mSetCompeNameView != null) {
                mSetCompeNameView.onError(e);
            }
        }

        @Override
        public void onNext(BaseBean baseBean) {
            if (mSetCompeNameView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mSetCompeNameView.onSetCompeNameSuccess(baseBean);
                } else {
                    mSetCompeNameView.onError(baseBean.getMsg());
                }
            }
        }
    };
    SetCompeLogoInfoInterface mSetCompeLogoInfoInterface = new SetCompeLogoInfoInterface() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mSetCompeLogoView != null) {
                mSetCompeLogoView.onError(e);
            }
        }

        @Override
        public void onNext(BaseBean baseBean) {
            if (mSetCompeLogoView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mSetCompeLogoView.onSetCompeLogoSuccess(baseBean);
                } else {
                    mSetCompeLogoView.onError(baseBean.getMsg());
                }
            }
        }
    };
    BusinessPhoneListInterface mBusinessPhoneListInterface = new BusinessPhoneListInterface() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mBusinessPhoneListView != null) {
                mBusinessPhoneListView.onError(e);
            }
        }

        @Override
        public void onNext(BusinessPhoneListBean baseBean) {
            if (mBusinessPhoneListView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mBusinessPhoneListView.onSetCompeLogoSuccess(baseBean);
                } else {
                    mBusinessPhoneListView.onError(baseBean.getMsg());
                }
            }
        }
    };
    SetUserNameInterface mSetUserNameInterface = new SetUserNameInterface() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mSetUserNameView != null) {
                mSetUserNameView.onError(e);
            }
        }

        @Override
        public void onNext(BaseBean baseBean) {
            if (mSetUserNameView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mSetUserNameView.onSetUserNameSuccess(baseBean);
                } else {
                    mSetUserNameView.onError(baseBean.getMsg());
                }
            }
        }
    };
    SetUserHeaderInfoInterface mSetUserHeaderInfoInterface = new SetUserHeaderInfoInterface() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mSetUserHeaderView != null) {
                mSetUserHeaderView.onError(e);
            }
        }

        @Override
        public void onNext(BaseBean baseBean) {
            if (mSetUserHeaderView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mSetUserHeaderView.onSetUserNameSuccess(baseBean);
                } else {
                    mSetUserHeaderView.onError(baseBean.getMsg());
                }
            }
        }
    };

    public interface FeedbackInterface extends CommonInterface<BaseBean> {
    }

    public interface FreeEntryInterface extends CommonInterface<FreeEntryBean> {
    }

    public interface AboutUsInterface extends CommonInterface<FreeEntryBean> {
    }

    public interface UserInfoInterface extends CommonInterface<UserInfoBean> {
    }

    public interface SetCompeNameInterface extends CommonInterface<BaseBean> {
    }

    public interface SetCompeLogoInfoInterface extends CommonInterface<BaseBean> {
    }

    public interface BusinessPhoneListInterface extends CommonInterface<BusinessPhoneListBean> {
    }

    public interface SetUserNameInterface extends CommonInterface<BaseBean> {
    }

    public interface SetUserHeaderInfoInterface extends CommonInterface<BaseBean> {
    }
}
