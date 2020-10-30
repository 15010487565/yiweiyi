package com.yiweiyi.www.ui.setting;

import android.os.Bundle;
import android.view.View;
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
//        TextView tv_details = findViewById(R.id.tv_details);
        OkHttpHelper.getRestfulHttp(this,1000,
                UrlAddr.ABOUT_US,this);

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


        }
    }

    @Override
    public void onErrorResult(int errorCode, String errorExcep) {
        ToastUtil.showToast(errorExcep);
    }
}
