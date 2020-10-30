package com.yiweiyi.www.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yiweiyi.www.R;
import com.yiweiyi.www.base.TitleBaseActivity;
import com.yiweiyi.www.ui.MainActivity;
import com.yiweiyi.www.utils.GlideCacheUtil;
import com.yiweiyi.www.utils.SpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends TitleBaseActivity {

    @BindView(R.id.tv_CacheSize)
    TextView tv_CacheSize;

    @Override
    public View getChildLayout() {
        return View.inflate(mContext, R.layout.activity_setting, null);
    }

    private void initView() {
//        setGoneBaseLeftMenu();
        setBaseTitle("设置");
//        setBaseRightImgMenu(R.drawable.close);
        tv_CacheSize.setText( GlideCacheUtil.getInstance().getCacheSize(this));
    }

    @Override
    public void baseBack(View v) {

    }

    @Override
    public void baseMenuTextClickListener(View v) {

    }

    @Override
    public void baseMenuImgClickListener(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();

    }


    @OnClick({R.id.ll_about, R.id.ll_Cache, R.id.ll_loginout})
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()){
            case R.id.ll_about:
                openActivity(AboutActivity.class);
                break;

            case R.id.ll_Cache:
                GlideCacheUtil.getInstance().clearImageAllCache(this,tv_CacheSize);

                break;
            case R.id.ll_loginout:
                SpUtils.loginOut();
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;

        }
    }
}
