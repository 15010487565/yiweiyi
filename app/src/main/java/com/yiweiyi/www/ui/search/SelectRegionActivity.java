package com.yiweiyi.www.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.alpha.QMUIAlphaButton;
import com.yiweiyi.www.R;
import com.yiweiyi.www.adapter.store.ContactAdapter;
import com.yiweiyi.www.adapter.store.ContactHeaderItemAdapter;
import com.yiweiyi.www.base.BaseBean;
import com.yiweiyi.www.base.TitleBaseActivity;
import com.yiweiyi.www.bean.search.AreaBean;
import com.yiweiyi.www.bean.search.CommonAreasListBean;
import com.yiweiyi.www.presenter.SearchPresenter;
import com.yiweiyi.www.utils.SpUtils;
import com.yiweiyi.www.utils.ToastUtils;
import com.yiweiyi.www.view.search.AddCommonAreasView;
import com.yiweiyi.www.view.search.CommonAreasListView;
import com.yiweiyi.www.view.search.DelCommonAreasView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.indexablerv.IndexableAdapter;
import me.yokeyword.indexablerv.IndexableHeaderAdapter;
import me.yokeyword.indexablerv.IndexableLayout;

;

/**
 * @Author: zsh
 * 2020/10/9
 * desc:选择地区
 */
