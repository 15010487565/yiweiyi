package com.yiweiyi.www.ui.details;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;

import com.tencent.smtt.sdk.TbsReaderView;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.yiweiyi.www.R;

import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;



public class WebX5FileActivity extends SimpleTopbarActivity implements TbsReaderView.ReaderCallback{

//    private RelativeLayout mRelativeLayout;
//    private TbsReaderView mTbsReaderView;
//    private TextView tvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_x5_file);
        init();
    }


    public void init() {
        resetTopbarTitle("图片");
//        mTbsReaderView = new TbsReaderView(this, this);
//        mRelativeLayout = (RelativeLayout) findViewById(R.id.tbsView);
//        mRelativeLayout.addView(mTbsReaderView,new RelativeLayout.LayoutParams(-1,-1));
        Intent intent = getIntent();
//        String title = intent.getStringExtra("title");
//        tvTitle.setText(title);


        String url = intent.getStringExtra("url");
//        String name = intent.getStringExtra("name");
//        displayFile(url,name);

        WebView mWebView = (WebView) findViewById(R.id.webView);
        WebSettings settings = mWebView.getSettings();
        mWebView.clearCache(true);
        settings.setCacheMode(android.webkit.WebSettings.LOAD_NO_CACHE);
        settings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        settings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        settings.setDisplayZoomControls(true); //隐藏原生的缩放控件
        settings.setBlockNetworkImage(false);//解决图片不显示
        settings.setLoadsImagesAutomatically(true); //支持自动加载图片
        settings.setDefaultTextEncodingName("gb2312");//设置编码格式
        settings.setCacheMode(android.webkit.WebSettings.LOAD_NO_CACHE);
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        settings.setBlockNetworkImage(false); // 解决图片不显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        settings.setTextZoom(100);// textZoom:100表示正常，120表示文字放大1.2倍


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ) {
            mWebView.setWebContentsDebuggingEnabled(true);
        }
        mWebView.setWebChromeClient(new WebChromeClient() {
            // For Android 3.0+
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {

            }

            // For Android < 3.0
            public void openFileChooser(ValueCallback<Uri> uploadMsgs) {

            }

            // For Android  > 4.1.1
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {

            }

            // For Android  >= 5.0
            public boolean onShowFileChooser(com.tencent.smtt.sdk.WebView webView,
                                             ValueCallback<Uri[]> filePathCallback,
                                             WebChromeClient.FileChooserParams fileChooserParams) {

                return true;
            }

        });

        //该界面打开更多链接
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                return true;
            }
        });
        mWebView.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            default:
                super.onClick(v);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mTbsReaderView.onStop();
    }
//    private String tbsReaderTemp =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + "wanke";;
//    private void displayFile(String filePath, String fileName) {
//    Log.e("TAG_查看文件","filePath="+filePath);
//        Log.e("TAG_查看文件","fileName="+fileName);
//        Log.e("TAG_查看文件","tbsReaderTemp="+tbsReaderTemp);
    //        //增加下面一句解决没有TbsReaderTemp文件夹存在导致加载文件失败
//        String bsReaderTemp = tbsReaderTemp;
//        File bsReaderTempFile =new File(bsReaderTemp);
//        if (!bsReaderTempFile.exists()) {
//            Log.d("print","准备创建/TbsReaderTemp！！");
//            boolean mkdir = bsReaderTempFile.mkdir();
//            if(!mkdir){
//                Log.d("print","创建/TbsReaderTemp失败！！！！！");
//            }
//        }
//        Bundle bundle = new Bundle();
//        bundle.putString("filePath", filePath);
//        bundle.putString("tempPath", tbsReaderTemp);
//        boolean result = mTbsReaderView.preOpen(getFileType(fileName), false);
////        Log.d("print","查看文档---"+result);
//        if (result) {
//            mTbsReaderView.openFile(bundle);
//        }else{
//
//        }
//    }

    private String getFileType(String paramString) {
        String str = "";

        if (TextUtils.isEmpty(paramString)) {
//            Log.d("print", "paramString---->null");
            return str;
        }
//        Log.d("print", "paramString:" + paramString);
        int i = paramString.lastIndexOf('.');
        if (i <= -1) {
//            Log.d("print", "i <= -1");
            return str;
        }

        str = paramString.substring(i + 1);
//        Log.d("print", "paramString.substring(i + 1)------>" + str);
        return str;
    }

    public static void show(Context context, String url) {
//        Log.e("TAG_X5文件","url="+url);
        Intent intent = new Intent(context, WebX5FileActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("path", url);
        intent.putExtras(bundle);
        context.startActivity(intent);

    }

    @Override
    public void onCallBackAction(Integer integer, Object o, Object o1) {

    }
}
