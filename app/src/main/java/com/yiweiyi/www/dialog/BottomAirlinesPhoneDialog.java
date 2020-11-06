package com.yiweiyi.www.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.yiweiyi.www.R;

import www.xcd.com.mylibrary.help.HelpUtils;

/**
 * Data:  2020/4/27
 * Auther: xcd
 * Description:
 */
public class BottomAirlinesPhoneDialog extends DialogFragment implements View.OnClickListener {

    private View frView;
    private Window window;
    private TextView tv_callphone,tv_cancel;

    private String phone;
    public void setData(String phone) {
        this.phone = phone;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 去掉默认的标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置背景透明
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        frView = inflater.inflate(R.layout.dialog_fr_bottom, null);

        return frView;
    }

    @Override
    public void onStart() {
        super.onStart();
        window = getDialog().getWindow();
        // 如果不设置这句代码, 那么弹框就会与四边都有一定的距离
//        window.setBackgroundDrawableResource(R.drawable.shape_white);
        // 设置动画
        window.setWindowAnimations(R.style.bottomDialog);
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        // 如果不设置宽度,那么即使你在布局中设置宽度为 match_parent 也不会起作用
        params.height = getResources().getDisplayMetrics().heightPixels;
        window.setAttributes(params);
        final Dialog dialog = getDialog();
        // 下面这些设置必须在此方法(onStart())中才有效
        tv_callphone = (TextView) frView.findViewById(R.id.tv_callphone);
        tv_callphone.setText("呼叫\t"+phone);
        tv_callphone.setOnClickListener(this);

        tv_cancel = (TextView) frView.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//            if (list != null && list.size() > 7) {
//                window.setLayout(-1, (int) (dm.heightPixels * 0.4));
//            } else {
                window.setLayout(-1, -2);
//            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.tv_callphone:

                HelpUtils.call(getActivity(),phone,false);
                dismiss();
                if (listener != null){
                    listener.callPhone(phone);
                }
                break;

            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }
    private CallBack listener;
    public void setOnClickCallPhone(CallBack listener){
        this.listener = listener;
    }
    public interface CallBack{
        public void callPhone(String phone);
    }
}
