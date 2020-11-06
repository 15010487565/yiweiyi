package com.yiweiyi.www.model;

import android.util.Log;

import com.yiweiyi.www.api.ApiManager;
import com.yiweiyi.www.api.Constants;
import com.yiweiyi.www.api.EventBusMsg;
import com.yiweiyi.www.base.BaseBean;
import com.yiweiyi.www.bean.search.CommonAreasListBean;
import com.yiweiyi.www.bean.search.ProximitySearchBean;
import com.yiweiyi.www.bean.search.SearchCompeBean;
import com.yiweiyi.www.bean.search.SearchRecordsBean;
import com.yiweiyi.www.presenter.SearchPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author: zsh
 * 2020/10/8
 * desc:
 */
public class SearchModel {

    SearchPresenter.SearchRecordsInterface mSearchRecordsInterface;
    SearchPresenter.ClearRecordsInterface mClearRecordsInterface;
    SearchPresenter.DeleteRecordInterface mDeleteRecordInterface;
    SearchPresenter.ProximitySearchInterface mProximitySearchInterface;
    SearchPresenter.SearchCompeInterface mSearchCompeInterface;
    SearchPresenter.AddCommonAreasInterface mAddCommonAreasInterface;
    SearchPresenter.DelCommonAreasInterface mDelCommonAreasInterface;
    SearchPresenter.CommonAreasListInterface mCommonAreasListInterface;

    public SearchModel(SearchPresenter.SearchRecordsInterface mSearchRecordsInterface,
                       SearchPresenter.ClearRecordsInterface mClearRecordsInterface,
                       SearchPresenter.DeleteRecordInterface mDeleteRecordInterface,
                       SearchPresenter.ProximitySearchInterface mProximitySearchInterface,
                       SearchPresenter.SearchCompeInterface mSearchCompeInterface,
                       SearchPresenter.AddCommonAreasInterface mAddCommonAreasInterface,
                       SearchPresenter.DelCommonAreasInterface mDelCommonAreasInterface,
                       SearchPresenter.CommonAreasListInterface mCommonAreasListInterface) {
        this.mSearchRecordsInterface = mSearchRecordsInterface;
        this.mClearRecordsInterface = mClearRecordsInterface;
        this.mDeleteRecordInterface = mDeleteRecordInterface;
        this.mProximitySearchInterface = mProximitySearchInterface;
        this.mSearchCompeInterface = mSearchCompeInterface;
        this.mAddCommonAreasInterface = mAddCommonAreasInterface;
        this.mDelCommonAreasInterface = mDelCommonAreasInterface;
        this.mCommonAreasListInterface = mCommonAreasListInterface;
    }

    public void searchRecords(String user_id) {
        ApiManager.getInstance().searchRecords(user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchRecordsBean>() {
                    @Override
                    public void onCompleted() {
                        mSearchRecordsInterface.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mSearchRecordsInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(SearchRecordsBean baseBean) {
                        mSearchRecordsInterface.onNext(baseBean);
                    }

                });
    }

    public void clearRecords(String user_id) {
        ApiManager.getInstance().clearRecords(user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBean>() {
                    @Override
                    public void onCompleted() {
                        mClearRecordsInterface.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mClearRecordsInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        mClearRecordsInterface.onNext(baseBean);
                    }

                });
    }

    public void deleteRecord(Map<String, Object> map) {
        ApiManager.getInstance().deleteRecord(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBean>() {
                    @Override
                    public void onCompleted() {
                        mDeleteRecordInterface.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mDeleteRecordInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        mDeleteRecordInterface.onNext(baseBean);
                    }

                });
    }

    public void proximitySearch(String search) {
        ApiManager.getInstance().proximitySearch(search)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProximitySearchBean>() {
                    @Override
                    public void onCompleted() {
                        mProximitySearchInterface.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mProximitySearchInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(ProximitySearchBean baseBean) {
                        mProximitySearchInterface.onNext(baseBean);
                    }

                });
    }

    public void searchCompe(String search,
                            String user_id,
                            String area,
                            String page) {
        ApiManager.getInstance().searchCompe(search, user_id, area,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchCompeBean>() {
                    @Override
                    public void onCompleted() {
                        mSearchCompeInterface.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mSearchCompeInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(SearchCompeBean baseBean) {
                        mSearchCompeInterface.onNext(baseBean);
                    }

                });
    }

    public void addCommonAreas(String user_id,
                               String used_area) {
        ApiManager.getInstance().addCommonAreas(user_id, used_area)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBean>() {
                    @Override
                    public void onCompleted() {
                        mAddCommonAreasInterface.onCompleted();
                        Log.e("TAG_刷新","店家列表");
                        EventBusMsg messageEvent = new EventBusMsg();
                        messageEvent.setCode(Constants.REFRESHSEARCH);
                        EventBus.getDefault().post(messageEvent);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mAddCommonAreasInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        mAddCommonAreasInterface.onNext(baseBean);
                    }

                });
    }
    public void delCommonAreas(String user_id,
                               String area) {
        ApiManager.getInstance().delCommonAreas(user_id, area)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBean>() {
                    @Override
                    public void onCompleted() {
                        mDelCommonAreasInterface.onCompleted();
                        Log.e("TAG_刷新","店家列表");
                        EventBusMsg messageEvent = new EventBusMsg();
                        messageEvent.setCode(Constants.REFRESHSEARCH);
                        EventBus.getDefault().post(messageEvent);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mDelCommonAreasInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        mDelCommonAreasInterface.onNext(baseBean);
                    }

                });
    }
    public void commonAreasList(String user_id,
                                String area) {
        ApiManager.getInstance().commonAreasList(user_id, area)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CommonAreasListBean>() {
                    @Override
                    public void onCompleted() {
                        mCommonAreasListInterface.onCompleted();

                    }

                    @Override
                    public void onError(Throwable e) {
                        mCommonAreasListInterface.onError(e.toString());
                    }

                    @Override
                    public void onNext(CommonAreasListBean baseBean) {
                        mCommonAreasListInterface.onNext(baseBean);
                    }

                });
    }

}
