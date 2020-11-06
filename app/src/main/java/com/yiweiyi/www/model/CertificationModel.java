package com.yiweiyi.www.model;

import java.util.List;

/**
 * Created by gs on 2020/10/30.
 */
public class CertificationModel {

    /**
     * code : 1
     * msg : 成功
     * data : {"img_list":["/upload/20200901/1598956082204008136.jpg","/upload/20200901/1598956082178445494.jpg","/upload/20200901/1598956082360194468.jpg","/upload/20200901/1598956082402538863.jpg"],"img_list_arr":[{"width":1080,"height":1492},{"width":1536,"height":1080},{"width":1528,"height":1080},{"width":2432,"height":1728}]}
     */

    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> img_list;
        private List<ImgListArrBean> img_list_arr;

        public List<String> getImg_list() {
            return img_list;
        }

        public void setImg_list(List<String> img_list) {
            this.img_list = img_list;
        }

        public List<ImgListArrBean> getImg_list_arr() {
            return img_list_arr;
        }

        public void setImg_list_arr(List<ImgListArrBean> img_list_arr) {
            this.img_list_arr = img_list_arr;
        }

        public static class ImgListArrBean {
            /**
             * width : 1080
             * height : 1492
             */

            private float width;
            private float height;

            public float getWidth() {
                return width;
            }

            public void setWidth(float width) {
                this.width = width;
            }

            public float getHeight() {
                return height;
            }

            public void setHeight(float height) {
                this.height = height;
            }
        }
    }
}
