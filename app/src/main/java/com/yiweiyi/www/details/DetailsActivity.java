package com.yiweiyi.www.details;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qmuiteam.qmui.alpha.QMUIAlphaButton;
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.yiweiyi.www.R;
import com.yiweiyi.www.adapter.store.GalleryAdapter;
import com.yiweiyi.www.adapter.store.GridAdapter;
import com.yiweiyi.www.api.Constants;
import com.yiweiyi.www.api.UrlAddr;
import com.yiweiyi.www.base.CommonData;
import com.yiweiyi.www.model.DetailsModel;
import com.yiweiyi.www.utils.HtmlUtils;
import com.yiweiyi.www.utils.ImageUtils;
import com.yiweiyi.www.utils.ScreenUtils;
import com.yiweiyi.www.utils.ShareDialog;
import com.yiweiyi.www.utils.SpUtils;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.help.OkHttpHelper;

import static com.yiweiyi.www.details.ProdcataActivity.ALNUMTYPE;

public class DetailsActivity extends SimpleTopbarActivity {

    @BindView(R.id.back_bt)
    LinearLayout back_bt;
    @BindView(R.id.share_bt)
    QMUIAlphaImageButton share_bt;

    @BindView(R.id.banner)
    Banner banner;

    @BindView(R.id.compe_name_tv)
    TextView compe_name_tv;
    @BindView(R.id.location_tv)
    TextView location_tv;

    @BindView(R.id.visitToday_tv)
    TextView visitToday_tv;
    @BindView(R.id.numberVisitors_tv)
    TextView numberVisitors_tv;
    @BindView(R.id.contactTimes_tv)
    TextView contactTimes_tv;
    @BindView(R.id.number_people_tv)
    TextView tv_like;

    @BindView(R.id.zan_bt)
    QMUIAlphaButton zan_bt;
    @BindView(R.id.company_profile_web)
    WebView company_profile_web;

    @BindView(R.id.contact_details_web)
    WebView contact_details_web;

    @BindView(R.id.products_catalogue_rv)
    RecyclerView products_catalogue_rv;
    GridAdapter catalogueAdapter;

    @BindView(R.id.real_view_rv)
    RecyclerView real_view_rv;
    GridAdapter realAdapter;

    @BindView(R.id.certifications_rv)
    RecyclerView certifications_rv;
    GridAdapter cationsAdapter;

    @BindView(R.id.index_tv)
    TextView index_tv;
    @BindView(R.id.more_products_catalogue_bt)
    QMUIAlphaButton more_products_catalogue_bt;

    @BindView(R.id.real_view_bt)
    QMUIAlphaButton real_view_bt;

    @BindView(R.id.certifications_bt)
    QMUIAlphaButton certifications_bt;

    @BindView(R.id.products_catalogue_cl)
    ConstraintLayout products_catalogue_cl;

    @BindView(R.id.ll_mini_program)
    LinearLayout ll_mini_program;
    @BindView(R.id.iv_mini_program)
    ImageView iv_mini_program;
    @BindView(R.id.scroll)
    NestedScrollView scroll;


