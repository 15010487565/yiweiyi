package com.yiweiyi.www.ui.search;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qmuiteam.qmui.alpha.QMUIAlphaButton;
import com.yiweiyi.www.R;
import com.yiweiyi.www.adapter.search.MorePhoneAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author: zsh
 * 2020/10/8
 * desc:更多号码弹框
 */
public class MorePhoneDialog {

    @BindView(R.id.phone_rv)
    RecyclerView phoneRv;
    @BindView(R.id.close_bt)
    QMUIAlphaButton closeBt;
    private Context mContext;
    private Dialog dialog;
    private View dialogView;
    private MorePhoneAdapter mMorePhoneAdapter;

    public MorePhoneDialog(Context mContext) {

        this.mContext = mContext;
        initDialog();
        initListener();
    }

    private void initDialog() {

        dialog = new Dialog(mContext, R.style.NoTitleDialog);

        dialogView = View.inflate(mContext, R.layout.dialog_phone_list, null);

        ButterKnife.bind(this, dialogView);

        dialog.setContentView(dialogView);

        Window window = dialog.getWindow();

        if (window != null) {

            window.setWindowAnimations(R.style.dialog_translate_animation);

            WindowManager.LayoutParams params = window.getAttributes();
            params.gravity = Gravity.CENTER;

            window.setAttributes(params);
        }
        initData();
    }

    private void initData() {
        phoneRv.setLayoutManager(new LinearLayoutManager(mContext));
        List<String> dataList = new ArrayList<>();
        mMorePhoneAdapter = new MorePhoneAdapter(R.layout.item_more_phone, dataList);
        //添加Android自带的分割线
        phoneRv.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        phoneRv.setAdapter(mMorePhoneAdapter);
    }

    private void initListener() {
        closeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
//        mMorePhoneAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                HelpUtils.call(mContext,phone.get(position),true);
//            }
//        });
    }

    /**
     * 关闭
     */
    public void dismiss() {

        if (dialog == null) return;

        dialog.dismiss();
    }

    /**
     * 显示
     */
    List<String> phone;
    public void show(List<String> phone) {
        this.phone = phone;
        if (dialog == null) return;

        if (dialog.isShowing()) return;

        Window window = dialog.getWindow();
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager m = window.getWindowManager();
        m.getDefaultDisplay().getMetrics(dm);
        if (phone != null && phone.size() > 3) {
            window.setLayout((int) (dm.heightPixels * 0.4), (int) (dm.heightPixels * 0.3));
        } else {
            window.setLayout((int) (dm.heightPixels * 0.4), -2);
        }

        mMorePhoneAdapter.replaceData(phone);

        dialog.show();
    }
}
