package com.yiweiyi.www.ui.search;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.yiweiyi.www.R;
import com.yiweiyi.www.api.ApiManager;
import com.yiweiyi.www.api.Constants;
import com.yiweiyi.www.api.EventBusMsg;
import com.yiweiyi.www.base.BaseActivity;
import com.yiweiyi.www.base.CommonData;
import com.yiweiyi.www.bean.personal.FreeEntryBean;
import com.yiweiyi.www.bean.search.SearchCompeBean;
import com.yiweiyi.www.presenter.SearchPresenter;
import com.yiweiyi.www.ui.MainActivity;
import com.yiweiyi.www.ui.login.LoginActivity;
import com.yiweiyi.www.utils.ShareDialog;
import com.yiweiyi.www.utils.SpUtils;
import com.yiweiyi.www.view.VerticalSwipeRefreshLayout;
import com.yiweiyi.www.view.search.SearchCompeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import www.xcd.com.mylibrary.help.HelpUtils;

/**
 * @Author: zsh
 * 2020/9/25
 * desc:商家展示页
 */
public class BusinessDisplayActivity extends BaseActivity implements SearchCompeView,
        SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.back_bt)
    QMUIAlphaImageButton backBt;
    @BindView(R.id.search_tv)
    TextView searchTv;
    @BindView(R.id.search_cl)
    ConstraintLayout searchCl;
    @BindView(R.id.share_bt)
    QMUIAlphaImageButton searchBt;
    @BindView(R.id.bar_cl)
    ConstraintLayout barCl;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.more_tab_bt)
    QMUIAlphaImageButton moreTabBt;
    @BindView(R.id.main_vp2)
    ViewPager2 vp2;
    @BindView(R.id.ll_empty)
    LinearLayout ll_empty;

    //分享弹窗
    private ShareDialog shareDialog;
    public static String SEARCH = "search";
    private String mSearch;
    private SearchPresenter mSearchPresenter;
    private String diqu, alldiqu;

    private List<String> mArea_list;
    @BindView(R.id.ll_mini_program)
    LinearLayout ll_mini_program ;
    @BindView(R.id.rc)
    RelativeLayout rc;

    private VerticalSwipeRefreshLayout ly_pull_refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_business_display);
        ButterKnife.bind(this);
        mSearch = getIntent().getStringExtra(SEARCH);
        mSearchPresenter = new SearchPresenter(this);
        ll_mini_program.setVisibility(View.GONE);
        initView();
        initLisetener();
    }

    private void initLisetener() {
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
                Bitmap bitmap = capture(rc);
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
                mHandler.sendEmptyMessageDelayed(0, 500);

            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                diqu = tab.getText().toString();
                TextView textView = new TextView(BusinessDisplayActivity.this);
                float selectedSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 19, getResources().getDisplayMetrics());
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,selectedSize);
                textView.setTextColor(getResources().getColor(R.color.black));
                TextPaint tp = textView.getPaint();
                tp.setFakeBoldText(true);
                textView.setText(tab.getText());
                tab.setCustomView(textView);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setCustomView(null);
            }


            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {
                diqu = tab.getText().toString();
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
            sharePicture(capture(rc),1);
            ll_mini_program.setVisibility(View.GONE);
        }
    };
    /**
     * 设置vp2
     */
    private void setVp2() {
        diqu = "全部";
        List<Fragment> fragmentList = new ArrayList<>();

        for (int i = 0; i < mArea_list.size(); i++) {
            fragmentList.add(BusinessDisplayFragment.newInstance(mSearch, mArea_list.get(i)));
        }


        vp2.setOffscreenPageLimit(mArea_list.size() + 1);

        vp2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getItemCount() {
                return fragmentList.size();
            }
        });

