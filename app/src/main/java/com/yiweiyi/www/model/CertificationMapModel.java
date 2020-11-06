package com.yiweiyi.www.model;

/**
 * Created by gs on 2020/10/30.
 */
public class CertificationMapModel {

    private String url;
    private float width_height;//宽/高

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public float getWidth_height() {
        return width_height == 0 ? 1 : width_height;
    }

    public void setWidth_height(float width_height) {
        this.width_height = width_height;
    }
}
