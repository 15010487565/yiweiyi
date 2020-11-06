package com.yiweiyi.www.ui.store;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.d.lib.slidelayout.SlideLayout;
import com.google.gson.Gson;
import com.yiweiyi.www.R;
import com.yiweiyi.www.adapter.store.PhoneListAdapter;
import com.yiweiyi.www.api.UrlAddr;
import com.yiweiyi.www.dialog.AddPhoneDialogFragment;
import com.yiweiyi.www.model.PhoneListModel;
import com.yiweiyi.www.utils.PrfUtils;
import com.yiweiyi.www.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.help.OkHttpHelper;

/**
 * @Author: zsh
 * 2020/9/27
 * desc:联系电话
 */
public class PhoneListActivity extends SimpleTopbarActivity implements  BaseQuickAdapter.OnItemChildClickListener{

    @BindView(R.id.recycler_rv)
    RecyclerView recyclerRv;

    private PhoneListAdapter mPhoneListAdapter;

//    private static Class<?> rightFuncArray[] = {EditRightTopBtnFunc.class};
//
//    @Override
//    protected Class<?>[] getTopbarRightFuncArray() {
//        return rightFuncArray;
//    }

    @Override
    protected Object getTopbarTitle() {
        return "联系电话";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_all);
        initData();
    }

    @Override
    protected void afterSetContentView() {
        super.afterSetContentView();
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        recyclerRv.setLayoutManager(new LinearLayoutManager(this));
        mPhoneListAdapter = new PhoneListAdapter(R.layout.item_phone_list, null);
        mPhoneListAdapter.setOnItemChildClickListener(this);
        recyclerRv.setAdapter(mPhoneListAdapter);
        View inflate = View.inflate(this, R.layout.foot_phone_list, null);
        LinearLayout ll_AddPhone = inflate.findViewById(R.id.ll_AddPhone);
        ll_AddPhone.setOnClickListener(this);
        mPhoneListAdapter.addFooterView(inflate);
        recyclerRv.addItemDecoration(getRecyclerViewDivider(R.drawable.inset_recyclerview_divider_1));
    }

    private void initData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("shop_id", PrfUtils.getMeShopId());
        OkHttpHelper.postAsyncHttp(this,1001,
                params, UrlAddr.GETSHOPPHONE,this);
    }

//    public void editor(){
//
//    }
    public void addPhone(String phone) {
        List<String> data = mPhoneListAdapter.getData();
        boolean contains = data.contains(phone);
        if (contains){
            ToastUtils.showToast( "该手机号已存在!");
        }else {
            data.add(phone);
            editPhone(data);
        }

    }

    private void editPhone(List<String> data) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < data.size(); i++) {
            String phone = data.get(i).trim();
            if (i == 0){
                buffer.append(phone);
            }else {
                buffer.append(",");
                buffer.append(phone);
            }
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put("shop_id", PrfUtils.getMeShopId());
        params.put("phone", buffer.toString());
        OkHttpHelper.postAsyncHttp(this, 1002,
                params, UrlAddr.EDITSHOPPHONE, this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ll_AddPhone:

                AddPhoneDialogFragment dialogFr = new AddPhoneDialogFragment();
                dialogFr.show(getSupportFragmentManager(), "AddPhone");

                break;
        }
    }

    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, String> paramsMaps) {
        switch (requestCode){
            case 1001:
                Gson gson = new Gson();
                PhoneListModel phoneListModel = gson.fromJson(returnData, PhoneListModel.class);
                List<String> data = phoneListModel.getData();
                mPhoneListAdapter.setNewData(data);
                break;
            case 1002:
                initData();

                break;
        }
    }

    @Override
    public void onErrorResult(int requestCode, String returnMsg) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        List<String> data = mPhoneListAdapter.getData();
        if (data != null && data.size() > 0) {
            switch (view.getId()) {
                case R.id.ll_Del: //删除
                    ViewParent parent = view.getParent();
                    if (parent instanceof SlideLayout) {
                        SlideLayout slItem = (SlideLayout) parent;
                        if (slItem.isOpen()) {
                            slItem.close();
                        }
                        data.remove(position);
                        editPhone(data);

                    }

                    break;
                case R.id.delete_:
                    data.remove(position);
                    editPhone(data);
                    break;
            }
        }
    }
}
