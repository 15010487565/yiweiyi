package com.yiweiyi.www.wxapi;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.yiweiyi.www.api.Constants;
import com.yiweiyi.www.utils.PrfUtils;
import com.yiweiyi.www.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Constants.wx_api.handleIntent(getIntent(), this);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        Constants.wx_api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Log.d("TAG_WEIXN", "onFailure: ");
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    @Override
    public void onResp(BaseResp resp) {
        Log.d("TAG_WEIXN", "onFailure: ");
        //登录回调
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                String code = ((SendAuth.Resp) resp).code;
                //获取accesstoken
                getAccessToken(code);
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED://用户拒绝授权
            case BaseResp.ErrCode.ERR_USER_CANCEL://用户取消
            default:
                finish();
                break;
        }
    }
    private void getAccessToken(String code) {
        createProgressDialog();
        //获取授权
        StringBuffer loginUrl = new StringBuffer();
        loginUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token")
                .append("?appid=")
                .append(Constants.APP_ID)
                .append("&secret=")
                .append(Constants.AppSecret)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");
        Log.e("TAG_URL", loginUrl.toString());

        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(loginUrl.toString())
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("TAG_WEIXN", "onFailure: ");
                mProgressDialog.dismiss();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseInfo= response.body().string();
                Log.e("TAG_WEIXN", "onResponse: " +responseInfo);
                String access = null;
                String openId = null;
                try {
                    JSONObject jsonObject = new JSONObject(responseInfo);
                    access = jsonObject.getString("access_token");
                    openId = jsonObject.getString("openid");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                getUserInfo(access, openId);
            }
        });
    }
    ProgressDialog mProgressDialog;
    private void createProgressDialog() {
        mProgressDialog=new ProgressDialog(this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//转盘
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setTitle("提示");
        mProgressDialog.setMessage("登录中，请稍后");
        mProgressDialog.show();
    }
    private void getUserInfo(String access, String openid) {
        SharedPreferences preferences = PrfUtils.getSharePreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("openid", openid);
        editor.commit();

        String getUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access + "&openid=" + openid;
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(getUserInfoUrl)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("TAG_WEIXN", "onFailure: ");
                mProgressDialog.dismiss();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseInfo= response.body().string();
                Log.e("TAG_微信登录", "onResponse: " + responseInfo);

                SharedPreferences preferences = PrfUtils.getSharePreferences();
                SharedPreferences.Editor editor = preferences.edit();
                try {
                    JSONObject object = new JSONObject(responseInfo);
                    String openid = object.optString("openid");
                    editor.putString("openid", openid);
                    String nickname = object.optString("nickname");
                    editor.putString("nickname", nickname);
                    String unionid = object.optString("unionid");
                    editor.putString("unionid", unionid);
                    String headUrl = object.optString("headimgurl");
//                    headUrl.replace("https://thirdwx.qlogo.cn", "https://wx.qlogo.cn");
                    editor.putString("avatar", headUrl);
                    editor.putBoolean("isAuth", true);

                    editor.commit();
                    finish();
                    ToastUtils.showToast("授权成功，请绑定手机号!");
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.showToast("授权失败，请稍后重试!");
                }

                mProgressDialog.dismiss();
            }
        });
    }
}