package com.yiweiyi.www.me;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.yiweiyi.www.R;
import com.yiweiyi.www.api.UrlAddr;
import com.yiweiyi.www.base.CommonData;
import com.yiweiyi.www.func.AccountHeadFunc;
import com.yiweiyi.www.func.AccountNameFunc;
import com.yiweiyi.www.utils.PrfUtils;
import com.yiweiyi.www.utils.SpUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import www.xcd.com.mylibrary.PhotoActivity;
import www.xcd.com.mylibrary.func.BaseFunc;
import www.xcd.com.mylibrary.help.OkHttpHelper;


public class UserinfoActivity extends PhotoActivity {

    private static Class<?>[] systemFuncArray = {
            AccountHeadFunc.class,//头像
            AccountNameFunc.class,//昵称
//            AccountIntroductionFunc.class,//介绍
//            AccountGenderFunc.class,//性别
//            AccountBirthdayFunc.class,//性别
//            AccountRegionFunc.class,//地区
//            AccountBabyStateFunc.class//宝宝状态
    };
    /**
     * 功能对象
     */
    private Hashtable<Integer, BaseFunc> htFunc = new Hashtable<>();
    /**
     * 获得系统功能列表
     */
    protected Class<?>[] getSystemFuncArray() {
        return systemFuncArray;
    }

    private LinearLayout systemFuncView;
    private LinearLayout systemFuncList;

    String content;

    @Override
    protected Object getTopbarTitle() {
        return "个人信息";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
    }

    @Override
    protected void afterSetContentView() {
        super.afterSetContentView();
        systemFuncView = findViewById(R.id.me_system_func_view);
        systemFuncList = findViewById(R.id.me_system_func_list);
        // 初始化系统功能
        initSystemFunc();
//        AccountRegionFunc baseFunc = (AccountRegionFunc) htFunc.get(R.id.account_birthday);
//        baseFunc.bindView();
    }
    /**
     * 初始化系统功能
     */
    protected void initSystemFunc() {
        Class<?>[] systemFuncs = getSystemFuncArray();
        // 功能列表为空,隐藏区域
        if (systemFuncs == null || systemFuncs.length == 0) {
            systemFuncView.setVisibility(View.GONE);
            return;
        }
        // 初始化功能
        boolean isSeparator = false;
        for (Class<?> systemFunc : systemFuncs) {
            // view
            View funcView = getFuncView(systemFunc, isSeparator);
            if (funcView != null) {
                // 点击事件
                funcView.setOnClickListener(this);
                // 加入页面
                systemFuncList.addView(funcView);
                isSeparator = true;
            }
        }
        // 设置列表显示
        systemFuncList.setVisibility(View.VISIBLE);
    }

    /**
     * 获得功能View
     *
     * @return
     */
    private View getFuncView(Class<?> funcClazz, boolean isSeparator) {
        BaseFunc func = BaseFunc.newInstance(funcClazz, this);
        if (func == null) {
            return null;
        }
        htFunc.put(func.getFuncId(), func);
        // view
        return func.initFuncView(isSeparator);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            default:
                // func
                BaseFunc func = htFunc.get(v.getId());
                // 处理点击事件
                if (func != null) {
                    func.onclick();
                }
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {

            case 101: {
                //返回对象集合：如果你需要了解图片的宽、高、大小、用户是否选中原图选项等信息，可以用这个
                ArrayList<Photo> resultPhotos = data.getParcelableArrayListExtra(EasyPhotos.RESULT_PHOTOS);
                //返回图片地址集合时如果你需要知道用户选择图片时是否选择了原图选项，用如下方法获取
                boolean selectedOriginal = data.getBooleanExtra(EasyPhotos.RESULT_SELECTED_ORIGINAL, false);
                if (resultPhotos != null && resultPhotos.size() > 0) {
                    Photo photo = resultPhotos.get(0);
                    uploadPhoto(photo.path);
                }
            }
            break;
            case 1:
                String key = data.getStringExtra("key");
                content = data.getStringExtra("content");
                Log.e("TAG_","key="+key+";content="+content);
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", SpUtils.getUserID());
                params.put(key, content);
                OkHttpHelper.postAsyncHttp(this,1001,params, UrlAddr.EDITNICKNAME,this);

                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    private void uploadPhoto(String imgPath) {
        OkHttpHelper.postAsyncImage(this,1000,imgPath, UrlAddr.UPLOADIMG,this);
    }

    String avatar;
    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, String> paramsMaps) {
        switch (requestCode){
            case 1000:
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(returnData);
                    String imageUrl = jsonObject.optString("data");
                    avatar = CommonData.mainUrl + imageUrl;
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("user_id", SpUtils.getUserID());
                    params.put("avatar", avatar);
                    OkHttpHelper.postAsyncHttp(this,1002,params, UrlAddr.EDITAVATAR,this);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case 1001:

                PrfUtils.setNickname(content);
                AccountNameFunc baseFunc = (AccountNameFunc) htFunc.get(R.id.account_name);
                baseFunc.resetRightName();
                break;
            case 1002:
                PrfUtils.setHeadimgurl(avatar);
                AccountHeadFunc headFunc = (AccountHeadFunc) htFunc.get(R.id.account_head);
                headFunc.refreshHead();
                break;
        }
    }

}
