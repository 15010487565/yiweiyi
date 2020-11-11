package com.yiweiyi.www.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.yiweiyi.www.R;
import com.yiweiyi.www.api.UrlAddr;
import com.yiweiyi.www.utils.HtmlUtils;
import com.yiweiyi.www.utils.SpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.help.OkHttpHelper;
import www.xcd.com.mylibrary.http.HttpInterface;
import www.xcd.com.mylibrary.utils.ToastUtil;

public class WebActivity extends SimpleTopbarActivity implements
        View.OnClickListener, HttpInterface {
    public static String WEB_TYPE = "WEB_TYPE" ;
    WebView webView;
    @Override
    protected Object getTopbarTitle() {
        return "免费入住";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Intent intent = getIntent();
        String type = intent.getStringExtra(WEB_TYPE);
        String key_name = intent.getStringExtra("key_name");
        webView = findViewById(R.id.webView);
        if (WEB_TYPE.equals(type)){
            resetTopbarTitle("线缆百科");
            HtmlUtils.getHtmlData(key_name,webView);
        }else if ("电阻表".equals(key_name)){
            resetTopbarTitle("线缆百科");
            initData(key_name);
        }else {
            OkHttpHelper.getRestfulHttp(this,1000,
                    UrlAddr.SETTLEDTIPS,this);
        }
    }

    private void initData(String search_param) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("search", search_param);
        params.put("user_id", SpUtils.getUserID());
        params.put("area", "");
        params.put("page ", "0");
        OkHttpHelper.postAsyncHttp(this, 1000,
                params, UrlAddr.SEARCH_INDEX, this);
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
//            case 3:
//                try {
//
//                    JSONObject jsonObject = new JSONObject(returnData);
//                    String data1 = jsonObject.optString("data");
//                    Intent intent = new Intent(mContext, WebActivity.class);
//                    intent.putExtra("type", "SEARCH3");
//                    intent.putExtra("data", data1);
//                    startActivity(intent);
//                    finish();
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;


        }
    }

    @Override
    public void onErrorResult(int errorCode, String errorExcep) {
        ToastUtil.showToast(errorExcep);
    }
}
