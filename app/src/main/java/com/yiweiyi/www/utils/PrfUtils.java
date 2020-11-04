package com.yiweiyi.www.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.yiweiyi.www.base.YWYApplication;

/**
 * Created by zhangshaofang on 2015/8/6.
 */
public class PrfUtils {
    private static SharedPreferences mSharePreferences;

    public static SharedPreferences getSharePreferences() {

        mSharePreferences = YWYApplication.getAppContext().getSharedPreferences(YWYApplication.getAppContext().getPackageName() + "_preferences", Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);

        return mSharePreferences;
    }

    public static void savePrfparams(String key, String value) {
        SharedPreferences preferences = getSharePreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getPrfparams(String key) {
        SharedPreferences preferences = getSharePreferences();
        return preferences.getString(key, null);
    }
    public static int getPrfInt(String key) {
        SharedPreferences preferences = getSharePreferences();
        return preferences.getInt(key, 0);
    }

    public static void setWidthPixel(int widthPixel) {
        SharedPreferences preferences = getSharePreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("widthPixel", widthPixel);
        editor.apply();
    }

    public static int getWidthPixel() {
        SharedPreferences preferences = PrfUtils.getSharePreferences();
        return preferences.getInt("widthPixel", 0);
    }

    public static void setWexinAuth(boolean value) {
        SharedPreferences preferences = getSharePreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isAuth", value);
        editor.apply();
    }
    //是否为微信授权登录
    public static boolean isWexinAuth() {
        SharedPreferences preferences = PrfUtils.getSharePreferences();
        return preferences.getBoolean("isAuth", false);
    }

    public static void setHeadimgurl(String avatar) {
        SharedPreferences preferences = getSharePreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("avatar", avatar);
        editor.apply();
    }

    public static String getHeadimgurl() {
        SharedPreferences preferences = PrfUtils.getSharePreferences();
        return preferences.getString("avatar", "");
    }

    public static void setNickname(String nickname) {
        SharedPreferences preferences = getSharePreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nickname", nickname);
        editor.apply();
    }

    public static String getNickname() {
        SharedPreferences preferences = PrfUtils.getSharePreferences();
        return preferences.getString("nickname", "");
    }

    public static String getUnionid() {
        SharedPreferences preferences = PrfUtils.getSharePreferences();
        return preferences.getString("unionid", "");
    }
    public static String getOpenid() {
        SharedPreferences preferences = PrfUtils.getSharePreferences();
        return preferences.getString("openid", "");
    }
    public static String getPhone() {
        SharedPreferences preferences = PrfUtils.getSharePreferences();
        return preferences.getString("phone", "");
    }
    public static int isShop() {
        SharedPreferences preferences = PrfUtils.getSharePreferences();
        return preferences.getInt("is_shop", 0);
    }
    public static String getMeShopId() {
        SharedPreferences preferences = PrfUtils.getSharePreferences();
        return preferences.getString("me_shop_id", "");
    }
}
