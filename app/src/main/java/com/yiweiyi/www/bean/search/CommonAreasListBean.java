package com.yiweiyi.www.bean.search;

import com.yiweiyi.www.base.BaseBean;

import java.util.List;

/**
 * @Author: zsh
 * 2020/10/9
 * desc:
 */
public class CommonAreasListBean extends BaseBean {

    /**
     * data : {"used_area":["北京","上海"],"hot_area":["北京"],"area":[{"title":"B","area":["北京"]},{"title":"S","area":["上海"]}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> used_area;
        private List<String> hot_area;
        private List<AreaBean> area;

        public List<String> getUsed_area() {
            return used_area;
        }

        public void setUsed_area(List<String> used_area) {
            this.used_area = used_area;
        }

        public List<String> getHot_area() {
            return hot_area;
        }

        public void setHot_area(List<String> hot_area) {
            this.hot_area = hot_area;
        }

        public List<AreaBean> getArea() {
            return area;
        }

        public void setArea(List<AreaBean> area) {
            this.area = area;
        }

        public static class AreaBean  {
            /**
             * title : B
             * area : ["北京"]
             */

            private String title;
            private List<String> area;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<String> getArea() {
                return area;
            }

            public void setArea(List<String> area) {
                this.area = area;
            }

        }
    }
}
