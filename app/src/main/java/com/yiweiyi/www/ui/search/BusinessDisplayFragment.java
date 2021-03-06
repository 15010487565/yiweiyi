package com.yiweiyi.www.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiweiyi.www.R;
import com.yiweiyi.www.adapter.search.BusinessDisplayAdapter;
import com.yiweiyi.www.api.ApiManager;
import com.yiweiyi.www.api.UrlAddr;
import com.yiweiyi.www.base.BaseFragment;
import com.yiweiyi.www.bean.personal.FreeEntryBean;
import com.yiweiyi.www.bean.search.SearchCompeBean;
import com.yiweiyi.www.presenter.SearchPresenter;
import com.yiweiyi.www.ui.details.DetailsActivity;
import com.yiweiyi.www.ui.login.LoginActivity;
import com.yiweiyi.www.utils.SpUtils;
import com.yiweiyi.www.view.search.SearchCompeView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import www.xcd.com.mylibrary.help.HelpUtils;
import www.xcd.com.mylibrary.help.OkHttpHelper;
import www.xcd.com.mylibrary.http.HttpInterface;

//import com.example.myapplication.ui.compe.CompanyDetailsActivity;

/**
 * @Author: zsh
 * 2020/9/25
 * desc:商家展示页列表
 */
public class BusinessDisplayFragment extends BaseFragment implements SearchCompeView, HttpInterface {
    @BindView(R.id.recycler_rv)
    RecyclerView recyclerRv;
//    @BindView(R.id.title_tv)
//    TextView titleTv;
//    @BindView(R.id.call_bt)
//    QMUIAlphaButton callBt;
    private Unbinder mUnbinder;
    private View mPagerView;
    private BusinessDisplayAdapter mBusinessDisplayAdapter;
    private String search;
    private String area;
    private SearchPresenter mSearchPresenter;
    private MorePhoneDialog mMorePhoneDialog;
    List<SearchCompeBean.DataBean.ShopListBean> shop_list;

    public static BusinessDisplayFragment newInstance(String search, String area,List<SearchCompeBean.DataBean.ShopListBean> shop_list) {
        Bundle args = new Bundle();
        args.putString("search", search);
        args.putString("area", area);
        args.putSerializable("shop_list", (Serializable) shop_list);
        BusinessDisplayFragment fragment = new BusinessDisplayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            search = bundle.getString("search");
            area = bundle.getString("area");
            shop_list = (ArrayList<SearchCompeBean.DataBean.ShopListBean>)bundle.getSerializable("shop_list");
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        mPagerView = super.onCreateView(inflater, container, savedInstanceState);

        if (mPagerView == null) {

            mPagerView = inflater.inflate(R.layout.fragment_business_display, container, false);
        }

        mUnbinder = ButterKnife.bind(this, mPagerView);

        return mPagerView;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSearchPresenter = new SearchPresenter(this);
        initView();
        if ("全部".equals(area)){
            initdata( shop_list);
        }else {
            initData();
        }

        initListener();

    }

    private void initData() {
        Log.e("TAG_列表fragment","area="+area);
        if (area.equals("全部")) {
            mSearchPresenter.searchCompe(search, SpUtils.getUserID(), "","0");
        } else {
            mSearchPresenter.searchCompe(search, SpUtils.getUserID(), area,"0");
        }

        //mBusinessDisplayAdapter.replaceData(mDataBeanList);
    }

