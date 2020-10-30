package com.yiweiyi.www.bean.search;

import me.yokeyword.indexablerv.IndexableEntity;

/**
 * @author: zsh
 * 2020/10/12 0012
 * @Description:
 */
public class AreaBean implements IndexableEntity {
    private String area;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String getFieldIndexBy() {
        return area;
    }

    @Override
    public void setFieldIndexBy(String indexField) {
        this.area = indexField;
    }

    @Override
    public void setFieldPinyinIndexBy(String pinyin) {

    }
}
