package com.yiweiyi.www.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.qmuiteam.qmui.alpha.QMUIAlphaButton;
import com.yiweiyi.www.R;
import com.yiweiyi.www.adapter.search.SearchAdapter;
import com.yiweiyi.www.adapter.search.SearchHistoryAdapter;
import com.yiweiyi.www.api.ApiManager;
import com.yiweiyi.www.base.BaseActivity;
import com.yiweiyi.www.base.BaseBean;
import com.yiweiyi.www.bean.personal.FreeEntryBean;
import com.yiweiyi.www.bean.search.ProximitySearchBean;
import com.yiweiyi.www.bean.search.SearchRecordsBean;
import com.yiweiyi.www.dialog.BottomAirlinesPhoneDialog;
import com.yiweiyi.www.presenter.SearchPresenter;
import com.yiweiyi.www.ui.login.LoginActivity;
import com.yiweiyi.www.utils.SpUtils;
import com.yiweiyi.www.view.search.ClearRecordsView;
import com.yiweiyi.www.view.search.DeleteRecordView;
import com.yiweiyi.www.view.search.ProximitySearchView;
import com.yiweiyi.www.view.search.SearchRecordsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author: zsh
 * 2020/9/24
 * desc:搜索页
 */
public class SearchActivity extends BaseActivity implements SearchRecordsView,
        ClearRecordsView, DeleteRecordView, ProximitySearchView {
    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.share_bt)
    QMUIAlphaButton searchBt;
    @BindView(R.id.bar_cl)
    ConstraintLayout barCl;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.search_history_re)
    RecyclerView searchHistoryRe;
    @BindView(R.id.search_re)
    RecyclerView searchRe;
    @BindView(R.id.sreach_cl)
    CoordinatorLayout sreachCl;
    private SearchHistoryAdapter mSearchHistoryAdapter;
    private SearchAdapter mSearchAdapter;
    private SearchPresenter mSearchPresenter;
    private int[] ids = new int[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        mSearchPresenter = new SearchPresenter(this);
        initView();
        initData();
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!SpUtils.getUserID().isEmpty()) {
            mSearchPresenter.searchRecords(SpUtils.getUserID());
        }

    }

    private void initListener() {
        //搜索输入框监听
        mSearchHistoryAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.delete_img: {
                        //用户删除搜索记录
                        ids[0] = mSearchHistoryAdapter.getData().get(position).getId();
                        mSearchPresenter.deleteRecord(SpUtils.getUserID(), ids);
                    }
                    break;
                    case R.id.all_cl: {
                        //商家展示页
                        Intent intent = new Intent(mContext, BusinessDisplayActivity.class);
                        intent.putExtra(BusinessDisplayActivity.SEARCH, mSearchHistoryAdapter.getItem(position).getContent());
                        startActivity(intent);
                    }
                    break;
                }
            }
        });
        mSearchAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //商家展示页
                Intent intent = new Intent(mContext, BusinessDisplayActivity.class);
                SearchRecordsBean.DataBean item = mSearchHistoryAdapter.getItem(position);
                if (item != null && item.getContent() != null){
                    intent.putExtra(BusinessDisplayActivity.SEARCH, item.getContent());

                }
                startActivity(intent);
            }
        });
        mSearchHistoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


            }
        });
        //输入监听
        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().isEmpty()) {
                    searchRe.setVisibility(View.VISIBLE);
                    searchHistoryRe.setVisibility(View.GONE);
                    mSearchPresenter.proximitySearch(charSequence.toString());
                } else {
                    searchRe.setVisibility(View.GONE);
                    searchHistoryRe.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    /**
     * 初始化视图
     */
    private void initView() {
        setSearchHistoryList();
        setSearchList();
    }

    /**
     * 搜索列表
     */
    private void setSearchList() {
        searchRe.setLayoutManager(new LinearLayoutManager(mContext));
        List<ProximitySearchBean.DataBean.ListBean> searchList = new ArrayList<>();
        mSearchAdapter = new SearchAdapter(R.layout.item_search_name, searchList);
        searchRe.setAdapter(mSearchAdapter);
    }

    /**
     * 搜索历史
     */
    private void setSearchHistoryList() {
        searchHistoryRe.setLayoutManager(new LinearLayoutManager(mContext));
        List<SearchRecordsBean.DataBean> searchHistoryList = new ArrayList<>();
        mSearchHistoryAdapter = new SearchHistoryAdapter(R.layout.item_search_history, searchHistoryList);
        searchHistoryRe.setAdapter(mSearchHistoryAdapter);
    }

    /**
     * 数据添加
     */
    private void initData() {

    }

    @OnClick({R.id.share_bt, R.id.clear_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.share_bt: {
                //关闭页面
                finish();
            }
            break;
            case R.id.clear_bt: {
                //用户清空搜索记录
                if (!SpUtils.getUserID().isEmpty()) {
                    mSearchPresenter.clearRecords(SpUtils.getUserID());
                } else {
                    openActivity(LoginActivity.class);
                }
            }
            break;
        }
    }

    /**
     * 搜索记录
     *
     * @param baseBean
     */
    @Override
    public void onSearchRecordsSuccess(SearchRecordsBean baseBean) {
        mSearchHistoryAdapter.replaceData(baseBean.getData());
    }

    @Override
    public void onError(String e) {

    }

    /**
     * 用户清空搜索记录
     *
     * @param baseBean
     */
    @Override
    public void onSearchRecordsSuccess(BaseBean baseBean) {
        mSearchPresenter.searchRecords(SpUtils.getUserID());
    }

    /**
     * 用户删除搜索记录
     *
     * @param baseBean
     */
    @Override
    public void onDeleteRecordSuccess(BaseBean baseBean) {
        mSearchPresenter.searchRecords(SpUtils.getUserID());
    }

    /**
     * 返回接近关键词
     *
     * @param baseBean
     */
    @Override
    public void onProximitySearchSuccess(ProximitySearchBean baseBean) {
        ProximitySearchBean.DataBean data = baseBean.getData();
        String search_param = data.getSearch_param();
        mSearchAdapter.setKeyword(search_param);
        List<ProximitySearchBean.DataBean.ListBean> list = data.getList();
        Log.e("TAG_搜索",(list==null)+"");
        if (list != null && list.size() > 0){
            mSearchAdapter.replaceData(list);
        }else {
            View emptyView = getLayoutInflater().inflate(R.layout.view_empty_search, null);
            mSearchAdapter.setEmptyView(emptyView);
            TextView textView = emptyView.findViewById(R.id.tv_msg);
            String searchStr = "抱歉，没有找到与“"+search_param+"”相关的结果，请尝试输入其他关键词。";
            SpannableString spannableString = new SpannableString(searchStr);
            //设置部分文字点击事件
            spannableString.setSpan(
                    new ForegroundColorSpan(getResources().getColor(R.color.blue)),
                    9, 9+search_param.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

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
                                    BottomAirlinesPhoneDialog dialog = new BottomAirlinesPhoneDialog();
                                    dialog.setData(data);
                                    dialog.show(getSupportFragmentManager(),"AirlinesPhone");
                                }
                            });
                }
            });
        }

    }
}
