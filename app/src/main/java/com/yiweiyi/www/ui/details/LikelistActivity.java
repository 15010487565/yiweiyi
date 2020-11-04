package com.yiweiyi.www.ui.details;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.yiweiyi.www.R;
import com.yiweiyi.www.adapter.store.LikeAdapter;
import com.yiweiyi.www.api.ApiManager;
import com.yiweiyi.www.api.UrlAddr;
import com.yiweiyi.www.bean.personal.FreeEntryBean;
import com.yiweiyi.www.dialog.BottomAirlinesPhoneDialog;
import com.yiweiyi.www.model.LikeModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.help.OkHttpHelper;
import www.xcd.com.mylibrary.utils.ToastUtil;


public class LikelistActivity extends SimpleTopbarActivity {

    RecyclerView rcLike;
    LikeAdapter adapter;
    TextView number_tv;

    @Override
    protected Object getTopbarTitle() {
        return "靠谱";
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likelist);

        int shop_id = getIntent().getIntExtra(DetailsActivity.SHOPEID, 0);
        Map<String, String> params = new HashMap<String, String>();
        params.put("shop_id", shop_id+"");
        params.put("page", "0");
        OkHttpHelper.postAsyncHttp(this,1001,
                params, UrlAddr.LIKE,this);
    }

    @Override
    protected void afterSetContentView() {
        super.afterSetContentView();
        number_tv = findViewById(R.id.number_tv);
        //设置布局管理器
        rcLike = findViewById(R.id.rc_like);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcLike.setLayoutManager(linearLayoutManager);
        //设置适配器
        adapter = new LikeAdapter(R.layout.iten_like, null);
        rcLike.setAdapter(adapter);
    }

    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, String> paramsMaps) {
        switch (requestCode){
            case 1001:
                Gson gson = new Gson();
                LikeModel likeModel = gson.fromJson(returnData, LikeModel.class);
                LikeModel.DataBean data = likeModel.getData();
                number_tv.setText(data.getTotal()+"人觉得这家公司很靠谱");
                List<LikeModel.DataBean.ListBean> list = data.getList();

                if (list != null && list.size() > 0){
                    adapter.setNewData(list);
                }else {
                    View emptyView = getLayoutInflater().inflate(R.layout.view_empty, null);
                    adapter.setEmptyView(emptyView);
                    findViewById(R.id.advisory_service).setOnClickListener(new View.OnClickListener() {
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
                break;


        }
    }

    @Override
    public void onErrorResult(int errorCode, String errorExcep) {
        ToastUtil.showToast(errorExcep);
    }
}
