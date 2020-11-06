package com.yiweiyi.www.ui.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yiweiyi.www.R;
import com.yiweiyi.www.api.UrlAddr;
import com.yiweiyi.www.base.CommonData;
import com.yiweiyi.www.base.TitleBaseActivity;
import com.yiweiyi.www.model.StoreManageModel;
import com.yiweiyi.www.utils.ImageUtils;
import com.yiweiyi.www.utils.PrfUtils;
import com.yiweiyi.www.utils.SpUtils;
import com.yiweiyi.www.view.CircleImageView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.xcd.com.mylibrary.help.OkHttpHelper;
import www.xcd.com.mylibrary.http.HttpInterface;

/**
 * @Author: zsh
 * 2020/9/25
 * desc:店铺管理
 */
public class StoreManageActivity extends TitleBaseActivity implements HttpInterface {

    @BindView(R.id.head_img)
    CircleImageView headImg;
    @BindView(R.id.compe_name_tv)
    TextView compeNameTv;
    @BindView(R.id.area_tv)
    TextView areaTv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.head_cl)
    LinearLayout headCl;
    @BindView(R.id.number_how_saw_me_tv)
    TextView numberHowSawMeTv;
    @BindView(R.id.number_call_records_tv)
    TextView numberCallRecordsTv;
    @BindView(R.id.number_reliable_tv)
    TextView numberReliableTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public View getChildLayout() {
        return View.inflate(mContext, R.layout.activity_store_manage, null);
    }


    private void initData() {
        String meShopId = PrfUtils.getMeShopId();
        Map<String, String> params = new HashMap<String, String>();
        params.put("user_id", SpUtils.getUserID());
        params.put("shop_id", meShopId +"");
        OkHttpHelper.postAsyncHttp(this,1001,
                params, UrlAddr.SHOP_DETAILS,this);
    }

    private void initView() {
        setGoneBaseLeftMenu();
        setBaseTitle(getResources().getString(R.string.store_manage));
        setBaseRightImgMenu(R.drawable.close);
    }

    @Override
    public void baseBack(View v) {

    }

    @Override
    public void baseMenuTextClickListener(View v) {

    }

    @Override
    public void baseMenuImgClickListener(View v) {
        finish();
    }

    String logo;
    String shop_name;
    String head;
    @OnClick({R.id.head_cl, R.id.who_saw_me_ll, R.id.call_records_ll, R.id.reliable_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head_cl: {
                //基本信息
                Intent intent = new Intent(StoreManageActivity.this,BasicInfoActivity.class);
                intent.putExtra("logo", logo);
                intent.putExtra("shop_name", shop_name);
                intent.putExtra("head", head);
                startActivityForResult(intent,1000);
            }

            break;
            case R.id.who_saw_me_ll: {
                //谁看过我
                Intent intent = new Intent(this,CallRecordsActivity.class);
                intent.putExtra("type","1");
                startActivity(intent);
            }
            break;
            case R.id.call_records_ll: {
                //通话记录
                Intent intent = new Intent(this,CallRecordsActivity.class);
                intent.putExtra("type","2");
                startActivity(intent);
            }
                break;
            case R.id.reliable_ll:
            {
                //靠谱
                Intent intent = new Intent(this,CallRecordsActivity.class);
                intent.putExtra("type","3");
                startActivity(intent);
            }
                break;
        }
    }

    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, String> paramsMaps) {
        switch (requestCode) {
            case 1001:
                Gson gson = new Gson();
                StoreManageModel storeManageModel = gson.fromJson(returnData, StoreManageModel.class);
                StoreManageModel.DataBean data = storeManageModel.getData();
                StoreManageModel.DataBean.InfoBean info = data.getInfo();
                shop_name = info.getShop_name();
                compeNameTv.setText(shop_name);
                String area = info.getArea();
                areaTv.setText(area);
                head = info.getHead();
                nameTv.setText(head);
                String imageLogo = info.getLogo();
                if (imageLogo != null && imageLogo.indexOf("http")!=-1){
                    this.logo = imageLogo;
                }else {
                    this.logo = CommonData.mainUrl + imageLogo;
                }

                ImageUtils.setImage(headImg, this.logo, 3000, R.mipmap.ic_launcher);

                int browse_total = data.getBrowse_total();
                numberHowSawMeTv.setText("("+browse_total+")");
//                int browse = info.getBrowse();
//                numberHowSawMeTv.setText("("+browse+")");
                int call_log_total = data.getCall_log_total();
                numberCallRecordsTv.setText("("+call_log_total+")");
                int like_num = data.getLike_num();
                numberReliableTv.setText("("+like_num+")");
                break;
        }

    }

    @Override
    public void onErrorResult(int requestCode, String returnMsg) {

    }
}
