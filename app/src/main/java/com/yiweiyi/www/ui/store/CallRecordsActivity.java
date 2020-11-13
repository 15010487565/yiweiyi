package com.yiweiyi.www.ui.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.yiweiyi.www.R;
import com.yiweiyi.www.adapter.store.CallRecordsAdapter;
import com.yiweiyi.www.api.UrlAddr;
import com.yiweiyi.www.base.TitleBaseActivity;
import com.yiweiyi.www.model.CallRecordsModel;
import com.yiweiyi.www.utils.PrfUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.xcd.com.mylibrary.help.OkHttpHelper;
import www.xcd.com.mylibrary.http.HttpInterface;

/**
 * @Author: zsh
 * 2020/9/25
 * desc: 靠谱 谁看过我 通话记录
 */
public class CallRecordsActivity extends TitleBaseActivity implements HttpInterface, BaseQuickAdapter.RequestLoadMoreListener  {

    @BindView(R.id.tv_app)
    TextView tv_app;
    @BindView(R.id.tv_xcx)
    TextView tv_xcx;
    @BindView(R.id.left_tv)
    TextView leftTv;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.recycler_rv)
    RecyclerView recyclerRv;

//    @BindView(R.id.head_ll)
//    LinearLayout head_ll;
//    @BindView(R.id.number_tv)
//    TextView number_tv;
    int page = 1;
    private CallRecordsAdapter mCallRecordsAdapter;

    @Override
    public View getChildLayout() {
        return View.inflate(mContext, R.layout.activity_call_records, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        /**
         * 1:谁看过我
         * 2:通话记录
         * 3:靠谱
         */
        String type = intent.getStringExtra("type");
        if ("1".equals(type)){
            setBaseTitle(getResources().getString(R.string.who_saw_me));
//            head_ll.setVisibility(View.VISIBLE);
//            number_tv.setVisibility(View.GONE);
            String meShopId = PrfUtils.getMeShopId();
            Map<String, String> params = new HashMap<String, String>();
            params.put("page", String.valueOf(page));
            params.put("shop_id", meShopId +"");
            OkHttpHelper.postAsyncHttp(this,1001,
                    params, UrlAddr.BROWSE,this);
        }else if ("2".equals(type)){
            setBaseTitle(getResources().getString(R.string.call_records));
//            head_ll.setVisibility(View.VISIBLE);
//            number_tv.setVisibility(View.GONE);
            String meShopId = PrfUtils.getMeShopId();
            Map<String, String> params = new HashMap<String, String>();
            params.put("page", String.valueOf(page));
            params.put("shop_id", meShopId +"");
            OkHttpHelper.postAsyncHttp(this,1002,
                    params, UrlAddr.CALLLOG,this);
        }else {
            setBaseTitle(getResources().getString(R.string.reliable));
//            head_ll.setVisibility(View.GONE);
//            number_tv.setVisibility(View.VISIBLE);
            String meShopId = PrfUtils.getMeShopId();
            Map<String, String> params = new HashMap<String, String>();
            params.put("shop_id", meShopId +"");
            params.put("page", String.valueOf(page));
            OkHttpHelper.postAsyncHttp(this,1003,
                    params, UrlAddr.LIKE,this);
        }

    }

    private void initView() {

        setBaseBarDarkColor();
        recyclerRv.setLayoutManager(new LinearLayoutManager(mContext));
        mCallRecordsAdapter = new CallRecordsAdapter(R.layout.item_call_records, null);
        mCallRecordsAdapter.setOnLoadMoreListener(this);
        recyclerRv.setAdapter(mCallRecordsAdapter);
        recyclerRv.addItemDecoration(getRecyclerViewDivider(R.drawable.inset_recyclerview_divider_1));
    }

    @Override
    public void baseBack(View v) {
        finish();
    }

    @Override
    public void baseMenuTextClickListener(View v) {

    }

    @Override
    public void baseMenuImgClickListener(View v) {

    }

    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, String> paramsMaps) {
        switch (requestCode) {
            case 1001:{
                Gson gson = new Gson();
                CallRecordsModel callRecordsModel = gson.fromJson(returnData, CallRecordsModel.class);
                CallRecordsModel.DataBean data = callRecordsModel.getData();

                tv_app.setText("APP："+data.getApp_num());
                tv_xcx.setText("小程序："+data.getXcx_num());
                int browse_total = data.getTotal();
                leftTv.setText("总："+browse_total);
                int today = data.getToday();
                rightTv.setText("今日："+today);

                List<CallRecordsModel.DataBean.ListBean> list = data.getList();
                if (page == 1){
                    if (list != null && list.size() > 0){
                        mCallRecordsAdapter.setNewData(list);
                        mCallRecordsAdapter.loadMoreComplete();
                    }else {
                        View emptyView = getLayoutInflater().inflate(R.layout.view_empty, null);
                        mCallRecordsAdapter.setEmptyView(emptyView);
                        mCallRecordsAdapter.loadMoreEnd();
                    }
                }else {
                    if (list != null && list.size() > 0){
                        mCallRecordsAdapter.addData(list);
                        mCallRecordsAdapter.loadMoreComplete();
                    }else {
                        mCallRecordsAdapter.loadMoreEnd();
                    }
                }

            }

                break;
            case 1002:{
                Gson gson = new Gson();
                CallRecordsModel callRecordsModel = gson.fromJson(returnData, CallRecordsModel.class);
                CallRecordsModel.DataBean data = callRecordsModel.getData();

                tv_app.setText("APP："+data.getApp_num());
                tv_xcx.setText("小程序："+data.getXcx_num());
                int browse_total = data.getTotal();
                 leftTv.setText("总："+browse_total);
                int today = data.getToday();
                rightTv.setText("今日："+today);

                List<CallRecordsModel.DataBean.ListBean> list = data.getList();

                if (page == 1){
                    if (list != null && list.size() > 0){
                        mCallRecordsAdapter.setNewData(list);
                        mCallRecordsAdapter.loadMoreComplete();
                    }else {
                        View emptyView = getLayoutInflater().inflate(R.layout.view_empty, null);
                        mCallRecordsAdapter.setEmptyView(emptyView);
                        mCallRecordsAdapter.loadMoreEnd();
                    }
                }else {
                    if (list != null && list.size() > 0){
                        mCallRecordsAdapter.addData(list);
                        mCallRecordsAdapter.loadMoreComplete();
                    }else {
                        mCallRecordsAdapter.loadMoreEnd();
                    }
                }
            }

                break;
            case 1003:
                Gson gson = new Gson();
                CallRecordsModel callRecordsModel = gson.fromJson(returnData, CallRecordsModel.class);
                CallRecordsModel.DataBean data = callRecordsModel.getData();
//                number_tv.setText(data.getTotal()+"人觉得这家公司很靠谱");
                tv_app.setText("APP："+data.getApp_num());
                tv_xcx.setText("小程序："+data.getXcx_num());
                int browse_total = data.getTotal();
                leftTv.setText("总："+browse_total);
                int today = data.getToday();
                rightTv.setText("今日："+today);

                List<CallRecordsModel.DataBean.ListBean> list = data.getList();
                if (page == 1){
                    if (list != null && list.size() > 0){
                        mCallRecordsAdapter.setNewData(list);
                        mCallRecordsAdapter.loadMoreComplete();
                    }else {
                        View emptyView = getLayoutInflater().inflate(R.layout.view_empty, null);
                        mCallRecordsAdapter.setEmptyView(emptyView);
                        mCallRecordsAdapter.loadMoreEnd();
                    }
                }else {
                    if (list != null && list.size() > 0){
                        mCallRecordsAdapter.addData(list);
                        mCallRecordsAdapter.loadMoreComplete();
                    }else {
                        mCallRecordsAdapter.loadMoreEnd();
                    }
                }
                break;

        }

    }

    @Override
    public void onErrorResult(int requestCode, String returnMsg) {

    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        initData();
    }
}
