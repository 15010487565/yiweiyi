package com.yiweiyi.www.me;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.yiweiyi.www.R;

import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.utils.ToastUtil;

public class SingleEditActivity extends SimpleTopbarActivity {

    private EditText etSingle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_edit);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");

        resetTopbarTitle(title);
        etSingle = findViewById(R.id.et_Single);
        String dese = intent.getStringExtra("content");
        if (!TextUtils.isEmpty(dese)){
            etSingle.setText(dese);
        }
        etSingle.setHint("请输入"+title);
        findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor();
            }
        });
    }
    public void editor(){
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        String text = etSingle.getText().toString();
        if (TextUtils.isEmpty(text)){
            ToastUtil.showToast("修改内容不能为空！");

        }else {
            Log.e("TAG_Single","key="+key+";content="+text);
            Intent intent1 = new Intent();
            intent1.putExtra("key",key);
            intent1.putExtra("content",text);
            setResult(Activity.RESULT_OK, intent1);
            finish();
        }
    }
}