//        vp2.setPageTransformer(new MZScaleInTransformer());

        new TabLayoutMediator(tabLayout, vp2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(mArea_list.get(position));
            }
        }).attach();

    }

    private void initView() {

        ly_pull_refresh = findViewById(R.id.ly_pull_refresh);
        ly_pull_refresh.setOnRefreshListener(this);
        //设置样式刷新显示的位置
        ly_pull_refresh.setProgressViewOffset(true, -20, 100);
        ly_pull_refresh.setColorSchemeResources(R.color.red, R.color.blue, R.color.black);
        ly_pull_refresh.setRefreshing(false);

        shareDialog = new ShareDialog(this, w);
        searchTv.setText(TextUtils.isEmpty(mSearch)?"真实货源\t即搜即得":mSearch);
        initData();
    }

    private void initData() {
        Log.e("TAG_列表","mSearch="+mSearch);
        mSearchPresenter.searchCompe(mSearch, SpUtils.getUserID(), "","0");
    }


    @OnClick({R.id.back_bt, R.id.more_tab_bt, R.id.share_bt,R.id.search_tv})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.back_bt:
                intent = new Intent(mContext, MainActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.more_tab_bt:
                String userID = SpUtils.getUserID();
                if (userID.isEmpty()) {
                    intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent);
                }else {
                    if (!diqu.isEmpty() && !alldiqu.isEmpty() && diqu != null && alldiqu != null) {
                        intent = new Intent(mContext, SelectRegionActivity.class);
                        Log.e("地区", diqu);
                        intent.putExtra(SelectRegionActivity.DATA, diqu);
                        intent.putExtra(SelectRegionActivity.ALLDATA, alldiqu);
                        startActivityForResult(intent, CommonData.GETCITY);
                    }
                }

                break;
            case R.id.share_bt: {
                //分享
                shareDialog.show();
            }
            break;
            case R.id.search_tv:
                intent = new Intent(this, SearchTabActivity.class);
//                intent.putExtra("type","index");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBusMsg event) {
        int code = event.getCode();
        if (code == Constants.REFRESHSEARCH) {
           initData();
        }
    }

    @Override
    public void onSearchCompeSuccess(SearchCompeBean baseBean) {
        ly_pull_refresh.setRefreshing(false);
        SearchCompeBean.DataBean data = baseBean.getData();
        List<SearchCompeBean.DataBean.ShopListBean> shop_list = data.getShop_list();
        if (shop_list == null || shop_list.size() == 0){
            ll_empty.setVisibility(View.VISIBLE);

            TextView textView = findViewById(R.id.tv_msg);
            String searchStr = "抱歉，没有找到与“" + mSearch + "”相关的结果，请尝试输入其他关键词。";
            SpannableString spannableString = new SpannableString(searchStr);
            //设置部分文字点击事件
            spannableString.setSpan(
                    new ForegroundColorSpan(getResources().getColor(R.color.blue)),
                    9, 9 + mSearch.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            textView.setText(spannableString);
            textView.setMovementMethod(LinkMovementMethod.getInstance());

            findViewById(R.id.advisory_service).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ApiManager.getInstance().consumerHotline()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<FreeEntryBean>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(FreeEntryBean baseBean) {
                                    String data = baseBean.getData();
                                    HelpUtils.call(mContext,data,false);
//                                                BottomAirlinesPhoneDialog dialog = new BottomAirlinesPhoneDialog();
//                                                dialog.setData(data);
//                                                dialog.show(getSupportFragmentManager(), "AirlinesPhone");
                                }
                            });
                }
            });

        }else {
            ll_empty.setVisibility(View.GONE);
            try {
                mArea_list = new ArrayList<>();
                Object area_list = data.getArea_list();
                if (area_list instanceof ArrayList){
                    ArrayList jsonArray = (ArrayList) area_list;
                    for (int i = 0; i < jsonArray.size(); i++) {
                        Object o = jsonArray.get(i);
                        if (o instanceof String){
                            String str = (String) o;
                            if (i == 0) {
                                mArea_list.add(0, "全部");
                                mArea_list.add(str);
                                alldiqu = str;
                            } else {
                                alldiqu = alldiqu + "," + o;
                                mArea_list.add(str);
                            }
                        }

                    }
                }

                setVp2();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onError(String e) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case CommonData.GETCITY:
                    String mCity = data.getExtras().getString("city");
                    Log.e("huidao", mCity);
                    for (int i = 0; i < mArea_list.size(); i++) {
                        String s = mArea_list.get(i);
                        if (mCity.equals(s)){
                            vp2.setCurrentItem(i);
                            break;
                        }
                    }

                    break;
            }
        }
    }

    @Override
    public void onRefresh() {
        initData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        if(keyCode== KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(mContext, MainActivity.class);
            mContext.startActivity(intent);
//        }
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        return super.onKeyDown(keyCode,event);
//    }
}
