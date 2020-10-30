package com.yiweiyi.www.ui.store;

import android.os.Bundle;
import android.view.View;
import android.widget.Scroller;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiweiyi.www.R;
import com.yiweiyi.www.adapter.store.PhoneListAdapter;
import com.yiweiyi.www.base.TitleBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author: zsh
 * 2020/9/27
 * desc:联系电话
 */
public class PhoneListActivity extends TitleBaseActivity {

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
    }

    private void initData() {
        setLiist();
    }

    private void setLiist() {
        recyclerRv.setLayoutManager(new LinearLayoutManager(mContext));
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            dataList.add("");
        }
        mPhoneListAdapter = new PhoneListAdapter(R.layout.item_phone_list, dataList);
        recyclerRv.setAdapter(mPhoneListAdapter);
        mPhoneListAdapter.addFooterView(View.inflate(mContext, R.layout.foot_phone_list, null));
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
}
