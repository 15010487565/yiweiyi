package com.yiweiyi.www.func;

import android.app.Activity;
import android.view.View;

import com.yiweiyi.www.R;

import www.xcd.com.mylibrary.func.BaseTopTextViewFunc;


public class EditRightTopBtnFunc extends BaseTopTextViewFunc {

    public EditRightTopBtnFunc(Activity activity) {
        super(activity);
    }

    @Override
    public int getFuncId() {
        return R.id.ok;
    }

    @Override
    protected int getFuncTextRes() {
        return R.string.ok;
    }

    @Override
    public void onclick(View v) {

//        ((PhoneListActivity) getActivity()).editor();
    }

}
