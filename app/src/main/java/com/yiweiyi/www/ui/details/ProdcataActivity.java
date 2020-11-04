package com.yiweiyi.www.ui.details;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.yiweiyi.www.R;
import com.yiweiyi.www.api.UrlAddr;
import com.yiweiyi.www.model.ProdcataModel;
import com.youth.banner.transformer.MZScaleInTransformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.help.OkHttpHelper;
import www.xcd.com.mylibrary.utils.ToastUtil;

public class ProdcataActivity extends SimpleTopbarActivity {

    @BindView(R.id.ll_image)
    LinearLayout ll_image;
    @BindView(R.id.view_empty)
    View view_empty;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.main_vp2)
    ViewPager2 vp2;
    public static String ALNUMTYPE = "ALNUMTYPE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prod_cata);
        String type = getIntent().getStringExtra(ALNUMTYPE);
        //1 产品图册  , 2 实景展示 , 3 资质证书
        if ("1".equals(type)){
            resetTopbarTitle("产品图册");
        }else if("2".equals(type)){
            resetTopbarTitle("实景展示");
        }else {
            resetTopbarTitle("资质证书");
        }

        Map<String, String> params = new HashMap<String, String>();
        params.put("type", type);
        params.put("shop_id", getIntent().getIntExtra(DetailsActivity.SHOPEID,0)+"");
        OkHttpHelper.postAsyncHttp(this,1000,
                params, UrlAddr.ALNUM,this);
    }

    @Override
    protected void afterSetContentView() {
        super.afterSetContentView();
        ButterKnife.bind(this);
        initLisetener();
    }

    private void initLisetener() {

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, String> paramsMaps) {
        switch (requestCode){
            case 1000:
                Gson gson = new Gson();
                ProdcataModel pr = gson.fromJson(returnData, ProdcataModel.class);
                List<ProdcataModel.DataBean> data = pr.getData();
                if (data == null || data.size() == 0){
                    ll_image.setVisibility(View.GONE);
                    view_empty.setVisibility(View.VISIBLE);
                }else {
                    ll_image.setVisibility(View.VISIBLE);
                    view_empty.setVisibility(View.GONE);
                    setVp2(data);

                }

                break;

        }
    }

    private void setVp2(List<ProdcataModel.DataBean> data) {

        List<Fragment> fragmentList = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            ProdcataModel.DataBean dataBean = data.get(i);
            fragmentList.add(ProdcataFragment.newInstance(dataBean));
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

        vp2.setPageTransformer(new MZScaleInTransformer());

        new TabLayoutMediator(tabLayout, vp2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                ProdcataModel.DataBean dataBean = data.get(position);
                tab.setText(dataBean.getAlbum_name());
            }
        }).attach();

    }

    @Override
    public void onErrorResult(int errorCode, String errorExcep) {
        ToastUtil.showToast(errorExcep);
    }

}
