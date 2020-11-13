package com.yiweiyi.www.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.qmuiteam.qmui.alpha.QMUIAlphaButton;
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.yiweiyi.www.R;
import com.yiweiyi.www.adapter.main.AllSeriesAdrpter;
import com.yiweiyi.www.api.ApiManager;
import com.yiweiyi.www.base.BaseActivity;
import com.yiweiyi.www.base.CommonData;
import com.yiweiyi.www.bean.main.HomeCategoryBean;
import com.yiweiyi.www.bean.personal.FreeEntryBean;
import com.yiweiyi.www.bean.personal.UserInfoBean;
import com.yiweiyi.www.presenter.MainPresenter;
import com.yiweiyi.www.presenter.PersonalPresenter;
import com.yiweiyi.www.ui.login.LoginActivity;
import com.yiweiyi.www.ui.me.FeedBackActivity;
import com.yiweiyi.www.ui.me.RawMaterialActivity;
import com.yiweiyi.www.ui.me.UserinfoActivity;
import com.yiweiyi.www.ui.search.SearchTabActivity;
import com.yiweiyi.www.ui.setting.SettingActivity;
import com.yiweiyi.www.ui.store.StoreManageActivity;
import com.yiweiyi.www.utils.PrfUtils;
import com.yiweiyi.www.utils.SpUtils;
import com.yiweiyi.www.view.CircleImageView;
import com.yiweiyi.www.view.main.HomeCategoryView;
import com.yiweiyi.www.view.personal.FreeEntryView;
import com.yiweiyi.www.view.personal.UserInfoView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import www.xcd.com.mylibrary.help.HelpUtils;

public class MainActivity extends BaseActivity implements HomeCategoryView, FreeEntryView, UserInfoView {

    @BindView(R.id.personal_abt)
    LinearLayout personalAbt;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.more_tab_bt)
    QMUIAlphaImageButton moreTabBt;
    @BindView(R.id.main_vp2)
    ViewPager2 vp2;
    @BindView(R.id.left_nv)
    NavigationView leftNv;
    @BindView(R.id.activity_main)
    DrawerLayout activityMain;
    @BindView(R.id.right_nv)
    LinearLayout rightNv;

    private ImageView mPersonalLeft;
    private QMUIRadiusImageView mHeadImg;
    private RecyclerView mAllSeries;
    private AllSeriesAdrpter mAllSeriesAdrpter;
    private MainPresenter mMainPresenter;
    private QMUIAlphaButton mStoreManagement;
    private TextView mChangeInfor;
    private QMUIAlphaButton mSet;
    private QMUIAlphaButton mFreeEntry;
    private QMUIAlphaButton mFeedback;
    private QMUIAlphaButton mRawMaterial,advisory_service;
    RelativeLayout rl_info;
    private LinearLayout ll_maininfo;
    private CircleImageView mHead;
    private TextView mLogin;
    private TextView mName;
    private int upPosition = 0;
    private int tabPosition = 0;
    private PersonalPresenter mPersonalPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMainPresenter = new MainPresenter(this);
        mPersonalPresenter = new PersonalPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!SpUtils.getUserID().isEmpty()) {
            RequestOptions requestOptions = new RequestOptions()
                        .placeholder(R.drawable.no_login)
                        .error(R.drawable.no_login)
                        .skipMemoryCache(true)//跳过内存缓存
                        .diskCacheStrategy(DiskCacheStrategy.ALL)//不要在disk硬盘缓存;
                        ;
            String imageUrl = PrfUtils.getHeadimgurl();
            String avatar;
            if (imageUrl != null && imageUrl.startsWith("http")){
                avatar = imageUrl;
            }else {
                avatar = CommonData.mainUrl + imageUrl;
            }
                Glide.with(mHead)
                        .asDrawable()
                        .load(avatar)
                        .apply(requestOptions)
                        .into(mHead);

            mLogin.setVisibility(View.GONE);
            ll_maininfo.setVisibility(View.VISIBLE);
            String nickname = PrfUtils.getNickname();
            Log.e("TAG_首页","nickname="+nickname);
            mName.setText(nickname);

            int shop = PrfUtils.isShop();
            if (shop == 2){
                mStoreManagement.setVisibility(View.VISIBLE);
            }else {
                mStoreManagement.setVisibility(View.GONE);
            }

        }else {
            mStoreManagement.setVisibility(View.GONE);
            mLogin.setVisibility(View.VISIBLE);
            ll_maininfo.setVisibility(View.GONE);
            mHead.setImageResource(R.drawable.no_login);
        }

    }

    /**
     * 初始化视图
     */
    private void initView() {
        String userID = SpUtils.getUserID();
        if (userID.isEmpty()) {
            //openActivity(RegisterActivity.class);
            openActivity(LoginActivity.class);
        }
        initViewLeft();
        initViewRight();
        initListener();
        Log.e("TAG_首页","userid="+SpUtils.getUserID());
    }

    /**
     * 右侧抽屉视图初始化
     */
    private void initViewRight() {
//        View headview = rightNv.inflateHeaderView(R.layout.nav_all_series);
        mAllSeries = findViewById(R.id.all_series_rv);
        setAllSeriesList();
    }

    /**
     * 左侧抽屉视图初始化
     */
    private void initViewLeft() {
        View headview = leftNv.inflateHeaderView(R.layout.nav_my_head);
        rl_info = headview.findViewById(R.id.rl_info);

        ll_maininfo = headview.findViewById(R.id.ll_maininfo);
        mHead = headview.findViewById(R.id.head_img);
        mLogin = headview.findViewById(R.id.login_bt);
        mName = headview.findViewById(R.id.name_tv);
        mPersonalLeft = headview.findViewById(R.id.personal_left_abt);
        mStoreManagement = headview.findViewById(R.id.store_management);//
        //字段is_shop=2商家，其他都是个人
        String userID = SpUtils.getUserID();
        if (userID.isEmpty()) {
            mStoreManagement.setVisibility(View.GONE);
        }else {
            int shop = PrfUtils.isShop();
            if (shop == 2){
                mStoreManagement.setVisibility(View.VISIBLE);
            }else {
                mStoreManagement.setVisibility(View.GONE);
            }
        }

        mChangeInfor = headview.findViewById(R.id.change_infor_tv);
        mSet = headview.findViewById(R.id.setting);
        mFreeEntry = headview.findViewById(R.id.free_entry);
        mFeedback = headview.findViewById(R.id.feedback);
        mRawMaterial = headview.findViewById(R.id.raw_material_market);

        advisory_service = headview.findViewById(R.id.advisory_service);

    }

    /**
     * 监听
     */
    private void initListener() {
        //侧滑数据点击
        leftNv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //item.setChecked(true);
                Toast.makeText(MainActivity.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                activityMain.closeDrawer(leftNv);
                return true;
            }
        });
        //左侧点击收起
        mPersonalLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityMain.closeDrawer(leftNv);
            }
        });
        //店铺管理
        mStoreManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(StoreManageActivity.class);
            }
        });
