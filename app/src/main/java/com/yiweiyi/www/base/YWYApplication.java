package com.yiweiyi.www.base;

import android.content.Context;
import android.util.DisplayMetrics;

import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yiweiyi.www.api.Constants;

import www.xcd.com.mylibrary.base.application.XCDApplication;

/**
 * @Author: zsh 2020/9/23
 * @Description: 应用配置
 */
public class YWYApplication extends XCDApplication {

    //设计稿屏幕宽度
    public static int DRAFTWIDTH = 414;

    private static Context context;

    public static Context getAppContext() {
        return YWYApplication.context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        YWYApplication.context = getApplicationContext();
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        Constants.wx_api = WXAPIFactory.createWXAPI(getApplicationContext(), Constants.APP_ID, true);
        // 将应用的appId注册到微信
        Constants.wx_api.registerApp(Constants.APP_ID);


    }

    private String getAppInfo() {
        try {
            String pkName = this.getPackageName();
            String versionName = this.getPackageManager().getPackageInfo(
                    pkName, 0).versionName;
            int versionCode = this.getPackageManager()
                    .getPackageInfo(pkName, 0).versionCode;
            return pkName + "   " + versionName + "  " + versionCode;
        } catch (Exception e) {
        }
        return null;
    }

}
