package com.yiweiyi.www.utils;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.yiweiyi.www.base.YWYApplication;

/**
 * @Author: zsh
 * 2020/9/29
 * desc:Toast工具类
 */
public class ToastUtils {

    private static Toast toast;

    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public static void showToast(String text) {
        showToast(text, Toast.LENGTH_SHORT);
    }

    private static void showToast(final String text, final int duration) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            show(text, duration);
        } else {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    show(text, duration);
                }
            });
        }
    }

    private static void show(String text, int duration) {
        if (toast != null) {
            toast.cancel();
        }
        Log.e("TAG_Toast","text="+text);
        toast = Toast.makeText(YWYApplication.getAppContext(), TextUtils.isEmpty(text)?"":text, duration);
        toast.show();
    }
}
