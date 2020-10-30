package com.yiweiyi.www.ui.store;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yiweiyi.www.R;
import com.yiweiyi.www.adapter.store.CallRecordsAdapter;
import com.yiweiyi.www.base.TitleBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author: zsh
 * 2020/9/25
 * desc: 靠谱 谁看过我 通话记录
 */
public class CallRecordsActivity extends TitleBaseActivity {

    @BindView(R.id.left_tv)
    TextView leftTv;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.recycler_rv)
    RecyclerView recyclerRv;
    private CallRecordsAdapter mCallRecordsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
    setList();
    }

    private void setList() {
        recyclerRv.setLayoutManager(new LinearLayoutManager(mContext));
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            dataList.add("");
        }
        mCallRecordsAdapter = new CallRecordsAdapter(R.layout.item_call_records, dataList);
        recyclerRv.setAdapter(mCallRecordsAdapter);
    }

    private void initView() {
        setBaseTitle(getResources().getString(R.string.call_records));
        setBaseBarDarkColor();
    }


    @Override
    public View getChildLayout() {
        return View.inflate(mContext, R.layout.activity_call_records, null);
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
