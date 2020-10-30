package com.yiweiyi.www.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yiweiyi.www.R;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

/**
 * @author:zsh 2020/10/10 0010
 * @Description: 轮播图适配器
 */
public class BannerStringAdapter extends BannerAdapter<String, BannerStringAdapter.ImgViewHolder> {


    private Context mContext;

    private List<String> datas;

    public BannerStringAdapter(Context mContext, List<String> datas) {
        super(datas);

        this.datas = datas;

        this.mContext = mContext;
    }

    @Override
    public ImgViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new ImgViewHolder(LayoutInflater.from(mContext).inflate(R.layout.banner_new_layout,parent,false));
    }

    @Override
    public void onBindView(ImgViewHolder holder, String data, int position, int size) {

        if (data == null){
            return;
        }

        Glide.with(holder.imageView)
                .asDrawable()
                .load(data)
//                .centerCrop()
                .into(holder.imageView);


    }

    public List<String> getDatas() {
        return datas;
    }

    public void setData(List<String> datas) {

        if (this.datas == null){

            this.datas = datas;
        }else {

            this.datas.clear();
            this.datas.addAll(datas);
        }
    }


    class ImgViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        public ImgViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.iv);
        }
    }
}
