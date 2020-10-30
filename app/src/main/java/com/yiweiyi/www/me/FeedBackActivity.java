package com.yiweiyi.www.me;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qmuiteam.qmui.alpha.QMUIAlphaButton;
import com.yiweiyi.www.R;
import com.yiweiyi.www.api.UrlAddr;
import com.yiweiyi.www.utils.SpUtils;

import java.util.HashMap;
import java.util.Map;

import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.help.OkHttpHelper;
import www.xcd.com.mylibrary.utils.ToastUtil;

public class FeedBackActivity extends SimpleTopbarActivity {

    private QMUIAlphaButton submit_feedback_bt;
    private EditText feedback_et;
    private TextView number_tv;

    @Override
    protected Object getTopbarTitle() {
        return "意见反馈";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        submit_feedback_bt = findViewById(R.id.submit_feedback_bt);
        submit_feedback_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }


        });
        number_tv = findViewById(R.id.number_tv);
        feedback_et = findViewById(R.id.feedback_et);
        feedback_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = feedback_et.getText().toString().trim();
                number_tv.setText(trim.length()+"/200");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, String> paramsMaps) {
        switch (requestCode){
            case 1001:
                ToastUtil.showToast(returnMsg);
                finish();
                break;


        }
    }

    @Override
    public void onErrorResult(int errorCode, String errorExcep) {
        ToastUtil.showToast(errorExcep);
    }

    private void submit() {
        String trim = feedback_et.getText().toString().trim();
        ToastUtil.showToast("提交内容不能为空！");
        if (TextUtils.isEmpty(trim)){
            return;
        }
        String userID = SpUtils.getUserID();
        Map<String, String> params = new HashMap<String, String>();
        params.put("user_id", userID);
        params.put("content", trim);
        OkHttpHelper.postAsyncHttp(this,1001,
                params, UrlAddr.RAWMARKET,this);
    }
}
