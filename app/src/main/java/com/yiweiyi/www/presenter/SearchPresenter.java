package com.yiweiyi.www.presenter;

import com.yiweiyi.www.base.BaseBean;
import com.yiweiyi.www.bean.search.CommonAreasListBean;
import com.yiweiyi.www.bean.search.ProximitySearchBean;
import com.yiweiyi.www.bean.search.SearchCompeBean;
import com.yiweiyi.www.bean.search.SearchRecordsBean;
import com.yiweiyi.www.model.SearchModel;
import com.yiweiyi.www.view.search.AddCommonAreasView;
import com.yiweiyi.www.view.search.BaseSearchView;
import com.yiweiyi.www.view.search.ClearRecordsView;
import com.yiweiyi.www.view.search.CommonAreasListView;
import com.yiweiyi.www.view.search.DelCommonAreasView;
import com.yiweiyi.www.view.search.DeleteRecordView;
import com.yiweiyi.www.view.search.ProximitySearchView;
import com.yiweiyi.www.view.search.SearchCompeView;
import com.yiweiyi.www.view.search.SearchRecordsView;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zsh
 * 2020/10/8
 * desc:搜索Presenter
 */
public class SearchPresenter {
    BaseSearchView mBaseSearchView;
    SearchModel mSearchModel;
    SearchRecordsView mSearchRecordsView;
    ClearRecordsView mClearRecordsView;
    DeleteRecordView mDeleteRecordView;
    ProximitySearchView mProximitySearchView;
    SearchCompeView mSearchCompeView;
    AddCommonAreasView mAddCommonAreasView;
    DelCommonAreasView mDelCommonAreasView;
    CommonAreasListView mCommonAreasListView;

    public SearchPresenter(BaseSearchView mBaseSearchView) {
        this.mBaseSearchView = mBaseSearchView;
        if (mBaseSearchView instanceof SearchRecordsView) {
            mSearchRecordsView = (SearchRecordsView) mBaseSearchView;
        }
        if (mBaseSearchView instanceof ClearRecordsView) {
            mClearRecordsView = (ClearRecordsView) mBaseSearchView;
        }
        if (mBaseSearchView instanceof DeleteRecordView) {
            mDeleteRecordView = (DeleteRecordView) mBaseSearchView;
        }
        if (mBaseSearchView instanceof ProximitySearchView) {
            mProximitySearchView = (ProximitySearchView) mBaseSearchView;
        }
        if (mBaseSearchView instanceof SearchCompeView) {
            mSearchCompeView = (SearchCompeView) mBaseSearchView;
        }
        if (mBaseSearchView instanceof AddCommonAreasView) {
            mAddCommonAreasView = (AddCommonAreasView) mBaseSearchView;
        }
        if (mBaseSearchView instanceof DelCommonAreasView) {
            mDelCommonAreasView = (DelCommonAreasView) mBaseSearchView;
        }
        if (mBaseSearchView instanceof CommonAreasListView) {
            mCommonAreasListView = (CommonAreasListView) mBaseSearchView;
        }
        mSearchModel = new SearchModel(mSearchRecordsInterface, mClearRecordsInterface,
                mDeleteRecordInterface, mProximitySearchInterface,
                mSearchCompeInterface, mAddCommonAreasInterface,
                mDelCommonAreasInterface, mCommonAreasListInterface);
    }

    /**
     * 搜索记录
     *
     * @param user_id 用户id
     */
    public void searchRecords(String user_id) {
        mSearchModel.searchRecords(user_id);
    }

    /**
     * 用户清空搜索记录
     *
     * @param user_id 用户id
     */
    public void clearRecords(String user_id) {
        mSearchModel.clearRecords(user_id);
    }