    RecyclerView zan_rv;
    GalleryAdapter adapter;
    //分享弹窗
    private ShareDialog shareDialog;
    int shpo_id;
    public static String SHOPEID = "SHOPEID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        initLisetener();
        ll_mini_program.setVisibility(View.GONE);
        shpo_id = getIntent().getIntExtra(SHOPEID, 0);
        Map<String, String> params = new HashMap<String, String>();
        params.put("user_id", SpUtils.getUserID());
        params.put("shop_id", shpo_id +"");
        OkHttpHelper.postAsyncHttp(this,1001,
                params, UrlAddr.SHOP_DETAILS,this);
    }

    @Override
    protected void afterSetContentView() {
        super.afterSetContentView();

        //设置布局管理器
        zan_rv = findViewById(R.id.zan_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        zan_rv.setLayoutManager(linearLayoutManager);
        //设置适配器
        adapter = new GalleryAdapter(R.layout.iten_gallery, null);
        zan_rv.setAdapter(adapter);

        products_catalogue_rv = findViewById(R.id.products_catalogue_rv);
        products_catalogue_rv.setLayoutManager(new GridLayoutManager(this, 3));
        catalogueAdapter = new GridAdapter(R.layout.item_prod_cata, null);
        products_catalogue_rv.setAdapter(catalogueAdapter);


        real_view_rv = findViewById(R.id.real_view_rv);
        real_view_rv.setLayoutManager(new GridLayoutManager(this, 3));
        realAdapter = new GridAdapter(R.layout.item_prod_cata, null);
        real_view_rv.setAdapter(realAdapter);

        certifications_rv = findViewById(R.id.certifications_rv);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        certifications_rv.setLayoutManager(linearLayoutManager1);
        //设置适配器
        cationsAdapter = new GridAdapter(R.layout.item_prod_cata, null);
        certifications_rv.setAdapter(cationsAdapter);


    }

    private void initLisetener() {
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        share_bt.setOnClickListener(this);
        shareDialog = new ShareDialog(this, ScreenUtils.getScreenWidth(this));
        //分享界面的点击监听
        shareDialog.setDialogCallBackListener(new ShareDialog.DialogCallBackListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void wxHaoyouBack(int position) {
                ll_mini_program.setVisibility(View.GONE);
                WXMiniProgramObject miniProgram = new WXMiniProgramObject();
                miniProgram.webpageUrl="www.xianlankufang.com";// 兼容低版本的网页链接
                miniProgram.userName="gh_6606c78a3dd6";//小程序ID
                miniProgram.path="/pages/index/index";//小程序路径
                WXMediaMessage mediaMessage = new WXMediaMessage(miniProgram);
                mediaMessage.title = "真实货源\t即搜即得";//小程序消息title
                mediaMessage.description = "真实货源\t即搜即得"; // 小程序消息desc
//                Bitmap bitmap = BitmapFactory.decodeResource(BusinessDisplayActivity.this.getResources(),R.mipmap.ic_launcher);
                Bitmap bitmap = capture(scroll);
//                Bitmap sendBitmap = Bitmap.createScaledBitmap(bitmap,50,50,true);
                mediaMessage.thumbData = bmpToByteArray(bitmap);

                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = "真实货源\t即搜即得"; // 小程序消息封面图片，小于128k
                req.scene = SendMessageToWX.Req.WXSceneSession;
                req.message = mediaMessage;
                Constants.wx_api.sendReq(req);
                bitmap.recycle();
            }
            @Override
            public void wxPyqBack(int position) {
                ll_mini_program.setVisibility(View.VISIBLE);
                mHandler.sendEmptyMessageDelayed(0, 1000);

            }
        });

    }

    public  Bitmap capture(View view) {
//        getWindow().getDecorView().setDrawingCacheEnabled(true);
//        Bitmap bitmap = getWindow().getDecorView().getDrawingCache();
        Bitmap bitmap = null;
        try {
            bitmap = getBitmap(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    private Bitmap getBitmap(View view) throws Exception {

        View screenView = getWindow().getDecorView();
        screenView.setDrawingCacheEnabled(true);
        screenView.buildDrawingCache();

        //获取屏幕整张图片
        Bitmap bitmap = screenView.getDrawingCache();

        if (bitmap != null) {

            //需要截取的长和宽
            int outWidth = view.getWidth();
            int outHeight = view.getHeight();

            //获取需要截图部分的在屏幕上的坐标(view的左上角坐标）
            int[] viewLocationArray = new int[2];
            view.getLocationOnScreen(viewLocationArray);

            //从屏幕整张图片中截取指定区域
            bitmap = Bitmap.createBitmap(bitmap, viewLocationArray[0], viewLocationArray[1], outWidth, outHeight);
            Toast.makeText(this, "截图成功", Toast.LENGTH_SHORT).show();
            view.setDrawingCacheEnabled(false);  //禁用DrawingCahce否则会影响性能
        }

        return bitmap;
    }

    /**
     * 分享图片
     * @param bitmap    图片
     * @param shareType    0：分享到好友  1：分享到朋友圈
     */
    private void sharePicture(Bitmap bitmap, int shareType) {
        WXImageObject imgObj = new WXImageObject(bitmap);

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;

//        Bitmap thumbBitmap = Bitmap.createScaledBitmap(bitmap, THUMB_SIZE, THUMB_SIZE, true);
//        bitmap.recycle();
//        msg.thumbData = Util.bmpToByteArray(bitmap,false);  //设置缩略图
        msg.thumbData = bmpToByteArray(bitmap);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "真实货源\t即搜即得";
        req.message = msg;
        req.scene = shareType;
        Constants.wx_api.sendReq(req);
    }
    public static byte[] bmpToByteArray(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] result = null;
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
            int options = 100;
            while (output.toByteArray().length > 35*1024) {
                Log.e("TAG_daxiao","=="+output.toByteArray().length);
                output.reset(); //清空output
                bitmap.compress(Bitmap.CompressFormat.JPEG, options, output);
                options /= 2;
            }
            result = output.toByteArray();

            bitmap.recycle();

            output.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            sharePicture(capture(scroll),1);
            ll_mini_program.setVisibility(View.GONE);
        }
    };

    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, String> paramsMaps) {
        switch (requestCode){
            case 1001:
                Gson gson = new Gson();
                DetailsModel detailsModel = gson.fromJson(returnData, DetailsModel.class);
                DetailsModel.DataBean data = detailsModel.getData();
                visitToday_tv.setText(data.getBrowse_today()+"");
                numberVisitors_tv.setText(data.getBrowse_total()+"");
                contactTimes_tv.setText(data.getCall_log_total()+"");

                tv_like.setText(data.getLike_num()+"");
                zan_bt.setText(data.getLike_num()+"");
                adapter.setNewData(data.getLike());

                DetailsModel.DataBean.InfoBean info = data.getInfo();
                String shop_name = info.getShop_name();
                compe_name_tv.setText(shop_name);
                location_tv.setText(info.getAddress());

                int is_like = info.getIs_like();
                if (is_like == 0){
                    Drawable drawableLeft = getResources().getDrawable(
                            R.drawable.ungreat_xiao);
                    zan_bt.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                            null, null, null);
//                    zan_bt.setCompoundDrawablePadding(13);
                }else {
                    Drawable drawableLeft = getResources().getDrawable(
                            R.drawable.great_xiao);
                    zan_bt.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                            null, null, null);
                }
                //简介
                HtmlUtils.getHtmlData(info.getShop_profile(),company_profile_web);

                try {
                    JSONObject jsonObject = new JSONObject(returnData);
                    JSONObject data1 = jsonObject.getJSONObject("data");
                    JSONArray jsonArray = data1.optJSONArray("type_1");
                    if (jsonArray != null){
                        List<String> dataArr = gson.fromJson(jsonArray.toString(), new TypeToken<List<String>>(){}.getType());
                        catalogueAdapter.setNewData(dataArr);
                        products_catalogue_cl.setVisibility(View.VISIBLE);
                    }else {
                        products_catalogue_cl.setVisibility(View.GONE);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


                realAdapter.setNewData(data.getType_2());
                cationsAdapter.setNewData(data.getType_3());
                //联系方式
                HtmlUtils.getHtmlData(info.getContact_us(),contact_details_web);

                List<String> rotation_imgs = data.getRotation_imgs();
                banner.setAdapter(new BannerImageAdapter<String>(rotation_imgs) {
                    @Override
                    public void onBindView(BannerImageHolder holder, String data, int position, int size) {
                        //图片加载自己实现
                        Glide.with(holder.itemView)
                                .load(CommonData.mainUrl+data)
                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                                .into(holder.imageView);

                        index_tv.setText(position+"/"+rotation_imgs.size());
                    }
                })
                        .addBannerLifecycleObserver(this)//添加生命周期观察者
                        .setIndicator(new CircleIndicator(this));

                ImageUtils.setImage(iv_mini_program,CommonData.mainUrl+info.getQr_code());

                break;

        }
    }
    @OnClick({R.id.share_bt,R.id.more_products_catalogue_bt, R.id.real_view_bt, R.id.certifications_bt})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.more_products_catalogue_bt:{
                //产品图册
                Intent intent = new Intent(DetailsActivity.this, ProdcataActivity.class);
                intent.putExtra(SHOPEID,shpo_id);
                intent.putExtra(ALNUMTYPE,"1");
//                startActivity(intent);
            }
                break;
            case R.id.real_view_bt:{
                //实景展示
                Intent intent = new Intent(DetailsActivity.this, ProdcataActivity.class);
                intent.putExtra(SHOPEID,shpo_id);
                intent.putExtra(ALNUMTYPE,"2");
//                startActivity(intent);
            }
                break;
            case R.id.certifications_bt://证书
                Intent intent = new Intent(DetailsActivity.this, ProdcataActivity.class);
                intent.putExtra(SHOPEID,shpo_id);
                intent.putExtra(ALNUMTYPE,"3");
//                startActivity(intent);
                break;

            case R.id.share_bt:
                shareDialog.show();
                break;

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //停止轮播
        banner.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁
        banner.destroy();
    }
}
