package com.yiweiyi.www.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.yiweiyi.www.api.UrlAddr;
import com.yiweiyi.www.base.BaseActivity;
import com.yiweiyi.www.base.BaseBean;
import com.yiweiyi.www.bean.search.ProximitySearchBean;
import com.yiweiyi.www.bean.search.SearchRecordsBean;
import com.yiweiyi.www.presenter.SearchPresenter;
import com.yiweiyi.www.ui.WebActivity;
import com.yiweiyi.www.ui.login.LoginActivity;
import com.yiweiyi.www.ui.me.RawMaterialActivity;
import com.yiweiyi.www.utils.SpUtils;
import com.yiweiyi.www.view.search.ClearRecordsView;
import com.yiweiyi.www.view.search.DeleteRecordView;
import com.yiweiyi.www.view.search.ProximitySearchView;
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
import www.xcd.com.mylibrary.help.OkHttpHelper;
import www.xcd.com.mylibrary.http.HttpInterface;

/**
 * @Author: zsh
 * 2020/9/24
 * desc:搜索页
 */
public class SearchTabActivity extends BaseActivity implements SearchRecordsView,
        ClearRecordsView, DeleteRecordView, ProximitySearchView, HttpInterface {
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
    @BindView(R.id.ll_search_history)
    LinearLayout ll_search_history;
    private SearchHistoryAdapter mSearchHistoryAdapter;
    private SearchAdapter mSearchAdapter;
    private SearchPresenter mSearchPresenter;
    private int[] ids = new int[1];
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        searchEt.setFocusable(true);
        searchEt.setFocusableInTouchMode(true);
        searchEt.requestFocus();
        mSearchPresenter = new SearchPresenter(this);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        initView();
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
                        String content = mSearchHistoryAdapter.getItem(position).getContent();
                        if ("原料行情".equals(content)||"历史铜价".equals(content)){
                            Intent intent = new Intent(mContext, RawMaterialActivity.class);
                            startActivity(intent);
                            finish();
                        }else if ("电阻表".equals(content)){
                            Intent intent = new Intent(mContext, WebActivity.class);
                            intent.putExtra("key_name", content);
                            startActivity(intent);
                            finish();
                        }else {
                            initData(content);
//                            Intent intent = new Intent(mContext, BusinessDisplayActivity.class);
//                            intent.putExtra(BusinessDisplayActivity.SEARCH,content );
//                            startActivity(intent);
//                            finish();
                        }
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
                ProximitySearchBean.DataBean.ListBean item = mSearchAdapter.getItem(position);
                if (item != null && item.getKey_name() != null) {
                    intent.putExtra(BusinessDisplayActivity.SEARCH, item.getKey_name());
                }
                startActivity(intent);
                finish();
            }
        });
//        mSearchHistoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//
//
//            }
//        });
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
                    ll_search_history.setVisibility(View.GONE);
                    //首页搜索
                    mSearchPresenter.proximitySearch(charSequence.toString());

                } else {
                    searchRe.setVisibility(View.GONE);
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
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search_param = searchEt.getText().toString().trim();
                    if (!TextUtils.isEmpty(search_param)) {
                        if ("原料行情".equals(search_param)||"历史铜价".equals(search_param)){
                            Intent intent = new Intent(mContext, RawMaterialActivity.class);
                            startActivity(intent);
                            finish();
                        }else if ("电阻表".equals(search_param)){
                            Intent intent = new Intent(mContext, WebActivity.class);
                            intent.putExtra("key_name", search_param);
                            startActivity(intent);
                            finish();
                        }else {
                            initData(search_param);
                        }
                        return true;
                    }
                    return true;
                }
                return false;
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
    String search_param;

    private void initData(String search_param) {
        this.search_param = search_param;
        mSearchAdapter.setKeyword(search_param);
        Map<String, String> params = new HashMap<String, String>();
        params.put("search", search_param);
        params.put("user_id", SpUtils.getUserID());
        params.put("area", "");
        params.put("page ", "0");
        OkHttpHelper.postAsyncHttp(this, 1000,
                params, UrlAddr.SEARCH_INDEX, this);
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
        Log.e("TAG_搜索", (list == null) + "");
        mSearchAdapter.setNewData(list);

    }


    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, String> paramsMaps) {
        switch (returnCode) {
            case 1:{
//                mSearchAdapter.setKeyword(search_param);
                Intent intent = new Intent(mContext, BusinessDisplayActivity.class);
                intent.putExtra(BusinessDisplayActivity.SEARCH, search_param);
                startActivity(intent);
                finish();
        }

        break;
//        case 2:
//        if (returnMsg.indexOf("原料行情") != -1) {
//            startActivity(new Intent(mContext, RawMaterialActivity.class));
//            finish();
//        }
//        break;
        case 3:
        try {

            JSONObject jsonObject = new JSONObject(returnData);
            String data1 = jsonObject.optString("data");
            Intent intent = new Intent(mContext, WebActivity.class);
            intent.putExtra("WEB_TYPE",WebActivity.WEB_TYPE);
            intent.putExtra("key_name", data1);
            startActivity(intent);
            finish();

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