//        if (mChangeInfor != null) {
//            //修改个人信息
//            mChangeInfor.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                }
//            });
//        }

        rl_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SpUtils.getUserID().isEmpty()){
                    openActivity(LoginActivity.class);
                }else {
                    //跳转个人信息
                    openActivity(UserinfoActivity.class);
                }
            }
        });
        //设置
        mSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(SettingActivity.class);
            }
        });
        //免费入驻
        mFreeEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(WebActivity.class);
            }
        });
        //意见反馈
        mFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(FeedBackActivity.class);
            }
        });
        //原料行情
        mRawMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(RawMaterialActivity.class);
            }
        });
        //咨询客服
        advisory_service.setOnClickListener(new View.OnClickListener() {
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
                                HelpUtils.call(MainActivity.this,data,false);
//                                BottomAirlinesPhoneDialog dialog = new BottomAirlinesPhoneDialog();
//                                dialog.setData(data);
//                                dialog.show(getSupportFragmentManager(),"AirlinesPhone");
                            }
                        });
            }
        });
        //登录
//        mLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openActivity(LoginActivity.class);
//            }
//        });
        //全部系列点击
        mAllSeriesAdrpter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (tabLayout == null)
                    return;
                if (activityMain.isDrawerOpen(rightNv)) {
                    activityMain.closeDrawer(rightNv);
                }
                tabLayout.getTabAt(position).select();
            }
        });




        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabPosition = tab.getPosition();
                TextView textView = new TextView(MainActivity.this);
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
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * 设置vp2
     */
    private void setVp2(HomeCategoryBean baseBean) {
        List<HomeCategoryBean.DataBean> data = baseBean.getData();
        List<Fragment> fragmentList = new ArrayList<>();


        for (int i = 0; i < baseBean.getData().size(); i++) {
            ArrayList<HomeCategoryBean.DataBean.ListBean> list = (ArrayList<HomeCategoryBean.DataBean.ListBean>) baseBean.getData().get(i).getList();
            fragmentList.add(MainFragment.newInstance(list));
        }


        vp2.setOffscreenPageLimit(data.size());

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

                tab.setText(data.get(position).getName());

            }
        }).attach();



    }

    private void setAllSeriesList() {
        mAllSeries.setLayoutManager(new LinearLayoutManager(mContext));
        List<HomeCategoryBean.DataBean> allSeriesList = new ArrayList<>();
        mAllSeriesAdrpter = new AllSeriesAdrpter(R.layout.iteam_text_all_series, allSeriesList);
        View view = LayoutInflater.from(this).inflate(R.layout.iten_footer, null);
        mAllSeriesAdrpter.addFooterView(view);
        mAllSeries.setAdapter(mAllSeriesAdrpter);
    }

    /**
     * 数据添加
     */
    private void initData() {
        if (!SpUtils.getUserID().isEmpty()) {
            mPersonalPresenter.userInfo(SpUtils.getUserID());
        }

        mMainPresenter.homeCategory();
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.personal_abt, R.id.search_ll, R.id.more_tab_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //个人信息
            case R.id.personal_abt: {
                if (activityMain.isDrawerOpen(leftNv)) {
                    activityMain.closeDrawer(leftNv);
                } else {
                    activityMain.openDrawer(leftNv);

                }
            }
            break;
            //搜索
            case R.id.search_ll: {
                Intent intent = new Intent(this, SearchTabActivity.class);
//                intent.putExtra("type","tab");
                startActivity(intent);
            }
            break;
            //更多型号
            case R.id.more_tab_bt: {
                if (activityMain.isDrawerOpen(rightNv)) {
                    activityMain.closeDrawer(rightNv);

                } else {
                    activityMain.openDrawer(rightNv);
                    if (upPosition != tabPosition) {
                        //清除掉原有的背景
                        mAllSeriesAdrpter.getData().get(upPosition).setSelect(false);
                        mAllSeriesAdrpter.notifyItemChanged(upPosition);
                        upPosition = tabPosition;
                        //更改重新选中的背景
                        mAllSeriesAdrpter.getData().get(upPosition).setSelect(true);
                        mAllSeriesAdrpter.notifyItemChanged(upPosition);
                    }

                }
            }
            break;
        }
    }

    @Override
    public void onHomeCategorySuccess(HomeCategoryBean baseBean) {
        setVp2(baseBean);
        baseBean.getData().get(upPosition).setSelect(true);
        mAllSeriesAdrpter.replaceData(baseBean.getData());
    }


    @Override
    public void onFreeEntrySuccess(FreeEntryBean baseBean) {
//        Intent intent = new Intent(mContext, FreeEntryActivity.class);
//        intent.putExtra(FreeEntryActivity.URL, baseBean.getData());
//        startActivity(intent);
    }

    @Override
    public void onError(String e) {

    }

    @Override
    public void onUserInfoSuccess(UserInfoBean baseBean) {
        HashMap<String, String> hashMap = new HashMap<>();
        //存储用户基本
        hashMap.put("id", String.valueOf(baseBean.getData().getId()));
        hashMap.put("nickname", baseBean.getData().getNickname());
        if (baseBean.getData().getAvatar() != null) {
            hashMap.put("avatar", baseBean.getData().getAvatar());
        }
        hashMap.put("phone", baseBean.getData().getPhone());
        SpUtils.saveUserInfo( hashMap);
        SpUtils.saveUserInfo( "is_shop", baseBean.getData().getIs_shop());
        //字段is_shop=2商家，其他都是个人
        int shop = PrfUtils.isShop();
        if (shop == 2){
            mStoreManagement.setVisibility(View.VISIBLE);
        }else {
            mStoreManagement.setVisibility(View.GONE);
        }
        SpUtils.saveUserInfo( "me_shop_id", baseBean.getData().getShop_id()+"");


            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.no_login)
                    .error(R.drawable.no_login)
                    .skipMemoryCache(true)//跳过内存缓存
                    .diskCacheStrategy(DiskCacheStrategy.ALL)//不要在disk硬盘缓存;
                    ;
            String imageUrl = PrfUtils.getHeadimgurl();
            String avatar;
            if (imageUrl != null && imageUrl.startsWith("http")){
                avatar = imageUrl;
            }else {
                avatar = CommonData.mainUrl + imageUrl;
            }
            Glide.with(mHead)
                    .asDrawable()
                    .load(avatar)
                    .apply(requestOptions)
                    .into(mHead);

            mLogin.setVisibility(View.GONE);
            ll_maininfo.setVisibility(View.VISIBLE);
            String nickname = PrfUtils.getNickname();
            Log.e("TAG_首页","nickname="+nickname);
            mName.setText(nickname);



    }
}