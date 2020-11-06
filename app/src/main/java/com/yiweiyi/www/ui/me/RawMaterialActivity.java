package com.yiweiyi.www.ui.me;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.yiweiyi.www.R;
import com.yiweiyi.www.adapter.personal.RawMaterialAdapter;
import com.yiweiyi.www.api.UrlAddr;
import com.yiweiyi.www.bean.raw.RawMaterialBean;
import com.yiweiyi.www.callback.OnPopupwindow;
import com.yiweiyi.www.utils.DisplayUtil;
import com.yiweiyi.www.view.BasePopWin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.help.OkHttpHelper;

/**
 * Created by gs on 2020/10/28.
 */
public class RawMaterialActivity extends SimpleTopbarActivity implements OnPopupwindow, BaseQuickAdapter.RequestLoadMoreListener {

    private RecyclerView raw_rv;
    RawMaterialAdapter adapter;
    ConstraintLayout parentView;
    TextView toolbar_tv_menu;
    private String year;
    private String type;
    private int page = 1;
    private boolean isMore = false;
    @Override
    protected Object getTopbarTitle() {
        return "原料行情";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raw_material);

        //沉浸式状态栏颜色
        setTransparent(this, ContextCompat.getColor(this,R.color.colorFD7033));

    }

    @Override
    protected void afterSetContentView() {
        super.afterSetContentView();
        parentView = findViewById(R.id.parentView);
        toolbar_tv_menu = findViewById(R.id.toolbar_tv_menu);
        toolbar_tv_menu.setOnClickListener(this);

        RadioGroup rg_left = findViewById(R.id.content_radiogroup);
        rg_left.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup radioGroup, int checkdId) {
                 RadioButton rgleft = radioGroup.findViewById(R.id.rg_left);
                 RadioButton rgRight = radioGroup.findViewById(R.id.rg_right);
                                switch (checkdId){
                                    case R.id.rg_left:
                                        rgleft.setTextColor(ContextCompat.getColor(RawMaterialActivity.this,R.color.white));
                                        rgRight.setTextColor(ContextCompat.getColor(RawMaterialActivity.this,R.color.colorFD7033));
                                        page = 1;
                                        initData(year,"1");
                                        break;
                                    case R.id.rg_right:
                                        rgRight.setTextColor(ContextCompat.getColor(RawMaterialActivity.this,R.color.white));
                                        rgleft.setTextColor(ContextCompat.getColor(RawMaterialActivity.this,R.color.colorFD7033));
                                        page = 1;
                                        initData(year,"2");
                                        break;
                                }
                            }
         });

        raw_rv = (RecyclerView) this.findViewById(R.id.raw_rv);
        raw_rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RawMaterialAdapter(R.layout.item_raw_material, null,this);
        adapter.setOnLoadMoreListener(this);
        raw_rv.setAdapter(adapter);
        initData("","1");
    }
    private void initData(String year,String type) {
        isMore = false;
        this.year = year;
        this.type = type;
        Map<String, String> params = new HashMap<String, String>();
        params.put("page", String.valueOf(page));
        params.put("year", year);
        params.put("type", type);
        OkHttpHelper.postAsyncHttp(this,1001,
                params, UrlAddr.RAWMARKET,this);
    }
    List<String> year_list;
    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, String> paramsMaps) {
        isMore = true;
        switch (requestCode){
            case 1001:

                Gson gson = new Gson();
                RawMaterialBean rawMaterialBean = gson.fromJson(returnData, RawMaterialBean.class);
                RawMaterialBean.DataBean data = rawMaterialBean.getData();
                List<RawMaterialBean.DataBean.ListBean> list = data.getList();
                year_list = data.getYear_list();
                if (page == 1) {
                    if (list == null || list.size() == 0) {
                        adapter.loadMoreEnd();
                    } else {

                        adapter.setNewData(list);
                        adapter.loadMoreComplete();
                    }
                } else {
                    if (list == null || list.size() == 0) {
                        adapter.loadMoreEnd();
                    } else {
//                        List<RawMaterialBean.DataBean.ListBean> data1 = adapter.getData();
//                        data1.addAll(list);
                        Log.e("TAG_httpUtils","添加数据");
                        adapter.addData(list);
                        adapter.loadMoreComplete();
                    }
                }
                break;

        }
    }

    @Override
    public void onErrorResult(int errorCode, String errorExcep) {
        super.onErrorResult(errorCode, errorExcep);
        isMore = true;
        adapter.loadMoreFail();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.toolbar_tv_menu:
                if (year_list != null && year_list.size() > 0)
                showPoPupWin(v);
            break;
        }
    }
    BasePopWin basePopWin = null;
    private void showPoPupWin(View view){

        if (basePopWin == null) {
            basePopWin = new BasePopWin(RawMaterialActivity.this,
                    this,
                    DisplayUtil.dip2px(RawMaterialActivity.this, 80),
                    year_list);
            //监听窗口的焦点事件，点击窗口外面则取消显示
            basePopWin.getContentView().setOnFocusChangeListener(new View.OnFocusChangeListener() {

                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        basePopWin.dismiss();
                    }
                }
            });
        }
        //设置默认获取焦点
        basePopWin.setFocusable(true);
        //以某个控件的x和y的偏移量位置开始显示窗口
        basePopWin.showAsDropDown(view, -50, 0);
        //如果窗口存在，则更新
        basePopWin.update();

    }

    @Override
    public void setOnClickListener(String year) {
        toolbar_tv_menu.setText(year+"年");
        page = 1;
        initData(year,type);
    }

    @Override
    public void onLoadMoreRequested() {
        Log.e("TAG_httpUtils","isMore"+isMore);
        if (isMore){
            page++;
            initData(year,type);
        }
    }
}
