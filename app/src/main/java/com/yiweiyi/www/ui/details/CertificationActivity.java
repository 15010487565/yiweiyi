package com.yiweiyi.www.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.yiweiyi.www.R;
import com.yiweiyi.www.adapter.store.CerImageAdapter;
import com.yiweiyi.www.api.UrlAddr;
import com.yiweiyi.www.base.CommonData;
import com.yiweiyi.www.model.CertificationMapModel;
import com.yiweiyi.www.model.CertificationModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.help.OkHttpHelper;
import www.xcd.com.mylibrary.utils.ToastUtil;

public class CertificationActivity extends SimpleTopbarActivity implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.recycler_rv)
    RecyclerView recyclerRv;
    @BindView(R.id.view_empty)
    View view_empty;
    CerImageAdapter imageAadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);

        resetTopbarTitle("资质证书");
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "3");
        params.put("shop_id", getIntent().getIntExtra(DetailsActivity.SHOPEID, 0) + "");
        OkHttpHelper.postAsyncHttp(this, 1003,
                params, UrlAddr.ALBUMLIST3, this);

    }

    @Override
    protected void afterSetContentView() {
        super.afterSetContentView();
        ButterKnife.bind(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerRv.setLayoutManager(layoutManager);
        imageAadapter = new CerImageAdapter(R.layout.item_cer_image, null);
        imageAadapter.setOnItemClickListener(this);
        recyclerRv.setAdapter(imageAadapter);
    }

    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, String> paramsMaps) {
        Gson gson = new Gson();
        switch (requestCode) {

            case 1003:

                CertificationModel certificationModel = gson.fromJson(returnData, CertificationModel.class);
                CertificationModel.DataBean data1 = certificationModel.getData();
                List<String> img_list = data1.getImg_list();
                if (img_list == null || img_list.size() == 0) {
                    recyclerRv.setVisibility(View.GONE);
                    view_empty.setVisibility(View.VISIBLE);
                } else {
                    recyclerRv.setVisibility(View.VISIBLE);
                    view_empty.setVisibility(View.GONE);
                    List<CertificationMapModel> list = new ArrayList<>();
                    List<CertificationModel.DataBean.ImgListArrBean> img_list_arr = data1.getImg_list_arr();
                    for (int i = 0; i < img_list.size(); i++) {
                        CertificationMapModel map = new CertificationMapModel();
                        String url = img_list.get(i);
                        if (url != null && url.startsWith("http")){
                            map.setUrl(url);
                        }else {
                            map.setUrl(CommonData.mainUrl + url);
                        }
                        if (i < img_list_arr.size()){
                            CertificationModel.DataBean.ImgListArrBean imgListArrBean = img_list_arr.get(i);
                            float width = imgListArrBean.getWidth();
                            float height = imgListArrBean.getHeight();
                            float v = width / height;
                            map.setWidth_height(v);
                        }
                        list.add(map);
                    }
                    imageAadapter.setNewData(list);
                }
                break;

        }
    }

    @Override
    public void onErrorResult(int errorCode, String errorExcep) {
        ToastUtil.showToast(errorExcep);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List<CertificationMapModel> data = imageAadapter.getData();
        CertificationMapModel model = data.get(position);
        String url = model.getUrl();
        Intent piCoinInt = new Intent(this, WebX5FileActivity.class);
        piCoinInt.putExtra("url", url);
        String name = url.substring(url.lastIndexOf("/") + 1).toLowerCase();
//        name = name.substring(name.lastIndexOf(".") + 1).toLowerCase();
        Log.e("TAG_name","name="+name);
        piCoinInt.putExtra("name", name);
        piCoinInt.putExtra("title", "详情");
        startActivity(piCoinInt);
    }
}
