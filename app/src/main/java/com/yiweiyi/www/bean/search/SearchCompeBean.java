package com.yiweiyi.www.bean.search;

import android.os.Parcel;
import android.os.Parcelable;

import com.yiweiyi.www.base.BaseBean;

import java.util.List;

/**
 * @Author: zsh
 * 2020/10/8
 * desc:
 */
public class SearchCompeBean extends BaseBean {

    /**
     * data : {"area_list":["石家庄"],"shop_list":[{"id":108,"shop_name":"佰汇电缆有限公司","head":"李保来","phone":["13931998013","0311-86962776"],"logo":"/upload/20200902/1599016738717094642.jpg","area":"石家庄","is_vip":0,"k_profile":null,"a_profile":"佰汇电缆有限公司","browse":0,"profile":"佰汇电缆有限公司"},{"id":115,"shop_name":"宏鑫电缆有限公司","head":"杨朝","phone":["13833118871","13933195151","0311-87816655","0311-87816622"],"logo":"/upload/20200902/1599018080580123268.jpg","area":"石家庄","is_vip":0,"k_profile":null,"a_profile":"宏鑫电缆有限公司","browse":0,"profile":"宏鑫电缆有限公司"},{"id":138,"shop_name":"津东电缆有限公司","head":"赵翔","phone":["13903112937","13903112935"],"logo":"/upload/20200902/1599035854362991296.jpg","area":"石家庄","is_vip":0,"k_profile":null,"a_profile":"津东电缆有限公司","browse":0,"profile":"津东电缆有限公司"},{"id":104,"shop_name":"富华线缆有限公司","head":"潘彦君","phone":["13633315308","18633000091","0311-66535722","0311-86996139"],"logo":"/upload/20200901/1598956800291941988.jpg","area":"石家庄","is_vip":0,"k_profile":null,"a_profile":"富华线缆有限公司","browse":0,"profile":"富华线缆有限公司"},{"id":168,"shop_name":"天龙伟业线缆有限公司","head":"张永波  翟茂佳","phone":["15230863999","0311-86037621","0311-86037376","0311-86030202"],"logo":"/upload/20200902/1599041167205379652.jpg","area":"石家庄","is_vip":0,"k_profile":null,"a_profile":"天龙伟业线缆有限公司","browse":0,"profile":"天龙伟业线缆有限公司"},{"id":107,"shop_name":"恒瑞电缆有限公司","head":"李恒山","phone":["13503330318","13933153908","0311-86983056","010-86111885"],"logo":"/upload/20200902/1599016589130098560.jpg","area":"石家庄","is_vip":0,"k_profile":null,"a_profile":"恒瑞电缆有限公司","browse":0,"profile":"恒瑞电缆有限公司"},{"id":225,"shop_name":"中盛线缆有限公司","head":"杨文","phone":["13739777000","0311-66699995","0311-87825584"],"logo":"/upload/20200903/1599102023862041622.jpg","area":"石家庄","is_vip":0,"k_profile":null,"a_profile":"中盛线缆有限公司","browse":0,"profile":"中盛线缆有限公司"},{"id":110,"shop_name":"弘泰线缆有限公司","head":"曹志栋","phone":["18630131068","13483430175","0311-86079385","0311-86989092"],"logo":"/upload/20200902/1599017117116692952.jpg","area":"石家庄","is_vip":0,"k_profile":null,"a_profile":"弘泰线缆有限公司","browse":0,"profile":"弘泰线缆有限公司"},{"id":211,"shop_name":"烨盛线缆有限公司","head":"李华通  李大征","phone":["13623311170","13623311155","0311-86968932","0311-86981695"],"logo":"/upload/20200903/1599100190503310309.jpg","area":"石家庄","is_vip":0,"k_profile":null,"a_profile":"烨盛线缆有限公司","browse":0,"profile":"烨盛线缆有限公司"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
//        private List<String> area_list;
        private Object area_list;
        private List<ShopListBean> shop_list;

        public Object getArea_list() {
            return area_list;
        }

        public void setArea_list(Object area_list) {
            this.area_list = area_list;
        }
//        public List<String> getArea_list() {
//            return area_list;
//        }
//
//        public void setArea_list(List<String> area_list) {
//            this.area_list = area_list;
//        }

        public List<ShopListBean> getShop_list() {
            return shop_list;
        }

        public void setShop_list(List<ShopListBean> shop_list) {
            this.shop_list = shop_list;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {

        }

        public static class ShopListBean {
            /**
             * id : 108
             * shop_name : 佰汇电缆有限公司
             * head : 李保来
             * phone : ["13931998013","0311-86962776"]
             * logo : /upload/20200902/1599016738717094642.jpg
             * area : 石家庄
             * is_vip : 0
             * k_profile : null
             * a_profile : 佰汇电缆有限公司
             * browse : 0
             * profile : 佰汇电缆有限公司
             */

            private int id;
            private String shop_name;
            private String head;
            private String logo;
            private String area;
            private int is_vip;
            private Object k_profile;
            private String a_profile;
            private int browse;
            private String profile;
            private List<String> phone;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }

            public String getHead() {
                return head;
            }

            public void setHead(String head) {
                this.head = head;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public int getIs_vip() {
                return is_vip;
            }

            public void setIs_vip(int is_vip) {
                this.is_vip = is_vip;
            }

            public Object getK_profile() {
                return k_profile;
            }

            public void setK_profile(Object k_profile) {
                this.k_profile = k_profile;
            }

            public String getA_profile() {
                return a_profile;
            }

            public void setA_profile(String a_profile) {
                this.a_profile = a_profile;
            }

            public int getBrowse() {
                return browse;
            }

            public void setBrowse(int browse) {
                this.browse = browse;
            }

            public String getProfile() {
                return profile;
            }

            public void setProfile(String profile) {
                this.profile = profile;
            }

            public List<String> getPhone() {
                return phone;
            }

            public void setPhone(List<String> phone) {
                this.phone = phone;
            }
        }
    }
}
