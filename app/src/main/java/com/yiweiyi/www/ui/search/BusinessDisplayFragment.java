package com.yiweiyi.www.ui.search;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.alpha.QMUIAlphaButton;
import com.yiweiyi.www.R;
import com.yiweiyi.www.adapter.search.BusinessDisplayAdapter;
import com.yiweiyi.www.base.BaseFragment;
import com.yiweiyi.www.bean.search.SearchCompeBean;
import com.yiweiyi.www.details.DetailsActivity;
import com.yiweiyi.www.presenter.SearchPresenter;
import com.yiweiyi.www.utils.SpUtils;
import com.yiweiyi.www.view.search.SearchCompeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//import com.example.myapplication.ui.compe.CompanyDetailsActivity;

/**
 * @Author: zsh
 * 2020/9/25
 * desc:商家展示页列表
 */
public class BusinessDisplayFragment extends BaseFragment implements SearchCompeView {
    @BindView(R.id.recycler_rv)
    RecyclerView recyclerRv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.call_bt)
    QMUIAlphaButton callBt;
    private Unbinder mUnbinder;
    private View mPagerView;
    private BusinessDisplayAdapter mBusinessDisplayAdapter;
    private String search;
    private String area;
    private SearchPresenter mSearchPresenter;
    private MorePhoneDialog mMorePhoneDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            search = bundle.getString("search");
            area = bundle.getString("area");

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
        initData();
        initListener();

    }

    private void initData() {
        if (area.equals("全部")) {
            mSearchPresenter.searchCompe(search, SpUtils.getUserID(), "");
        } else {
            mSearchPresenter.searchCompe(search, SpUtils.getUserID(), area);
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

                if (data != null && data.size() > 0){
                    SearchCompeBean.DataBean.ShopListBean shopListBean = data.get(position);
                    Intent intent = new Intent(mContext, DetailsActivity.class);
                    intent.putExtra(DetailsActivity.SHOPEID, shopListBean.getId());
                    mContext.startActivity(intent);
                }
            }
        });
        mBusinessDisplayAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.phone_tv:{

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

    public static BusinessDisplayFragment newInstance(String search, String area) {
        Bundle args = new Bundle();
        args.putString("search", search);
        args.putString("area", area);
        BusinessDisplayFragment fragment = new BusinessDisplayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSearchCompeSuccess(SearchCompeBean baseBean) {
        if (baseBean.getData().getShop_list() == null || baseBean.getData().getShop_list().size() == 0) {
            SpannableString spannableString = new SpannableString("抱歉，没有找到与“无结果的关键词”相关的结果，请 尝试输入其他关键词。");
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#367FE7")), 9, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            titleTv.setText(spannableString);
        } else {
            mBusinessDisplayAdapter.replaceData(baseBean.getData().getShop_list());
        }

    }

    @Override
    public void onError(String e) {

    }
}
