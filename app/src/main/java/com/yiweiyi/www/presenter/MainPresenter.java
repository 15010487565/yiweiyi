package com.yiweiyi.www.presenter;

import com.yiweiyi.www.bean.main.HomeCategoryBean;
import com.yiweiyi.www.model.MainModel;
import com.yiweiyi.www.view.main.BaseMainView;
import com.yiweiyi.www.view.main.HomeCategoryView;

/**
 * @Author: zsh
 * 2020/9/25
 * desc:
 */
public class MainPresenter {
    HomeCategoryView mHomeCategoryView;
    BaseMainView mBaseMainView;
    MainModel mMainModel;

    public MainPresenter(BaseMainView mBaseMainView) {
        this.mBaseMainView = mBaseMainView;
        if (mBaseMainView instanceof HomeCategoryView) {
            mHomeCategoryView = (HomeCategoryView) mBaseMainView;
        }
        mMainModel = new MainModel(homeCategoryInterface);
    }

    /**
     * 获取首页系列列表
     */
    public void homeCategory() {
        mMainModel.homeCategory();
    }


    HomeCategoryInterface homeCategoryInterface = new HomeCategoryInterface() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(String e) {
            if (mHomeCategoryView != null) {
                mHomeCategoryView.onError(e);
            }
        }

        @Override
        public void onNext(HomeCategoryBean baseBean) {
            if (mHomeCategoryView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mHomeCategoryView.onHomeCategorySuccess(baseBean);
                } else {
                    mHomeCategoryView.onError(baseBean.getMsg());
                }
            }
        }
    };

    public interface HomeCategoryInterface extends CommonInterface<HomeCategoryBean> {
    }

}
