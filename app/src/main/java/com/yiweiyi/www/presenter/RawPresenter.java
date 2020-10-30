package com.yiweiyi.www.presenter;

import com.yiweiyi.www.bean.raw.RawMaterialBean;
import com.yiweiyi.www.model.RawModel;
import com.yiweiyi.www.view.raw.BaseRawView;
import com.yiweiyi.www.view.raw.RawMaterialView;


/**
 * @author: zsh
 * 2020/10/11 0011
 * @Description:
 */
public class RawPresenter {

    BaseRawView mBaseRawView;
    RawModel mRawModel;
    RawMaterialView mRawMaterialView;

    public RawPresenter(BaseRawView mBaseRawView) {
        this.mBaseRawView = mBaseRawView;
        if (mBaseRawView instanceof RawMaterialView) {
            mRawMaterialView = (RawMaterialView) mBaseRawView;
        }

        mRawModel = new RawModel(mRawMaterialInterface);
    }

    //原料行情
    public void rawMaterial(int type, String year){
        mRawModel.rawMaterial(type, year);
    }

    RawMaterialInterface mRawMaterialInterface = new RawMaterialInterface(){
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mRawMaterialView != null) {
                mRawMaterialView.onError(e);
            }
        }

        @Override
        public void onNext(RawMaterialBean baseBean) {
            if (mRawMaterialView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mRawMaterialView.onRawMaterialSuccess(baseBean);
                } else {
                    mRawMaterialView.onError(baseBean.getMsg());
                }
            }
        }
    };

    public interface RawMaterialInterface extends CommonInterface<RawMaterialBean> {
    }
}
