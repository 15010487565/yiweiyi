package com.yiweiyi.www.ui.store;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.yiweiyi.www.R;
import com.yiweiyi.www.api.UrlAddr;
import com.yiweiyi.www.base.CommonData;
import com.yiweiyi.www.utils.ImageUtils;
import com.yiweiyi.www.utils.PrfUtils;
import com.yiweiyi.www.view.CircleImageView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.xcd.com.mylibrary.PhotoActivity;
import www.xcd.com.mylibrary.help.OkHttpHelper;

/**
 * @Author: zsh
 * 2020/9/25
 * desc:基本信息
 */
public class BasicInfoActivity extends PhotoActivity {


    @BindView(R.id.head_img)
    CircleImageView headImg;

    @BindView(R.id.ll_shopName)
    LinearLayout ll_shopName;
    @BindView(R.id.compe_name_tv)
    TextView compe_name_tv;

    @BindView(R.id.ll_userName)
    LinearLayout ll_userName;
    @BindView(R.id.name_tv)
    TextView name_tv;

    @BindView(R.id.phone_bt)
    TextView phone_bt;

    @Override
    protected Object getTopbarTitle() {
        return R.string.basic_info;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_info);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        Intent intent = getIntent();
        String logo = intent.getStringExtra("logo");
        ImageUtils.setImage(headImg, logo, 3000, R.mipmap.ic_launcher);

        String shop_name = intent.getStringExtra("shop_name");
        compe_name_tv.setText(shop_name);

        String head = intent.getStringExtra("head");
        name_tv.setText(head);

    }


    private void initData() {
    }



    @OnClick({R.id.head_img, R.id.ll_shopName, R.id.phone_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head_img:
                getChoiceDialog().show();
                break;
//            case R.id.ll_shopName:{
//                Intent intent = new Intent(this, SingleEditActivity.class);
//                intent.putExtra("title","厂名");
//                intent.putExtra("key"," shop_name");
//                String shop_name = intent.getStringExtra("shop_name");
//                intent.putExtra("content",shop_name);
//                startActivityForResult(intent,1);
//            }
//
//                break;
            case R.id.phone_bt: {
                Intent intent = new Intent(this, PhoneListActivity.class);
                startActivity(intent);
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
                params.put("shop_id", PrfUtils.getMeShopId());
                params.put(key, content);
                OkHttpHelper.postAsyncHttp(this,1001,params, UrlAddr.EDITSHOPNAME,this);

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
    String content;
    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, String> paramsMaps) {
        switch (requestCode){
            case 1000:
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(returnData);
                    String imageUrl = jsonObject.optString("data");
                    if (imageUrl != null && imageUrl.startsWith("http")){
                        avatar = imageUrl;
                    }else {
                        avatar = CommonData.mainUrl + imageUrl;
                    }

                    String meShopId = PrfUtils.getMeShopId();
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("shop_id", meShopId);
                    params.put("logo", avatar);
                    OkHttpHelper.postAsyncHttp(this,1002,params, UrlAddr.EDITLOGO,this);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case 1001:
                compe_name_tv.setText(content);

                break;
            case 1002:
                ImageUtils.setImage(headImg, avatar, 3000, R.mipmap.ic_launcher);
                break;
        }
    }

}
