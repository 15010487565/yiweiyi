package com.yiweiyi.www.ui.login;

import android.os.Bundle;
import android.view.View;

import com.yiweiyi.www.R;
import com.yiweiyi.www.base.BaseActivity;
import com.qmuiteam.qmui.alpha.QMUIAlphaButton;
import com.qmuiteam.qmui.alpha.QMUIAlphaTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName: zsh
 * @Author: liys 2020/9/23
 * desc: 短信验证码登陆
 */
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.protocol_tv)
    QMUIAlphaTextView protocolTv;
    @BindView(R.id.login_qbt)
    QMUIAlphaButton loginQbt;
    @BindView(R.id.wx_login_qbt)
    QMUIAlphaButton wxLoginQbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initDate();
    }

    private void initDate() {

    }


    @OnClick({R.id.switch_number_bt, R.id.login_qbt, R.id.wx_login_qbt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.switch_number_bt:{
                //登陆
            }
                break;
            case R.id.login_qbt:
                break;
            case R.id.wx_login_qbt:
                break;
        }
    }
}
