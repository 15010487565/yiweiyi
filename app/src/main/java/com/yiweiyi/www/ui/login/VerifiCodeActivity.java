package com.yiweiyi.www.ui.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yiweiyi.www.R;
import com.yiweiyi.www.api.ApiManager;
import com.yiweiyi.www.base.BaseBean;
import com.yiweiyi.www.base.TitleBaseActivity;
import com.yiweiyi.www.bean.login.SigninBean;
import com.yiweiyi.www.presenter.LoginPresenter;
import com.yiweiyi.www.utils.AppManager;
import com.yiweiyi.www.utils.PrfUtils;
import com.yiweiyi.www.utils.SpUtils;
import com.yiweiyi.www.utils.ToastUtils;
import com.yiweiyi.www.utils.VerifyCodeView;
import com.yiweiyi.www.view.login.SigninView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author: zsh
 * 2020/9/28
 * desc:验证码
 */
public class VerifiCodeActivity extends TitleBaseActivity implements SigninView {


    @BindView(R.id.code_edit)
    VerifyCodeView codeEdit;
    public static String PHONE = "phone";
    @BindView(R.id.newSend_tv)
    TextView newSendTv;
    @BindView(R.id.phone_tv)
    TextView tvPhone;

    private String mPhone;
    private LoginPresenter mLoginPresenter;
    private TimeCount mTime;
    private long timeL = 60000;
    @Override
    public View getChildLayout() {
        return View.inflate(mContext, R.layout.activity_verifi_code, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mPhone = getIntent().getStringExtra(PHONE);
        tvPhone.setText(TextUtils.isEmpty(mPhone)?"手机号":("\t+86\t"+mPhone));
        mLoginPresenter = new LoginPresenter(this);
        initView();
        initListener();
    }

    private void initListener() {

        codeEdit.setInputCompleteListener(new VerifyCodeView.InputCompleteListener() {

            @Override
            public void inputComplete() {
                boolean wexinAuth = PrfUtils.isWexinAuth();
                Log.e("TAG_登陆方式",wexinAuth?"微信":"手机号");
                if (!wexinAuth){
                    mLoginPresenter.signin(mPhone, codeEdit.getEditContent());
                }else {
                    String unionid = PrfUtils.getUnionid();
                    String openid = PrfUtils.getOpenid();
                    String nickname = PrfUtils.getNickname();
                    String headimgurl = PrfUtils.getHeadimgurl();
                    ApiManager.getInstance().wxBindPhone(unionid,
                            openid,
                            nickname,
                            headimgurl,
                            mPhone)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<SigninBean>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(SigninBean baseBean) {

                                }

                            });
                }

            }

            @Override
            public void invalidContent() {

            }

        });


    }

    @OnClick({R.id.toolbar_iv_back, R.id.newSend_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_iv_back:
                if (mTime != null) {
                    mTime.onFinish();
                }
                finish();
                break;
            case R.id.newSend_tv:
                ApiManager.getInstance().sendVerifiCode(mPhone)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<BaseBean>() {
                            @Override
                            public void onCompleted() {
                                initView();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(BaseBean baseBean) {

                            }

                        });
                break;
        }
    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            newSendTv.setText(millisUntilFinished / 1000 + "秒后可重新获取");
        }

        @Override
        public void onFinish() {
            newSendTv.setText("重新获取验证码");
        }
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

    private void initView() {
        //六十秒倒计时
        mTime = new TimeCount(timeL, 1000);
        mTime.start();
    }

    @Override
    public void onSigninSuccess(SigninBean baseBean) {
        HashMap<String, String> hashMap = new HashMap<>();
        //存储用户基本
        hashMap.put("id", String.valueOf(baseBean.getData().getId()));
        hashMap.put("nickname", baseBean.getData().getNickname());
        if (baseBean.getData().getAvatar() != null) {
            hashMap.put("avatar", baseBean.getData().getAvatar());
        }
        hashMap.put("phone", baseBean.getData().getPhone());
        SpUtils.saveUserInfo( hashMap);
        SpUtils.saveUserInfo( "is_shop", baseBean.getData().getIs_shop());
        SpUtils.saveUserInfo( "shop_id", baseBean.getData().getShop_id());
        AppManager.getAppManager().finishAllExpectMainActivity();
    }

    @Override
    public void onError(String e) {
        ToastUtils.showToast(e);
    }
}
