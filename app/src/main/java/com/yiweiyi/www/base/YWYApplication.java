package com.yiweiyi.www.base;

import android.content.Context;
import android.util.Log;

import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.smtt.sdk.QbSdk;
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

        //非wifi情况下，主动下载x5内核
        QbSdk.setDownloadWithoutWifi(true);
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.e(  "TAG_x5内核","arg0="+arg0);
            }

            @Override
            public void onCoreInitFinished() {

            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
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
