package com.yiweiyi.www.ui.store;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.yiweiyi.www.R;
import com.yiweiyi.www.base.TitleBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author: zsh
 * 2020/9/25
 * desc:店铺管理
 */
public class StoreManageActivity extends TitleBaseActivity {

    @BindView(R.id.head_img)
    ImageView headImg;
    @BindView(R.id.compe_name_tv)
    TextView compeNameTv;
    @BindView(R.id.area_tv)
    TextView areaTv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.head_cl)
    ConstraintLayout headCl;
    @BindView(R.id.number_how_saw_me_tv)
    TextView numberHowSawMeTv;
    @BindView(R.id.number_call_records_tv)
    TextView numberCallRecordsTv;
    @BindView(R.id.number_reliable_tv)
    TextView numberReliableTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public View getChildLayout() {
        return View.inflate(mContext, R.layout.activity_store_manage, null);
    }


    private void initData() {
    }

    private void initView() {
        setGoneBaseLeftMenu();
        setBaseTitle(getResources().getString(R.string.store_manage));
        setBaseRightImgMenu(R.drawable.close);
    }

    @Override
    public void baseBack(View v) {

    }

    @Override
    public void baseMenuTextClickListener(View v) {

    }

    @Override
    public void baseMenuImgClickListener(View v) {
        finish();
    }

    @OnClick({R.id.head_cl, R.id.who_saw_me_ll, R.id.call_records_ll, R.id.reliable_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head_cl: {
                //基本信息
                openActivity(BasicInfoActivity.class);
            }

            break;
            case R.id.who_saw_me_ll: {
                //谁看过我
                openActivity(CallRecordsActivity.class);
            }
            break;
            case R.id.call_records_ll:
            {
                //通话记录
                openActivity(CallRecordsActivity.class);
            }
                break;
            case R.id.reliable_ll:
            {
                //靠谱
                openActivity(CallRecordsActivity.class);
            }
                break;
        }
    }
}
