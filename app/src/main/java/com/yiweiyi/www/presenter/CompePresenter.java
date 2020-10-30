package com.yiweiyi.www.presenter;

import com.yiweiyi.www.base.BaseBean;
import com.yiweiyi.www.bean.compe.CompeDetailsBean;
import com.yiweiyi.www.bean.compe.ProdCataBean;
import com.yiweiyi.www.bean.compe.ReliableListBean;
import com.yiweiyi.www.bean.compe.ShareImgBean;
import com.yiweiyi.www.model.CompeModel;
import com.yiweiyi.www.view.compe.AddReliableView;
import com.yiweiyi.www.view.compe.BaseCompeView;
import com.yiweiyi.www.view.compe.CertificationView;
import com.yiweiyi.www.view.compe.CompeDetailsView;
import com.yiweiyi.www.view.compe.DelReliableView;
import com.yiweiyi.www.view.compe.ProdCataView;
import com.yiweiyi.www.view.compe.RealPictureView;
import com.yiweiyi.www.view.compe.ReliableListView;
import com.yiweiyi.www.view.compe.ShareImgView;

import retrofit2.http.Field;

/**
 * @Author: zsh
 * 2020/10/8
 * desc:
 */
public class CompePresenter {

    BaseCompeView mBaseCompeView;
    CompeModel mCompeModel;
    CompeDetailsView mCompeDetailsView;
    ProdCataView mProdCataView;
    RealPictureView mRealPictureView;
    CertificationView mCertificationView;
    ShareImgView mShareImgView;
    ReliableListView mReliableListView;
    AddReliableView mAddReliableView;
    DelReliableView mDelReliableView;

    public CompePresenter(BaseCompeView mBaseCompeView) {
        this.mBaseCompeView = mBaseCompeView;
        if (mBaseCompeView instanceof CompeDetailsView) {
            mCompeDetailsView = (CompeDetailsView) mBaseCompeView;
        }
        if (mBaseCompeView instanceof ProdCataView) {
            mProdCataView = (ProdCataView) mBaseCompeView;
        }
        if (mBaseCompeView instanceof RealPictureView) {
            mRealPictureView = (RealPictureView) mBaseCompeView;
        }
        if (mBaseCompeView instanceof CertificationView) {
            mCertificationView = (CertificationView) mBaseCompeView;
        }
        if (mBaseCompeView instanceof ShareImgView) {
            mShareImgView = (ShareImgView) mBaseCompeView;
        }
        if (mBaseCompeView instanceof ReliableListView) {
            mReliableListView = (ReliableListView) mBaseCompeView;
        }
        if (mBaseCompeView instanceof AddReliableView) {
            mAddReliableView = (AddReliableView) mBaseCompeView;
        }
        if (mBaseCompeView instanceof DelReliableView) {
            mDelReliableView = (DelReliableView) mBaseCompeView;
        }
        mCompeModel = new CompeModel(mCompeDetailsInterface, mProdCataInterface,
                mRealPictureInterface, mCertificationInterface,
                mShareImgInterface, mReliableListInterface,
                mAddReliableInterface, mDelReliableInterface);
    }

    /**
     * 商家详情表
     *
     * @param shop_id 商家ID
     * @param user_id
     */
    public void compeDetails(int shop_id, String user_id) {
        mCompeModel.compeDetails(shop_id, user_id);
    }

    //产品图册
    public void prodCata(int shop_id) {
        mCompeModel.prodCata(shop_id, "");
    }

    //实景图册列表
    public void realPicture(int shop_id) {
        mCompeModel.realPicture(shop_id, "");
    }

    //资质证书图册列表
    public void certification(int shop_id) {
        mCompeModel.certification(shop_id, "");
    }

    //获取分享图片
    public void shareImg(int shop_id) {
        mCompeModel.shareImg(shop_id);
    }

    //靠谱列表
    public void reliableList(@Field("shop_id") int shop_id) {
        mCompeModel.reliableList(shop_id);
    }

    //添加 靠谱
    public void addReliable(@Field("shop_id") int shop_id,
                            @Field("user_id") String user_id) {
        mCompeModel.addReliable(shop_id, user_id);
    }

