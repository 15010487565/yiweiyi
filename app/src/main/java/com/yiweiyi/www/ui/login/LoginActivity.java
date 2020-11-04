package com.yiweiyi.www.ui.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qmuiteam.qmui.layout.QMUIButton;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.yiweiyi.www.R;
import com.yiweiyi.www.api.Constants;
import com.yiweiyi.www.base.BaseActivity;
import com.yiweiyi.www.base.BaseBean;
import com.yiweiyi.www.presenter.LoginPresenter;
import com.yiweiyi.www.ui.setting.AboutActivity;
import com.yiweiyi.www.utils.PrfUtils;
import com.yiweiyi.www.utils.RegexUtils;
import com.yiweiyi.www.utils.ToastUtils;
import com.yiweiyi.www.view.login.SendVerifiCodeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.yiweiyi.www.ui.setting.AboutActivity.ABOUT;
import static com.yiweiyi.www.ui.setting.AboutActivity.ABOUT_AGREEMENT;
import static com.yiweiyi.www.ui.setting.AboutActivity.ABOUT_PRIVACYPOLICY;

/**
 * @Author: zsh 2020/9/23
 * desc:验证码登陆界面
 */
public class LoginActivity extends BaseActivity implements SendVerifiCodeView {
    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.login_qbt)
    QMUIButton loginQbt;
    @BindView(R.id.title_tv)
    TextView titleTv;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginPresenter = new LoginPresenter(this);
        initView();
        initListener();
    }

    private void initView() {


        PrfUtils.setWexinAuth(false);
       SpannableString spannableString = new SpannableString(getString(R.string.registered_title));
        //设置部分文字点击事件
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(LoginActivity.this, AboutActivity.class);
                intent.putExtra(ABOUT,ABOUT_AGREEMENT);
                startActivity(intent);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(Color.parseColor("#295FA1"));            //设置可以点击文本部分的颜色
                ds.setUnderlineText(true);            //设置该文本部分是否显示超链接形式的下划线
            }
        };
        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(LoginActivity.this, AboutActivity.class);
                intent.putExtra(ABOUT,ABOUT_PRIVACYPOLICY);
                startActivity(intent);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(Color.parseColor("#295FA1"));            //设置可以点击文本部分的颜色
                ds.setUnderlineText(true);            //设置该文本部分是否显示超链接形式的下划线
            }
        };
        spannableString.setSpan(clickableSpan, 12, 18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(clickableSpan2, 19, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        titleTv.setText(spannableString);
        titleTv.setMovementMethod(LinkMovementMethod.getInstance());


    }

    private void initListener() {
        phoneEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() >= 11) {
                    loginQbt.setEnabled(true);
                }

            }
        });
    }

    @OnClick({R.id.toolbar_iv_back, R.id.login_qbt, R.id.wx_login_qbt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_iv_back:
                finish();
                break;
            case R.id.login_qbt: {
                boolean isPhone = RegexUtils.checkMobile(phoneEt.getText().toString());
                if (isPhone) {
                    //点击发送验证码后不可编辑输入框
                    mLoginPresenter.sendVerifiCode(phoneEt.getText().toString());
                    phoneEt.setEnabled(false);
                    loginQbt.setEnabled(false);
                } else {
                    ToastUtils.showToast( getString(R.string.please_enter_valid_phone));
                }
            }
            break;
            case R.id.wx_login_qbt:
                if (!Constants.wx_api.isWXAppInstalled()) {
                    ToastUtils.showToast("您的设备未安装微信客户端");
                } else {
                    weixinLogin();
                }

                break;
        }
    }

    public void weixinLogin() {
        // send oauth request
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        Constants.wx_api.sendReq(req);
    }

    @Override
    public void onSendVerifiCodeSuccess(BaseBean baseBean) {
        Intent intent = new Intent(mContext, VerifiCodeActivity.class);
        intent.putExtra(VerifiCodeActivity.PHONE, phoneEt.getText().toString());
        startActivity(intent);
        phoneEt.setEnabled(true);
        loginQbt.setEnabled(true);
    }

    @Override
    public void onError(String e) {
        ToastUtils.showToast(e);
        phoneEt.setEnabled(true);
        loginQbt.setEnabled(true);
    }
}
