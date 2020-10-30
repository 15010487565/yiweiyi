package com.yiweiyi.www.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yiweiyi.www.utils.AppManager;
import com.yiweiyi.www.utils.PrfUtils;
import com.yiweiyi.www.utils.ScreenUtils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

public class BaseActivity extends AppCompatActivity {

    //屏幕宽度
    public int w;
    //上下文
    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        QMUIStatusBarHelper.translucent(this);//状态栏透明
        QMUIStatusBarHelper.setStatusBarLightMode(this);//状态栏字体深色
        w = ScreenUtils.getScreenWidth(mContext);
        PrfUtils.setWidthPixel(w);
        AppManager.getAppManager().addActivity(this);
    }

    /**
     * 打开activity * @param ActivityClass
     */
    public void openActivity(Class<? extends Activity> ActivityClass) {
        Intent intent = new Intent(this, ActivityClass);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }
}
