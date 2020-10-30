package com.yiweiyi.www.utils;

import android.util.Log;

/**
 * @ProjectName: My Application
 * @Package: com.example.myapplication.utils
 * @ClassName: zsh
 * @Description: 日志显示类
 * @Author: liys
 * @CreateDate: 2020/9/23 13:14
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/9/23 13:14
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LogUtils {
    static String className;//类名
    static String methodName;//方法名
    static int lineNumber;//行数

    private LogUtils() { /* Protect from instantiations */ }

    private static boolean isDebuggable() {
        return true;
    }

    private static String createLog(String log) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(methodName);
        buffer.append("line Number:(").append(lineNumber).append(")").append(": log content： ");
        buffer.append(log);
        return buffer.toString();
    }

    private static void getMethodNames(StackTraceElement[] sElements) {
        className =  sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }

    public static void e(String message) {
        if (!isDebuggable())
            return; // Throwable instance must be created before any methods
        getMethodNames(new Throwable().getStackTrace());
        Log.e(className, createLog(message));
    }

    public static void i(String message) {
        if (!isDebuggable()) return;
        getMethodNames(new Throwable().getStackTrace());
        Log.i(className, createLog(message));
    }

    public static void d(String message) {
        if (!isDebuggable()) return;
        getMethodNames(new Throwable().getStackTrace());
        Log.d(className, createLog(message));
    }

    public static void v(String message) {
        if (!isDebuggable()) return;
        getMethodNames(new Throwable().getStackTrace());
        Log.v(className, createLog(message));
    }

    public static void w(String message) {
        if (!isDebuggable()) return;
        getMethodNames(new Throwable().getStackTrace());
        Log.w(className, createLog(message));
    }

    public static void wtf(String message) {
        if (!isDebuggable()) return;
        getMethodNames(new Throwable().getStackTrace());
        Log.wtf(className, createLog(message));
    }
}
