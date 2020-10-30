package com.yiweiyi.www.details;

import android.os.Bundle;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.yiweiyi.www.R;
import com.yiweiyi.www.api.UrlAddr;
import com.yiweiyi.www.model.DetailsModel;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.help.OkHttpHelper;
import www.xcd.com.mylibrary.utils.ToastUtil;

import static com.yiweiyi.www.details.DetailsActivity.SHOPEID;

public class ProdcataActivity extends SimpleTopbarActivity {

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
        Map<String, String> params = new HashMap<String, String>();
        //1 产品图册  , 2 实景展示 , 3 资质证书
        params.put("type", type);
        params.put("shop_id", getIntent().getIntExtra(SHOPEID,0)+"");
        OkHttpHelper.postAsyncHttp(this,1001,
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
                DetailsModel pr = gson.fromJson(returnData, DetailsModel.class);

//                setVp2(baseBean.getData());
                break;


        }
    }
//    private void setVp2(SearchCompeBean.DataBean baseBean) {
//        mArea_list = baseBean.getArea_list();
//        mArea_list.add(0, "全部");
//        diqu = "全部";
//        List<Fragment> fragmentList = new ArrayList<>();
//
//        for (int i = 0; i < mArea_list.size(); i++) {
//            fragmentList.add(BusinessDisplayFragment.newInstance(mSearch, mArea_list.get(i)));
//        }
//
//
//        vp2.setOffscreenPageLimit(mArea_list.size() + 1);
//
//        vp2.setAdapter(new FragmentStateAdapter(this) {
//            @NonNull
//            @Override
//            public Fragment createFragment(int position) {
//                return fragmentList.get(position);
//            }
//
//            @Override
//            public int getItemCount() {
//                return fragmentList.size();
//            }
//        });
//
//        vp2.setPageTransformer(new MZScaleInTransformer());
//
//        new TabLayoutMediator(tabLayout, vp2, new TabLayoutMediator.TabConfigurationStrategy() {
//            @Override
//            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//                tab.setText(mArea_list.get(position));
//            }
//        }).attach();
//        if (!mCity.isEmpty()) {
//            for (int i = 0; i < mArea_list.size(); i++) {
//                if (mCity.equals(mArea_list.get(i))) {
//                    tabLayout.getTabAt(i).select();
//                }
//
//            }
//        }
//    }

    @Override
    public void onErrorResult(int errorCode, String errorExcep) {
        ToastUtil.showToast(errorExcep);
    }

}