    /**
     * 监听
     */
    private void initListener() {
        final List<SearchCompeBean.DataBean.ShopListBean> data = mBusinessDisplayAdapter.getData();
        mBusinessDisplayAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //公司详情
                String userID = SpUtils.getUserID();
                if (userID.isEmpty()) {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent);
                }else {
                    if (data != null && data.size() > 0){
                        SearchCompeBean.DataBean.ShopListBean shopListBean = data.get(position);
                        Intent intent = new Intent(mContext, DetailsActivity.class);
                        intent.putExtra(DetailsActivity.SHOPEPHONE,shopListBean.getPhone().get(0));
                        intent.putExtra(DetailsActivity.SHOPEID, shopListBean.getId());
                        mContext.startActivity(intent);
                    }
                }

            }
        });
        mBusinessDisplayAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //公司详情
                String userID = SpUtils.getUserID();
                if (userID.isEmpty()) {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent);
                }else {
                    switch (view.getId()){
                        case R.id.ll_phone:{

                            List<SearchCompeBean.DataBean.ShopListBean> data1 = mBusinessDisplayAdapter.getData();
                            SearchCompeBean.DataBean.ShopListBean shopListBean = data1.get(position);
                            int id = shopListBean.getId();
                            callPhone(String.valueOf(id), shopListBean.getPhone().get(0));
//                            BottomAirlinesPhoneDialog dialog = new BottomAirlinesPhoneDialog();
//                            dialog.setData(data1.get(position).getPhone().get(0));
//                            dialog.show(getFragmentManager(),"Phone");

                        }break;
                        case R.id.more_number_tv:{
                            //
                            if (data != null && data.size() > 0){
                                SearchCompeBean.DataBean.ShopListBean shopListBean = data.get(position);
                                mMorePhoneDialog.show(shopListBean.getPhone());
                            }

                        }break;
                    }
                }

            }
        });
    }

    private void initView() {
        mMorePhoneDialog = new MorePhoneDialog(mContext);
        setList();
    }

    /**
     * 初始化列表
     */
    private void setList() {
        recyclerRv.setLayoutManager(new LinearLayoutManager(mContext));
        List<SearchCompeBean.DataBean.ShopListBean> businessDisplayList = new ArrayList<>();
        mBusinessDisplayAdapter = new BusinessDisplayAdapter(R.layout.item_business_display, businessDisplayList);
        recyclerRv.setAdapter(mBusinessDisplayAdapter);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mUnbinder != null)
            mUnbinder.unbind();

    }



    @Override
    public void onSearchCompeSuccess(SearchCompeBean baseBean) {
        List<SearchCompeBean.DataBean.ShopListBean> shop_list = baseBean.getData().getShop_list();
        initdata(shop_list);


    }

    private void initdata( List<SearchCompeBean.DataBean.ShopListBean> shop_list) {
        if (shop_list == null || shop_list.size() == 0) {
            View emptyView = getLayoutInflater().inflate(R.layout.view_empty_search, null);
            mBusinessDisplayAdapter.setEmptyView(emptyView);
            TextView textView = emptyView.findViewById(R.id.tv_msg);
            String searchStr = "抱歉，没有找到与“" + search + "”相关的结果，请尝试输入其他关键词。";
            SpannableString spannableString = new SpannableString(searchStr);
            //设置部分文字点击事件
            spannableString.setSpan(
                    new ForegroundColorSpan(getResources().getColor(R.color.blue)),
                    9, 9 + search.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            textView.setText(spannableString);
            textView.setMovementMethod(LinkMovementMethod.getInstance());

            emptyView.findViewById(R.id.advisory_service).setOnClickListener(new View.OnClickListener() {
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
                                    HelpUtils.call(getActivity(), data, false);

                                }
                            });
                }
            });
        } else {
            mBusinessDisplayAdapter.setNewData(shop_list);
        }
    }

    @Override
    public void onError(String e) {

    }

    public void callPhone(String shop_id,String phone) {
        HelpUtils.call(getActivity(),phone,false);

        Map<String, String> params1 = new HashMap<String, String>();
        params1.put("user_id", SpUtils.getUserID());
        params1.put("shop_id", shop_id +"");

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
//        Date date = new Date();
//        String str=simpleDateFormat.format(date);

        params1.put("shop_phone", phone +"");
        params1.put("call_time", System.currentTimeMillis() +"");
        params1.put("is_connect", "否");
        OkHttpHelper.postAsyncHttp(getActivity(),1002,
                params1, UrlAddr.ADDCALLLOG,this);
    }

    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, String> paramsMaps) {

    }

    @Override
    public void onErrorResult(int requestCode, String returnMsg) {

    }
}
