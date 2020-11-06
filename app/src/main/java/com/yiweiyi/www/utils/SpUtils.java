package com.yiweiyi.www.utils;

import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

/**
 * @Author: zsh
 * 2020/9/29
 * desc:
 */
public class SpUtils {

    public static void saveUserInfo(Map<String, String> stringMap) {
        SharedPreferences preferences = PrfUtils.getSharePreferences();
        SharedPreferences.Editor editor = preferences.edit();
        for (Map.Entry<String, String> entry : stringMap.entrySet()) {
            LogUtils.e(entry.getKey() + "===" + entry.getValue());
            if (entry.getValue() != null) {
                editor.putString(entry.getKey(), entry.getValue());
            }
        }
        editor.apply();
    }

    public static void saveUserInfo(String key, Object obj) {
        SharedPreferences preferences = PrfUtils.getSharePreferences();
        SharedPreferences.Editor editor = preferences.edit();

        // key 不为null时再存入，否则不存储
        if (key != null) {
            if (obj instanceof Integer) {
                editor.putInt(key, (Integer) obj);
            } else if (obj instanceof Long) {
                editor.putLong(key, (Long) obj);
            } else if (obj instanceof Boolean) {
                editor.putBoolean(key, (Boolean) obj);
            } else if (obj instanceof Float) {
                editor.putFloat(key, (Float) obj);
            } else if (obj instanceof Set) {
                editor.putStringSet(key, (Set<String>) obj);
            } else if (obj instanceof String) {
                editor.putString(key, String.valueOf(obj));
            }
        }
        editor.apply();
    }


//    /**
//     * 获取个人信息
//     *
//     * @return
//     */
//    public static SigninBean.DataBean getUserInfo() {
//
//        SigninBean.DataBean userInfoBean = new SigninBean.DataBean();
//        userInfoBean.setId(Integer.parseInt(PrfUtils.getPrfparams("id")));
//        userInfoBean.setIs_shop(PrfUtils.getPrfInt("is_shop"));
//        userInfoBean.setNickname(PrfUtils.getPrfparams("nickname"));
//        userInfoBean.setAvatar(PrfUtils.getPrfparams("avatar"));
//        userInfoBean.setShop_id(PrfUtils.getPrfInt("shop_id"));
//        userInfoBean.setPhone(PrfUtils.getPrfparams("phone"));
//        return userInfoBean;
//    }

    public static String getUserID() {
        SharedPreferences preferences = PrfUtils.getSharePreferences();
        return preferences.getString("id", "");
    }

    public static void loginOut() {
        SharedPreferences preferences = PrfUtils.getSharePreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("id", "");
        editor.putString("openid", "");
        editor.putString("nickname", "");
        editor.putString("unionid", "");
        editor.putString("avatar", "");
        editor.putBoolean("isAuth", false);
        editor.putInt("is_shop", 0);
        editor.putString("me_shop_id", "");

        editor.apply();
    }

    public static void addString( String key, String value) {
        SharedPreferences preferences = PrfUtils.getSharePreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(String key) {
        SharedPreferences preferences = PrfUtils.getSharePreferences();
        return preferences.getString(key, "");
    }


    public static String getApkUrl() {
        SharedPreferences preferences = PrfUtils.getSharePreferences();
        return preferences.getString("apkUrl", "");
    }

    public static void setApkUrl( String path) {
        SharedPreferences preferences = PrfUtils.getSharePreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("apkUrl", path);
        editor.apply();
    }
}
