package com.yiweiyi.www.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiweiyi.www.R;
import com.yiweiyi.www.adapter.main.SeriesTextAdapter;
import com.yiweiyi.www.base.BaseFragment;
import com.yiweiyi.www.bean.main.HomeCategoryBean;
import com.yiweiyi.www.ui.search.BusinessDisplayActivity;
import com.yiweiyi.www.utils.GridDecorationLpp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author: zsh 2020/9/24
 * desc:
 */
public class MainFragment extends BaseFragment {

    @BindView(R.id.recycler_rv)
    RecyclerView recyclerRv;
    private Unbinder mUnbinder;
    private View mPagerView;
    private SeriesTextAdapter mSeriesTextAdapter;
    private ArrayList<HomeCategoryBean.DataBean.ListBean> mDataBeanList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mDataBeanList = bundle.getParcelableArrayList("listBean");
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        mPagerView = super.onCreateView(inflater, container, savedInstanceState);

        if (mPagerView == null) {

            mPagerView = inflater.inflate(R.layout.recycler_all, container, false);
        }

        mUnbinder = ButterKnife.bind(this, mPagerView);

        return mPagerView;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initData();
        initListener();

    }

    private void initData() {
        mSeriesTextAdapter.replaceData(mDataBeanList);
    }

    /**
     * 监听
     */
    private void initListener() {
        mSeriesTextAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, BusinessDisplayActivity.class);
                intent.putExtra(BusinessDisplayActivity.SEARCH, mSeriesTextAdapter.getItem(position).getName());
                mContext.startActivity(intent);
            }
        });
    }

    private void initView() {
        setList();
    }

    /**
     * 初始化列表
     */
    private void setList() {
        recyclerRv.setLayoutManager(new GridLayoutManager(mContext, 2));
        List<HomeCategoryBean.DataBean.ListBean> seriesSortList = new ArrayList<>();
        mSeriesTextAdapter = new SeriesTextAdapter(R.layout.iteam_text_series, seriesSortList);
        recyclerRv.addItemDecoration(new GridDecorationLpp(2, dip2px(22), dip2px(20), true));
        recyclerRv.setAdapter(mSeriesTextAdapter);
    }

    /**
     * dp 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mUnbinder != null)
            mUnbinder.unbind();

    }

    public static MainFragment newInstance(ArrayList<HomeCategoryBean.DataBean.ListBean> listBean) {
        Bundle args = new Bundle();
        args.putParcelableArrayList("listBean", listBean);
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
