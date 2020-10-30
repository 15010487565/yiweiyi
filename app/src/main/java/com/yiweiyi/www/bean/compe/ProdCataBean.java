package com.yiweiyi.www.bean.compe;

import com.yiweiyi.www.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zsh
 * 2020/10/11 0011
 * @Description:
 */
public class ProdCataBean extends BaseBean {

    /**
     * data : {"class_list":["低压阻燃","低烟无卤"],"img_list":["/upload/20200828/1598576162473716227.jpeg","/upload/20200828/1598576164736588502.jpeg","/upload/20200828/1598576167230582772.jpeg","/upload/20200828/1598576169442842417.jpeg","/upload/20200828/1598576170988834485.jpeg","/upload/20200828/1598576172838144608.jpeg","/upload/20200828/1598576174988553403.jpeg","/upload/20200828/1598576176747051532.jpeg","/upload/20200828/1598576178533271660.jpeg","/upload/20200828/1598576181626231991.jpeg","/upload/20200828/1598576184703273275.jpeg","/upload/20200828/1598576186503992460.jpeg","/upload/20200828/159857619889450235.jpeg","/upload/20200828/1598576205584496102.jpg","/upload/20200828/1598576208501927369.jpg"],"img_list_arr":[{"width":1080,"height":1440},{"width":1080,"height":1440},{"width":1080,"height":1440},{"width":1080,"height":1440},{"width":1080,"height":1440},{"width":1080,"height":1440},{"width":1080,"height":1440},{"width":1080,"height":1440},{"width":1080,"height":1440},{"width":1080,"height":1440},{"width":1065,"height":1065},{"width":1065,"height":1065},{"width":1080,"height":1440},{"width":1080,"height":1080},{"width":1080,"height":1080}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> class_list;
        private ArrayList<String> img_list;
        private List<ImgListArrBean> img_list_arr;

        public List<String> getClass_list() {
            return class_list;
        }

        public void setClass_list(List<String> class_list) {
            this.class_list = class_list;
        }

        public ArrayList<String> getImg_list() {
            return img_list;
        }

        public void setImg_list(ArrayList<String> img_list) {
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
             * height : 1440
             */

            private int width;
            private int height;

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }
        }
    }
}
