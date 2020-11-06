package com.yiweiyi.www.ui.details;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yiweiyi.www.R;
import com.yiweiyi.www.adapter.store.ImageAdapter;
import com.yiweiyi.www.base.BaseFragment;
import com.yiweiyi.www.base.CommonData;
import com.yiweiyi.www.model.ProdcataModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author: zsh 2020/9/24
 * desc:
 */
public class ProdcataFragment extends BaseFragment {

    @BindView(R.id.recycler_rv)
    RecyclerView recyclerRv;
    private Unbinder mUnbinder;
    private View mPagerView;
    private ImageAdapter adapter;
    private ProdcataModel.DataBean mDataBeanList;

    public static ProdcataFragment newInstance(ProdcataModel.DataBean listBean) {
        Bundle args = new Bundle();
        args.putParcelable("listBean", listBean);
        ProdcataFragment fragment = new ProdcataFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mDataBeanList = bundle.getParcelable("listBean");
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        mPagerView = super.onCreateView(inflater, container, savedInstanceState);

        if (mPagerView == null) {

            mPagerView = inflater.inflate(R.layout.frament_rc, container, false);
        }

        mUnbinder = ButterKnife.bind(this, mPagerView);

        return mPagerView;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }
    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerRv.setLayoutManager(layoutManager);
        initData();
    }

    private void initData() {
        String imgs = mDataBeanList.getImgs();
//        Log.e("TAG_ss",""+imgs.toString());
        if (imgs != null && imgs.indexOf(",")!=-1){
            String[] str=imgs.split(",");
            List<String> list = new ArrayList<>();
            for (String url : str) {
                if (url != null && url.startsWith("http")){
                    list.add(url);
                }else {
                    list.add(CommonData.mainUrl + url);

                }

            }
            Log.e("TAG_图片集合",""+list.size());
//            adapter.setNewData(list);
            adapter = new ImageAdapter(R.layout.item_image, list);
            recyclerRv.setAdapter(adapter);
        }

    }

    /**
     * dp 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mUnbinder != null)
            mUnbinder.unbind();

    }

}
