package com.yiweiyi.www.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.yiweiyi.www.R;
import com.yiweiyi.www.ui.store.PhoneListActivity;
import com.yiweiyi.www.utils.PrfUtils;
import com.yiweiyi.www.utils.RegexUtils;
import com.yiweiyi.www.utils.ToastUtils;

/**
 * Data:  2020/4/27
 * Auther: xcd
 * Description:
 */
public class AddPhoneDialogFragment extends DialogFragment implements View.OnClickListener {

    private View frView;
    private Window window;
    EditText etPhone;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 去掉默认的标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        frView = inflater.inflate(R.layout.dialog_fr_post, null);

        return frView;
    }

    @Override
    public void onStart() {
        super.onStart();
        window = getDialog().getWindow();
        etPhone = frView.findViewById(R.id.et_AddPhone);

        frView.findViewById(R.id.tv_cancel).setOnClickListener(this);

        TextView tv_abandon = frView.findViewById(R.id.tv_add);
        tv_abandon.setOnClickListener(this);

        WindowManager.LayoutParams params = window.getAttributes();
//        params.gravity = Gravity.BOTTOM;
        // 如果不设置宽度,那么即使你在布局中设置宽度为 match_parent 也不会起作用
        params.height = getResources().getDisplayMetrics().heightPixels;
        window.setAttributes(params);
        final Dialog dialog = getDialog();

        if (dialog != null) {
//            DisplayMetrics dm = new DisplayMetrics();
//            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

            window.setLayout((int) (PrfUtils.getWidthPixel()*0.75), -2);

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_add:
                String phone = etPhone.getText().toString().trim();
                boolean isPhone = RegexUtils.checkMobile(phone);
                if (isPhone) {
                    ((PhoneListActivity)getActivity()).addPhone(phone);
                } else {
                    ToastUtils.showToast( getString(R.string.please_enter_valid_phone));
                }

                dismiss();
                break;
        }
    }

}
