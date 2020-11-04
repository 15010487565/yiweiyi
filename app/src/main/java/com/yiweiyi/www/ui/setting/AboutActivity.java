package com.yiweiyi.www.ui.setting;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.yiweiyi.www.R;
import com.yiweiyi.www.api.UrlAddr;
import com.yiweiyi.www.utils.HtmlUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.help.OkHttpHelper;
import www.xcd.com.mylibrary.http.HttpInterface;
import www.xcd.com.mylibrary.utils.ToastUtil;

public class AboutActivity extends SimpleTopbarActivity implements
        View.OnClickListener, HttpInterface {
    public static String ABOUT = "ABOUT";
    public static int ABOUT_TYPE = 0;
    public static int ABOUT_AGREEMENT = 2;
    public static int ABOUT_PRIVACYPOLICY = 3;
    WebView webView;
    @Override
    protected Object getTopbarTitle() {
        return "关于我们";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webView = findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        webView.clearCache(true);
        settings.setCacheMode(android.webkit.WebSettings.LOAD_NO_CACHE);
        settings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        settings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        settings.setDisplayZoomControls(true); //隐藏原生的缩放控件
        settings.setBlockNetworkImage(false);//解决图片不显示
        settings.setLoadsImagesAutomatically(true); //支持自动加载图片
        settings.setDefaultTextEncodingName("gb2312");//设置编码格式
        settings.setCacheMode(android.webkit.WebSettings.LOAD_NO_CACHE);
        settings.setJavaScriptEnabled(true);
        settings.setBlockNetworkImage(false); // 解决图片不显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        settings.setTextZoom( 100);// textZoom:100表示正常，120表示文字放大1.2倍
//        TextView tv_details = findViewById(R.id.tv_details);
        Intent intent = getIntent();
        int intExtra = intent.getIntExtra(ABOUT,0);
        if (intExtra == ABOUT_TYPE){
            resetTopbarTitle("关于我们");
            OkHttpHelper.getRestfulHttp(this,1000,
                    UrlAddr.ABOUT_US,this);
        }else if (intExtra == ABOUT_AGREEMENT){
            resetTopbarTitle("用户协议");
            OkHttpHelper.getRestfulHttp(this,1001,
                    UrlAddr.USER_AGREEMENT,this);
        }else {
            resetTopbarTitle("隐私政策");
            OkHttpHelper.getRestfulHttp(this,1002,
                    UrlAddr.PRIVACYPOLICY,this);
        }

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){

        }
    }

    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, String> paramsMaps) {
        switch (requestCode){
            case 1000:
            case 1001:
            case 1002:
                try {
                    JSONObject jsonObject = new JSONObject(returnData);
                    String data = jsonObject.optString("data");
//                    if (data.contains("<p>")){
                    HtmlUtils.getHtmlData(data,webView);
//                    }else {
//                        tv_details.setText(content);
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;


        }
    }

    @Override
    public void onErrorResult(int errorCode, String errorExcep) {
        ToastUtil.showToast(errorExcep);
    }
}