    //取消 靠谱
    public void delReliable(@Field("shop_id") int shop_id,
                            @Field("user_id") String user_id) {
        mCompeModel.delReliable(shop_id, user_id);
    }

    CompeDetailsInterface mCompeDetailsInterface = new CompeDetailsInterface() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mCompeDetailsView != null) {
                mCompeDetailsView.onError(e);
            }
        }

        @Override
        public void onNext(CompeDetailsBean baseBean) {
            if (mCompeDetailsView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mCompeDetailsView.onCompeDetailsSuccess(baseBean);
                } else {
                    mCompeDetailsView.onError(baseBean.getMsg());
                }
            }
        }
    };
    ProdCataInterface mProdCataInterface = new ProdCataInterface() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mProdCataView != null) {
                mProdCataView.onError(e);
            }
        }

        @Override
        public void onNext(ProdCataBean baseBean) {
            if (mProdCataView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mProdCataView.onProdCataSuccess(baseBean);
                } else {
                    mProdCataView.onError(baseBean.getMsg());
                }
            }
        }
    };
    RealPictureInterface mRealPictureInterface = new RealPictureInterface() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mRealPictureView != null) {
                mRealPictureView.onError(e);
            }
        }

        @Override
        public void onNext(ProdCataBean baseBean) {
            if (mRealPictureView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mRealPictureView.onRealPictureSuccess(baseBean);
                } else {
                    mRealPictureView.onError(baseBean.getMsg());
                }
            }
        }
    };
    CertificationInterface mCertificationInterface = new CertificationInterface() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mCertificationView != null) {
                mCertificationView.onError(e);
            }
        }

        @Override
        public void onNext(ProdCataBean baseBean) {
            if (mCertificationView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mCertificationView.onCertificationSuccess(baseBean);
                } else {
                    mCertificationView.onError(baseBean.getMsg());
                }
            }
        }
    };
    ShareImgInterface mShareImgInterface = new ShareImgInterface() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mShareImgView != null) {
                mShareImgView.onError(e);
            }
        }

        @Override
        public void onNext(ShareImgBean baseBean) {
            if (mShareImgView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mShareImgView.onShareImgSuccess(baseBean);
                } else {
                    mShareImgView.onError(baseBean.getMsg());
                }
            }
        }
    };
    ReliableListInterface mReliableListInterface = new ReliableListInterface() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mReliableListView != null) {
                mReliableListView.onError(e);
            }
        }

        @Override
        public void onNext(ReliableListBean baseBean) {
            if (mReliableListView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mReliableListView.onReliableListSuccess(baseBean);
                } else {
                    mReliableListView.onError(baseBean.getMsg());
                }
            }
        }
    };
    AddReliableInterface mAddReliableInterface = new AddReliableInterface() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mAddReliableView != null) {
                mAddReliableView.onError(e);
            }
        }

        @Override
        public void onNext(BaseBean baseBean) {
            if (mAddReliableView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mAddReliableView.onAddReliableSuccess(baseBean);
                } else {
                    mAddReliableView.onError(baseBean.getMsg());
                }
            }
        }
    };
    DelReliableInterface mDelReliableInterface = new DelReliableInterface() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mDelReliableView != null) {
                mDelReliableView.onError(e);
            }
        }

        @Override
        public void onNext(BaseBean baseBean) {
            if (mDelReliableView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mDelReliableView.onDelReliableSuccess(baseBean);
                } else {
                    mDelReliableView.onError(baseBean.getMsg());
                }
            }
        }
    };

    public interface CompeDetailsInterface extends CommonInterface<CompeDetailsBean> {
    }

    public interface ProdCataInterface extends CommonInterface<ProdCataBean> {
    }

    public interface RealPictureInterface extends CommonInterface<ProdCataBean> {
    }

    public interface CertificationInterface extends CommonInterface<ProdCataBean> {
    }

    public interface ShareImgInterface extends CommonInterface<ShareImgBean> {
    }

    public interface ReliableListInterface extends CommonInterface<ReliableListBean> {
    }

    public interface AddReliableInterface extends CommonInterface<BaseBean> {
    }

    public interface DelReliableInterface extends CommonInterface<BaseBean> {
    }
}
