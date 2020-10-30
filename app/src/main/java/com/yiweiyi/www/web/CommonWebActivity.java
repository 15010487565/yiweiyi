package com.yiweiyi.www.web;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.yiweiyi.www.R;
import com.yiweiyi.www.base.TitleBaseActivity;
import com.yiweiyi.www.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author: zsh
 * 2020/9/27
 * desc:通用h5
 */
public class CommonWebActivity extends TitleBaseActivity {


    @BindView(R.id.seek_bar)
    ProgressBar seekBar;
    @BindView(R.id.web_view)
    WebView mWebView;
    public static String HEADE = "header";
    public static String URL = "url";
    private String mHeader;
    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mHeader = getIntent().getStringExtra(HEADE);
        mUrl = getIntent().getStringExtra(URL);
        setBaseTitle(mHeader);
        initWebView();
        initData();
        initSeekBar();
    }

    private void initSeekBar() {
        seekBar.setMax(100);
        if (seekBar.getVisibility() != View.VISIBLE) {
            seekBar.setVisibility(View.VISIBLE);
        }
    }

    private void initWebView() {
        LogUtils.e(Thread.currentThread().getName());
        WebSettings mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebSettings.setUseWideViewPort(true);//设置webview推荐使用的窗口
        mWebSettings.setLoadWithOverviewMode(true);//设置webview加载的页面的模式
        mWebSettings.setDisplayZoomControls(false);//隐藏webview缩放按钮
        mWebSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        mWebSettings.setAllowFileAccess(true); // 允许访问文件
        mWebSettings.setSupportZoom(true); // 支持缩放
        /**
         * 用WebView显示图片，可使用这个参数 设置网页布局类型：
         * 1、LayoutAlgorithm.NARROW_COLUMNS ：适应内容大小
         * 2、LayoutAlgorithm.SINGLE_COLUMN:适应屏幕，内容将自动缩放
         */
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        mWebView.loadData(mUrl, "text/html", "UTF-8");
        //设置不用系统浏览器打开,直接显示在当前Webview
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //设置WebChromeClient类
        mWebView.setWebChromeClient(new WebChromeClient() {


            //获取网站标题
            @Override
            public void onReceivedTitle(WebView view, String title) {

            }


            //获取加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    seekBar.setProgress(newProgress);
                } else {
                    seekBar.setVisibility(View.GONE);
                }
            }
        });
        //设置WebViewClient类
        mWebView.setWebViewClient(new WebViewClient() {
            //设置加载前的函数
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

            }

            //设置结束加载函数
            @Override
            public void onPageFinished(WebView view, String url) {

            }
        });
    }

    //销毁Webview
    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();

            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }

    private void initData() {
    }

    @Override
    public View getChildLayout() {
        return View.inflate(mContext, R.layout.activity_conf_web, null);
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
}
