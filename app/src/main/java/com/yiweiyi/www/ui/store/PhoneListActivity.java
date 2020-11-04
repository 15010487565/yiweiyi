package com.yiweiyi.www.ui.store;

import android.os.Bundle;
import android.view.View;
import android.widget.Scroller;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.yiweiyi.www.R;
import com.yiweiyi.www.adapter.store.PhoneListAdapter;
import com.yiweiyi.www.api.UrlAddr;
import com.yiweiyi.www.base.TitleBaseActivity;
import com.yiweiyi.www.model.PhoneListModel;
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
 * 2020/9/27
 * desc:联系电话
 */
public class PhoneListActivity extends TitleBaseActivity implements HttpInterface {

    @BindView(R.id.recycler_rv)
    RecyclerView recyclerRv;
    private PhoneListAdapter mPhoneListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        Scroller mScroller = new Scroller(mContext);
        // 获取触碰点所在的view
        mPhoneListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.delete_:
                        view.getScrollX();

                        break;
                }
            }
        });
        mPhoneListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
    }

    private void initView() {
        setBaseTitle(getResources().getString(R.string.contact_number));
        recyclerRv.setLayoutManager(new LinearLayoutManager(mContext));
        mPhoneListAdapter = new PhoneListAdapter(R.layout.item_phone_list, null);
        recyclerRv.setAdapter(mPhoneListAdapter);
        mPhoneListAdapter.addFooterView(View.inflate(mContext, R.layout.foot_phone_list, null));
        recyclerRv.addItemDecoration(getRecyclerViewDivider(R.drawable.inset_recyclerview_divider_1));
    }

    private void initData() {
        setLiist();
    }

    private void setLiist() {

        Map<String, String> params = new HashMap<String, String>();
        params.put("shop_id", PrfUtils.getMeShopId());
        OkHttpHelper.postAsyncHttp(this,1001,
                params, UrlAddr.GETSHOPPHONE,this);
    }

    @Override
    public View getChildLayout() {
        return View.inflate(mContext, R.layout.recycler_all, null);
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
        switch (requestCode){
            case 1001:
                Gson gson = new Gson();
                PhoneListModel phoneListModel = gson.fromJson(returnData, PhoneListModel.class);
                List<String> data = phoneListModel.getData();
                mPhoneListAdapter.setNewData(data);
                break;
        }
    }

    @Override
    public void onErrorResult(int requestCode, String returnMsg) {

    }
}
