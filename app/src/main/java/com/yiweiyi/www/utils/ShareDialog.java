package com.yiweiyi.www.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmuiteam.qmui.alpha.QMUIAlphaTextView;
import com.yiweiyi.www.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author: zsh
 * 2020/9/27
 * desc:微信朋友圈分享
 */
public class ShareDialog {
    @BindView(R.id.wx_haoyou)
    TextView wxHaoyou;
    @BindView(R.id.wx_pyq)
    TextView wxPyq;
    @BindView(R.id.wx_img)
    ImageView wxImg;
    @BindView(R.id.wx_pyq_img)
    ImageView wxPyqImg;
    @BindView(R.id.back_tv)
    QMUIAlphaTextView backTv;

    private Activity mContext;
    private int w;
    private Dialog dialog;

    private View dialogView;

    //如果是-1证明不是列表打开的分享
    private int position = -1;

    public void setDialogCallBackListener(DialogCallBackListener dialogCallBackListener) {
        this.dialogCallBackListener = dialogCallBackListener;
    }

    private DialogCallBackListener dialogCallBackListener;

    public ShareDialog(Activity mContext, int w) {

        this.mContext = mContext;
        this.w = w;
        initDialog();
        initListener();
    }


    private void initListener() {
        //朋友圈
        wxPyq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialogCallBackListener != null)
                    dialogCallBackListener.wxPyqBack(position);

                dismiss();


            }
        });
        //朋友圈
        wxPyqImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialogCallBackListener != null)
                    dialogCallBackListener.wxPyqBack(position);
                dismiss();
                //PublicHttpUtil.share();
            }
        });

        //好友
        wxHaoyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialogCallBackListener != null)
                    dialogCallBackListener.wxHaoyouBack(position);
                dismiss();
                // PublicHttpUtil.share();
            }
        });
        //好友
        wxImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialogCallBackListener != null)
                    dialogCallBackListener.wxHaoyouBack(position);
                dismiss();
                // PublicHttpUtil.share();

            }
        });
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    /**
     * 初始化窗口
     */
    private void initDialog() {

        dialog = new Dialog(mContext, R.style.NoTitleDialog);

        dialogView = View.inflate(mContext, R.layout.dialog_share, null);

        ButterKnife.bind(this, dialogView);

        dialog.setContentView(dialogView);

        Window window = dialog.getWindow();

        if (window != null) {

            window.setWindowAnimations(R.style.dialog_translate_animation);

            WindowManager.LayoutParams params = window.getAttributes();

            params.height = WindowManager.LayoutParams.WRAP_CONTENT;

            params.width = WindowManager.LayoutParams.MATCH_PARENT;

            params.gravity = Gravity.BOTTOM;

            window.setAttributes(params);
        }


    }

    /**
     * 显示
     */
    public void show() {

        this.position = -1;

        if (dialog == null) return;

        if (dialog.isShowing()) return;

        dialog.show();
    }

    /**
     * 关闭
     */
    public void dismiss() {

        if (dialog == null) return;

        dialog.dismiss();
    }


    public void show(int position) {


        this.position = position;

        if (dialog == null) return;

        if (dialog.isShowing()) return;

        dialog.show();
    }


    public interface DialogCallBackListener {

        void wxHaoyouBack(int position);

        void wxPyqBack(int position);

    }
}
