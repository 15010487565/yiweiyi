package com.yiweiyi.www.ui.search;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.qmuiteam.qmui.alpha.QMUIAlphaButton;
import com.yiweiyi.www.R;
import com.yiweiyi.www.adapter.search.SearchHistoryAdapter;
import com.yiweiyi.www.adapter.search.SearchIndexAdapter;
import com.yiweiyi.www.api.ApiManager;
import com.yiweiyi.www.api.UrlAddr;
import com.yiweiyi.www.base.BaseActivity;
import com.yiweiyi.www.base.BaseBean;
import com.yiweiyi.www.bean.personal.FreeEntryBean;
import com.yiweiyi.www.bean.search.SearchCompeBean;
import com.yiweiyi.www.bean.search.SearchRecordsBean;
import com.yiweiyi.www.presenter.SearchPresenter;
import com.yiweiyi.www.ui.WebActivity;
import com.yiweiyi.www.ui.login.LoginActivity;
import com.yiweiyi.www.ui.me.RawMaterialActivity;
import com.yiweiyi.www.utils.SpUtils;
import com.yiweiyi.www.view.search.ClearRecordsView;
import com.yiweiyi.www.view.search.DeleteRecordView;
import com.yiweiyi.www.view.search.SearchRecordsView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import www.xcd.com.mylibrary.help.HelpUtils;
import www.xcd.com.mylibrary.help.OkHttpHelper;
import www.xcd.com.mylibrary.http.HttpInterface;

/**
 * @Author: zsh
 * 2020/9/24
 * desc:搜索页
 */
public class SearchIndexActivity extends BaseActivity implements SearchRecordsView,
        ClearRecordsView, DeleteRecordView, HttpInterface {
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

//    @BindView(R.id.webView)
//    WebView webView;

    @BindView(R.id.sreach_cl)
    CoordinatorLayout sreachCl;
    @BindView(R.id.ll_search_history)
    LinearLayout ll_search_history;
    private SearchHistoryAdapter mSearchHistoryAdapter;
    private SearchIndexAdapter mSearchAdapter;
    private SearchPresenter mSearchPresenter;
    private int[] ids = new int[1];
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        mSearchPresenter = new SearchPresenter(this);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
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
//                        searchEt.setText(mSearchHistoryAdapter.getItem(position).getContent());
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
                SearchCompeBean.DataBean.ShopListBean item = mSearchAdapter.getItem(position);
                if (item != null && item.getShop_name() != null) {
                    intent.putExtra(BusinessDisplayActivity.SEARCH, item.getShop_name());
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
//                    webView.setVisibility(View.GONE);
                    searchHistoryRe.setVisibility(View.GONE);
                    ll_search_history.setVisibility(View.GONE);

                } else {
                    searchRe.setVisibility(View.GONE);
//                    webView.setVisibility(View.GONE);
                    ll_search_history.setVisibility(View.VISIBLE);
                    searchHistoryRe.setVisibility(View.VISIBLE);
                }

            }


            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        searchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                search_param = searchEt.getText().toString().trim();
                if (!TextUtils.isEmpty(search_param)) {
                    initData(search_param);
                    return true;
                }
                return false;
            }
        });

    }


    String search_param;

    private void initData(String search_param) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("search", search_param);
        params.put("user_id", SpUtils.getUserID());
        params.put("area", "");
        params.put("page ", "0");
        OkHttpHelper.postAsyncHttp(this, 1000,
                params, UrlAddr.SEARCH_INDEX, this);
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
        mSearchAdapter = new SearchIndexAdapter(R.layout.item_search_name, null);
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
        mSearchHistoryAdapter.setNewData(baseBean.getData());
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


    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, String> paramsMaps) {
        switch (returnCode) {
            case 1:
                try {
                    Gson gson = new Gson();
                    SearchCompeBean searchCompeBean = gson.fromJson(returnData, SearchCompeBean.class);
                    SearchCompeBean.DataBean data = searchCompeBean.getData();
                    mSearchAdapter.setKeyword(search_param);
                    List<SearchCompeBean.DataBean.ShopListBean> shop_list = data.getShop_list();

                    if (shop_list == null || shop_list.size() == 0) {

                        View emptyView = getLayoutInflater().inflate(R.layout.view_empty_search, null);
                        mSearchAdapter.setEmptyView(emptyView);
                        TextView textView = emptyView.findViewById(R.id.tv_msg);
                        String searchStr = "抱歉，没有找到与“" + search_param + "”相关的结果，请尝试输入其他关键词。";
                        SpannableString spannableString = new SpannableString(searchStr);
                        //设置部分文字点击事件
                        spannableString.setSpan(
                                new ForegroundColorSpan(getResources().getColor(R.color.blue)),
                                9, 9 + search_param.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

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
                                                HelpUtils.call(SearchIndexActivity.this,data,false);
//                                                BottomAirlinesPhoneDialog dialog = new BottomAirlinesPhoneDialog();
//                                                dialog.setData(data);
//                                                dialog.show(getSupportFragmentManager(), "AirlinesPhone");
                                            }
                                        });
                            }
                        });
                    }else {
//                        mSearchAdapter.setNewData(shop_list);

                        Intent intent = new Intent(mContext, BusinessDisplayActivity.class);
//                        SearchCompeBean.DataBean.ShopListBean item = mSearchAdapter.getItem(position);
//                        if (item != null && item.getShop_name() != null) {
                            intent.putExtra(BusinessDisplayActivity.SEARCH, search_param);
//                        }
                        startActivity(intent);
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                if (returnMsg.indexOf("原料行情") != -1) {
                    startActivity(new Intent(SearchIndexActivity.this, RawMaterialActivity.class));
                }
                break;
            case 3:
                try {

                    JSONObject jsonObject = new JSONObject(returnData);
                    String data1 = jsonObject.optString("data");
                    Intent intent = new Intent(SearchIndexActivity.this, WebActivity.class);
                    intent.putExtra("type","SEARCH3");
                    intent.putExtra("data",data1);
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    @Override
    public void onErrorResult(int requestCode, String returnMsg) {

    }
}