public class SelectRegionActivity extends TitleBaseActivity implements CommonAreasListView,
        AddCommonAreasView, DelCommonAreasView {


    @BindView(R.id.indexableLayout)
    IndexableLayout indexableLayout;

    public static String ALLDATA = "alldata";
    public static String DATA = "data";

    private String mAlldiqu;
    private String mDiqu;
    private SearchPresenter mSearchPresenter;
    private List<AreaBean> mAreaBeans = new ArrayList<>();
    private ContactAdapter mContactAdapter;
    private Intent intent;
    private List<String> mHot_area;
    private List<String> mUsed_area;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mSearchPresenter = new SearchPresenter(this);
        initView();
        initData();
    }

    private void initListener() {
        mContactAdapter.setOnItemContentClickListener(new IndexableAdapter.OnItemContentClickListener<AreaBean>() {
            @Override
            public void onItemClick(View v, int originalPosition, int currentPosition, AreaBean entity) {
                if (originalPosition >= 0) {
                    intent.putExtra("city", entity.getArea());
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    ToastUtils.showToast("选中Header/Footer:" + entity.getArea() + "  当前位置:" + currentPosition);
                }
            }
        });
    }

    private void initView() {
        setBaseTitle(getResources().getString(R.string.selectRegion));
        intent = getIntent();
        mAlldiqu = getIntent().getStringExtra(ALLDATA);
        mDiqu = getIntent().getStringExtra(DATA);

    }

    @Override
    protected void onDestroy() {
        mSearchPresenter = null;
        super.onDestroy();
    }

    private void initData() {
        mSearchPresenter.commonAreasList(SpUtils.getUserID(), mAlldiqu);
    }

    private void setAllArea() {
        indexableLayout.setLayoutManager(new LinearLayoutManager(this));
        mContactAdapter = new ContactAdapter(this);
        indexableLayout.setAdapter(mContactAdapter);
        indexableLayout.setOverlayStyle_Center();
        indexableLayout.showAllLetter(false);
        indexableLayout.setCompareMode(IndexableLayout.MODE_FAST);
        List<String> bannerList = new ArrayList<>();
        bannerList.add("");
        ContactHeaderAdapter mBannerHeaderAdapter = new ContactHeaderAdapter("热门", null, bannerList);
        indexableLayout.addHeaderAdapter(mBannerHeaderAdapter);
    }


    @Override
    public View getChildLayout() {
        return View.inflate(mContext, R.layout.activity_select_region, null);
    }

    @Override
    public void baseBack(View v) {
        finish();
    }

    @Override
    public void baseMenuTextClickListener(View v) {

    }

    @Override
    public void baseMenuImgClickListener(View v) {

    }


    @Override
    public void onCommonAreasListSuccess(CommonAreasListBean baseBean) {

        mUsed_area = baseBean.getData().getUsed_area();
        mHot_area = baseBean.getData().getHot_area();
        for (int i = 0; i < baseBean.getData().getArea().size(); i++) {
            for (int j = 0; j < baseBean.getData().getArea().get(i).getArea().size(); j++) {
                AreaBean areaBean = new AreaBean();
                areaBean.setArea(baseBean.getData().getArea().get(i).getArea().get(j));
                mAreaBeans.add(areaBean);
            }
        }
        setAllArea();
        initListener();
        mContactAdapter.setDatas(mAreaBeans);
    }

    @Override
    public void onError(String e) {

    }

    @Override
    public void onAddCommonAreasSuccess(BaseBean baseBean) {
        mSearchPresenter.commonAreasList(SpUtils.getUserID(), mAlldiqu);
    }

    @Override
    public void onDelCommonAreasSuccess(BaseBean baseBean) {
        mSearchPresenter.commonAreasList(SpUtils.getUserID(), mAlldiqu);
    }

    class ContactHeaderAdapter extends IndexableHeaderAdapter {
        private static final int TYPE = 1;

        public ContactHeaderAdapter(String index, String indexTitle, List datas) {
            super(index, indexTitle, datas);

        }

        @Override
        public int getItemViewType() {
            return TYPE;
        }

        @Override
        public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.header_city, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindContentViewHolder(RecyclerView.ViewHolder holder, Object entity) {
            // 数据源为null时, 该方法不用实现
            final ViewHolder vh = (ViewHolder) holder;
            if (!mDiqu.equals("全部")) {
                vh.currentAreaTv.setText(mDiqu);
                vh.currentAreaBtn.setEnabled(true);
            }

            if (mUsed_area != null && mUsed_area.size() != 0) {
                vh.ll_common_area.setVisibility(View.VISIBLE);
                ContactHeaderItemAdapter commonlyAdapter = new ContactHeaderItemAdapter(R.layout.item_contact_header_item, mUsed_area);
                vh.commonAreaRv.setLayoutManager(new GridLayoutManager(mContext, 4));
                vh.commonAreaRv.setAdapter(commonlyAdapter);
                commonlyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        intent.putExtra("city", commonlyAdapter.getItem(position));
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
                commonlyAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                        mSearchPresenter.delCommonAreas(SpUtils.getUserID(), commonlyAdapter.getItem(position));
                        return true;
                    }
                });
            }else {
                vh.ll_common_area.setVisibility(View.GONE);
            }


            ContactHeaderItemAdapter hotAreaRvAdapter = new ContactHeaderItemAdapter(R.layout.item_contact_header_item, mHot_area);
            vh.hotAreaRv.setLayoutManager(new GridLayoutManager(mContext, 4));
            vh.hotAreaRv.setAdapter(hotAreaRvAdapter);
            hotAreaRvAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    intent.putExtra("city", hotAreaRvAdapter.getItem(position));
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });

            vh.currentAreaBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSearchPresenter.addCommonAreas(SpUtils.getUserID(), mDiqu);
                }
            });
        }


        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.current_area_tv)
            TextView currentAreaTv;
            @BindView(R.id.current_area_btn)
            QMUIAlphaButton currentAreaBtn;
            @BindView(R.id.ll_common_area)
            LinearLayout ll_common_area;
            @BindView(R.id.common_area_rv)
            RecyclerView commonAreaRv;
            @BindView(R.id.hot_area_)
            TextView hotArea;
            @BindView(R.id.hot_area_rv)
            RecyclerView hotAreaRv;

            public ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
//        overridePendingTransition(R.anim.zoom_enter,
//                R.anim.zoom_exit);
    }
}
