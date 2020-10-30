package com.yiweiyi.www.utils;

import android.annotation.SuppressLint;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yiweiyi.www.R;

import www.xcd.com.mylibrary.base.application.XCDApplication;

/**
 * Created by gs on 2020/8/11.
 */
public class ImageUtils {


    public static void setImage(ImageView imageView, String url) {
        setImage(imageView, url, 10, R.mipmap.album_photo_default);
    }

    public static void setImage(ImageView imageView, String url, float rounded) {
        setImage(imageView, url, rounded, R.mipmap.album_photo_default);
    }

    @SuppressLint("ResourceType")
    public static void setImage(ImageView imageView, String url, float rounded, @DrawableRes int resourceId) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(resourceId)
                .error(resourceId)
                .skipMemoryCache(true)//跳过内存缓存
                .diskCacheStrategy(DiskCacheStrategy.ALL)//不要在disk硬盘缓存;
                .fitCenter() /*处理源图片ScaleType*/
                .transform(new RoundedCornersTransform(rounded, rounded, rounded, rounded));
        if (resourceId > 0) {
            Glide.with(XCDApplication.getAppContext())
                    .load(url)
                    .apply(requestOptions)
                    .into(imageView);
        } else {
            Glide.with(XCDApplication.getAppContext())
                    .load(url)
                    .into(imageView);
        }
    }

}
