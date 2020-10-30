package com.yiweiyi.www.func;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.yiweiyi.www.R;
import com.yiweiyi.www.me.UserinfoActivity;
import com.yiweiyi.www.utils.ImageUtils;
import com.yiweiyi.www.utils.PrfUtils;
import com.yiweiyi.www.view.CircleImageView;

import www.xcd.com.mylibrary.func.BaseFunc;


/**
 * 头像
 *
 * @author litfb
 * @version 1.0
 * @date 2014年10月16日
 */
public class AccountHeadFunc extends BaseFunc {

    CircleImageView imageView;

    public AccountHeadFunc(Activity activity) {
        super(activity);
    }

    @Override
    public int getFuncId() {
        return R.id.account_head;
    }

    @Override
    public int getFuncIcon() {
        return 0;
    }

    @Override
    public int getFuncName() {
        return R.string.head;
    }

    @Override
    public void onclick() {
		((UserinfoActivity)getActivity()).getChoiceDialog().show();

    }

    @Override
    public View initFuncView(boolean isSeparator, Object... params) {
        View funcView = super.initFuncView(isSeparator, params);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params2.topMargin = (int) getActivity().getResources().getDimension(R.dimen.me_vertical_margin);
        funcView.setLayoutParams(params2);
        return funcView;
    }

    @Override
    protected void initCustomView(LinearLayout customView) {
        super.initCustomView(customView);
        //创建保存布局参数的对象
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                        , LinearLayout.LayoutParams.WRAP_CONTENT);
        imageView = new CircleImageView(getActivity());
        int maxhw = (int) getActivity().getResources().getDimension(R.dimen.margin_35);
        params.height = maxhw;
        params.width = maxhw;
        imageView.setLayoutParams(params);//设置布局参数
//		imageView.setScaleType(ImageView.ScaleType.FIT_XY);//设置图片自动缩放
        customView.setGravity(Gravity.RIGHT);
        customView.addView(imageView);

        refreshHead();
    }

    public void refreshHead() {
        String headimg = PrfUtils.getHeadimgurl();
        ImageUtils.setImage(imageView, headimg, 3000, R.drawable.no_login);
    }
}
