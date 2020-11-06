package com.yiweiyi.www.func;

import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiweiyi.www.R;
import com.yiweiyi.www.ui.me.SingleEditActivity;
import com.yiweiyi.www.utils.PrfUtils;

import www.xcd.com.mylibrary.func.BaseFunc;

public class AccountNameFunc extends BaseFunc {

    public AccountNameFunc(Activity activity) {
        super(activity);
    }

    @Override
    public int getFuncId() {
        return R.id.account_name;
    }

    @Override
    public int getFuncIcon() {
        return 0;
    }

    @Override
    public int getFuncName() {
        return R.string.nickname;
    }

    @Override
    public void onclick() {
        Intent intent = new Intent(getActivity(), SingleEditActivity.class);
        intent.putExtra("title","昵称");
        intent.putExtra("key","nickname");
        String name = PrfUtils.getNickname();
        intent.putExtra("content",name);
        getActivity().startActivityForResult(intent,1);
    }

    @Override
    public View initFuncView(boolean isSeparator, Object... params) {
        View funcView = super.initFuncView(true, params);
        return funcView;
    }
    TextView textview;
    @Override
    protected void initCustomView(LinearLayout customView) {
        super.initCustomView(customView);
        //创建保存布局参数的对象
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT
                        , LinearLayout.LayoutParams.MATCH_PARENT);
        textview = new TextView(getActivity());
        textview.setLayoutParams(params);//设置布局参数
        resetRightName();
        textview.setTextSize(12);
        textview.setGravity(Gravity.RIGHT);
        textview.setTextColor(getActivity().getResources().getColor(R.color.black_66));
        customView.addView(textview);
    }
    public void resetRightName(){
        String name = PrfUtils.getNickname();
        textview.setText(name);
    }
}