    /**
     * 用户删除搜索记录
     *
     * @param user_id
     * @param ids     要删除的记录ID,数组格式,例子: [1,2,3]
     */
    public void deleteRecord(String user_id, int[] ids) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("ids.xml", ids);
        mSearchModel.deleteRecord(map);
    }

    /**
     * 返回接近关键词
     *
     * @param search 用户输入的内容
     */
    public void proximitySearch(String search) {
        mSearchModel.proximitySearch(search);
    }

    /**
     * 搜索接口
     *
     * @param search  搜索内容
     * @param user_id 用户ID,非必填
     * @param area    地区,非必填
     */
    public void searchCompe(String search, String user_id, String area) {
        mSearchModel.searchCompe(search, user_id, area);
    }

    //用户添加常用地区
    public void addCommonAreas(String user_id,
                               String used_area) {
        mSearchModel.addCommonAreas(user_id, used_area);
    }

    //用户删除常用地区
    public void delCommonAreas(String user_id,
                               String area) {
        mSearchModel.delCommonAreas(user_id, area);
    }

    //返回搜索厂家包含地区
    public void commonAreasList(String user_id,
                                String area) {
        mSearchModel.commonAreasList(user_id, area);
    }


    SearchRecordsInterface mSearchRecordsInterface = new SearchRecordsInterface() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mSearchRecordsView != null) {
                mSearchRecordsView.onError(e);
            }
        }

        @Override
        public void onNext(SearchRecordsBean baseBean) {
            if (mSearchRecordsView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mSearchRecordsView.onSearchRecordsSuccess(baseBean);
                } else {
                    mSearchRecordsView.onError(baseBean.getMsg());
                }
            }
        }
    };
    ClearRecordsInterface mClearRecordsInterface = new ClearRecordsInterface() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mClearRecordsView != null) {
                mClearRecordsView.onError(e);
            }
        }

        @Override
        public void onNext(BaseBean baseBean) {
            if (mClearRecordsView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mClearRecordsView.onSearchRecordsSuccess(baseBean);
                } else {
                    mClearRecordsView.onError(baseBean.getMsg());
                }
            }
        }
    };
    DeleteRecordInterface mDeleteRecordInterface = new DeleteRecordInterface() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mDeleteRecordView != null) {
                mDeleteRecordView.onError(e);
            }
        }

        @Override
        public void onNext(BaseBean baseBean) {
            if (mDeleteRecordView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mDeleteRecordView.onDeleteRecordSuccess(baseBean);
                } else {
                    mDeleteRecordView.onError(baseBean.getMsg());
                }
            }
        }
    };
    ProximitySearchInterface mProximitySearchInterface = new ProximitySearchInterface() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mProximitySearchView != null) {
                mProximitySearchView.onError(e);
            }
        }

        @Override
        public void onNext(ProximitySearchBean baseBean) {
            if (mProximitySearchView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mProximitySearchView.onProximitySearchSuccess(baseBean);
                } else {
                    mProximitySearchView.onError(baseBean.getMsg());
                }
            }
        }
    };
    SearchCompeInterface mSearchCompeInterface = new SearchCompeInterface() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mSearchCompeView != null) {
                mSearchCompeView.onError(e);
            }
        }

        @Override
        public void onNext(SearchCompeBean baseBean) {
            if (mSearchCompeView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mSearchCompeView.onSearchCompeSuccess(baseBean);
                } else {
                    mSearchCompeView.onError(baseBean.getMsg());
                }
            }
        }
    };
    AddCommonAreasInterface mAddCommonAreasInterface = new AddCommonAreasInterface() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mAddCommonAreasView != null) {
                mAddCommonAreasView.onError(e);
            }
        }

        @Override
        public void onNext(BaseBean baseBean) {
            if (mAddCommonAreasView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mAddCommonAreasView.onAddCommonAreasSuccess(baseBean);
                } else {
                    mAddCommonAreasView.onError(baseBean.getMsg());
                }
            }
        }
    };
    DelCommonAreasInterface mDelCommonAreasInterface = new DelCommonAreasInterface() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mDelCommonAreasView != null) {
                mDelCommonAreasView.onError(e);
            }
        }

        @Override
        public void onNext(BaseBean baseBean) {
            if (mDelCommonAreasView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mDelCommonAreasView.onDelCommonAreasSuccess(baseBean);
                } else {
                    mDelCommonAreasView.onError(baseBean.getMsg());
                }
            }
        }
    };
    CommonAreasListInterface mCommonAreasListInterface = new CommonAreasListInterface() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(String e) {
            if (mCommonAreasListView != null) {
                mCommonAreasListView.onError(e);
            }
        }

        @Override
        public void onNext(CommonAreasListBean baseBean) {
            if (mCommonAreasListView != null) {
                if ("1".equals(baseBean.getCode())) {
                    mCommonAreasListView.onCommonAreasListSuccess(baseBean);
                } else {
                    mCommonAreasListView.onError(baseBean.getMsg());
                }
            }
        }
    };


    public interface SearchRecordsInterface extends CommonInterface<SearchRecordsBean> {
    }

    public interface ClearRecordsInterface extends CommonInterface<BaseBean> {
    }

    public interface DeleteRecordInterface extends CommonInterface<BaseBean> {
    }

    public interface ProximitySearchInterface extends CommonInterface<ProximitySearchBean> {
    }

    public interface SearchCompeInterface extends CommonInterface<SearchCompeBean> {
    }

    public interface AddCommonAreasInterface extends CommonInterface<BaseBean> {
    }

    public interface DelCommonAreasInterface extends CommonInterface<BaseBean> {
    }

    public interface CommonAreasListInterface extends CommonInterface<CommonAreasListBean> {
    }
}
