package com.yiweiyi.www.utils;

import androidx.appcompat.app.AppCompatActivity;

import com.yiweiyi.www.ui.MainActivity;
import com.yiweiyi.www.ui.login.RegisterActivity;

import java.util.Stack;

/**
 * @ProjectName: My Application
 * @Package: com.example.myapplication.utils
 * @ClassName: zsh
 * @Description: activity常用
 * @Author: liys
 * @CreateDate: 2020/9/23 13:16
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/9/23 13:16
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AppManager {
    // Activity栈
    private static Stack<AppCompatActivity> activityStack;
    // 单例模式
    private static AppManager instance;

    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(AppCompatActivity activity) {
        if (activityStack == null) {
            activityStack = new Stack<AppCompatActivity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public AppCompatActivity currentActivity() {
        AppCompatActivity activity = null;
        if (activityStack != null) {
            try {
                activity = activityStack.lastElement();
            }catch (Exception e){

            }
        }
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        AppCompatActivity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(AppCompatActivity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (AppCompatActivity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 回到短信登录界面
     */
    public void finishAllExpectLoginActivity() {
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)) {
                if (activityStack.get(i) instanceof RegisterActivity) {

                } else {
                    activityStack.get(i).finish();
                }
            }
        }
        activityStack.clear();
    }


    /**
     * 回到主界面
     */
    public void finishAllExpectMainActivity() {
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)) {
                if (activityStack.get(i) instanceof MainActivity) {

                } else {
                    activityStack.get(i).finish();
                }
            }
        }
        activityStack.clear();
    }

}
