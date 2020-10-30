package com.yiweiyi.www.ui.store;

import android.os.Bundle;
import android.view.View;

import com.yiweiyi.www.R;
import com.yiweiyi.www.base.TitleBaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author: zsh
 * 2020/9/25
 * desc:基本信息
 */
public class BasicInfoActivity extends TitleBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public View getChildLayout() {
        return View.inflate(mContext, R.layout.activity_basic_info, null);
    }


    private void initData() {
    }

    private void initView() {
        setBaseTitle(getResources().getString(R.string.basic_info));
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

    @OnClick({R.id.head_img, R.id.more_img, R.id.phone_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head_img:
                break;
            case R.id.more_img:
                break;
            case R.id.phone_bt: {
                openActivity(PhoneListActivity.class);
            }
            break;
        }
    }
}
