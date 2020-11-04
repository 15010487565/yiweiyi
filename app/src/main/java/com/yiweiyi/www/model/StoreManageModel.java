package com.yiweiyi.www.model;

import java.util.List;

/**
 * Created by gs on 2020/11/3.
 */
public class StoreManageModel {

    /**
     * code : 1
     * msg : 成功
     * data : {"browse_total":1,"browse_today":1,"call_log_total":1,"like":[{"id":663,"nickname":"&nbsp","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/MrjGvvhic3Ep14dUfV9H6faicZMKabib3U07km1MRkh5VUyN5Z0Cv4tWqnYqOfGg5yRGvjpb9CH1uWaLWadwsfmnQ/132"}],"like_num":1,"type_1":"","type_2":"","type_3":"","type_3_arr":[],"rotation_imgs":["/static/img/lbt.jpg"],"info":{"id":262,"user_id":354,"sort":1,"shop_name":"蜗牛","key":"蜗牛蜗牛15010487565","logo":"/upload/20201103/1604389488375285724.png","head":"蜗牛","phone":"15010487565","area":"北京","address":"河北","qr_code":"qrcode/20201103/262.png","share_img":"/upload/20201103/160438949276441796.png","rotation_imgs":"/static/img/lbt.jpg","certificate_imgs":"","product_imgs":"","real_imgs":"","is_vip":0,"profile":"","shop_profile":"<p><span style=\"color: rgb(102, 102, 102); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 12px; background-color: rgb(242, 242, 242);\">15010487565<\/span><\/p>","contact_us":"<p><span style=\"color: rgb(102, 102, 102); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 12px; background-color: rgb(242, 242, 242);\">15010487565<\/span><\/p>","browse":3,"status":1,"create_time":1604389526,"is_like":0}}
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
        /**
         * browse_total : 1
         * browse_today : 1
         * call_log_total : 1
         * like : [{"id":663,"nickname":"&nbsp","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/MrjGvvhic3Ep14dUfV9H6faicZMKabib3U07km1MRkh5VUyN5Z0Cv4tWqnYqOfGg5yRGvjpb9CH1uWaLWadwsfmnQ/132"}]
         * like_num : 1
         * type_1 :
         * type_2 :
         * type_3 :
         * type_3_arr : []
         * rotation_imgs : ["/static/img/lbt.jpg"]
         * info : {"id":262,"user_id":354,"sort":1,"shop_name":"蜗牛","key":"蜗牛蜗牛15010487565","logo":"/upload/20201103/1604389488375285724.png","head":"蜗牛","phone":"15010487565","area":"北京","address":"河北","qr_code":"qrcode/20201103/262.png","share_img":"/upload/20201103/160438949276441796.png","rotation_imgs":"/static/img/lbt.jpg","certificate_imgs":"","product_imgs":"","real_imgs":"","is_vip":0,"profile":"","shop_profile":"<p><span style=\"color: rgb(102, 102, 102); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 12px; background-color: rgb(242, 242, 242);\">15010487565<\/span><\/p>","contact_us":"<p><span style=\"color: rgb(102, 102, 102); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 12px; background-color: rgb(242, 242, 242);\">15010487565<\/span><\/p>","browse":3,"status":1,"create_time":1604389526,"is_like":0}
         */

        private int browse_total;
        private int browse_today;
        private int call_log_total;
        private int like_num;
//        private String type_1;
//        private String type_2;
//        private String type_3;
        private InfoBean info;
        private List<LikeBean> like;
        private List<?> type_3_arr;
        private List<String> rotation_imgs;

        public int getBrowse_total() {
            return browse_total;
        }

        public void setBrowse_total(int browse_total) {
            this.browse_total = browse_total;
        }

        public int getBrowse_today() {
            return browse_today;
        }

        public void setBrowse_today(int browse_today) {
            this.browse_today = browse_today;
        }

        public int getCall_log_total() {
            return call_log_total;
        }

        public void setCall_log_total(int call_log_total) {
            this.call_log_total = call_log_total;
        }

        public int getLike_num() {
            return like_num;
        }

        public void setLike_num(int like_num) {
            this.like_num = like_num;
        }

//        public String getType_1() {
//            return type_1;
//        }
//
//        public void setType_1(String type_1) {
//            this.type_1 = type_1;
//        }
//
//        public String getType_2() {
//            return type_2;
//        }
//
//        public void setType_2(String type_2) {
//            this.type_2 = type_2;
//        }
//
//        public String getType_3() {
//            return type_3;
//        }
//
//        public void setType_3(String type_3) {
//            this.type_3 = type_3;
//        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<LikeBean> getLike() {
            return like;
        }

        public void setLike(List<LikeBean> like) {
            this.like = like;
        }

        public List<?> getType_3_arr() {
            return type_3_arr;
        }

        public void setType_3_arr(List<?> type_3_arr) {
            this.type_3_arr = type_3_arr;
        }

        public List<String> getRotation_imgs() {
            return rotation_imgs;
        }

        public void setRotation_imgs(List<String> rotation_imgs) {
            this.rotation_imgs = rotation_imgs;
        }

        public static class InfoBean {
            /**
             * id : 262
             * user_id : 354
             * sort : 1
             * shop_name : 蜗牛
             * key : 蜗牛蜗牛15010487565
             * logo : /upload/20201103/1604389488375285724.png
             * head : 蜗牛
             * phone : 15010487565
             * area : 北京
             * address : 河北
             * qr_code : qrcode/20201103/262.png
             * share_img : /upload/20201103/160438949276441796.png
             * rotation_imgs : /static/img/lbt.jpg
             * certificate_imgs :
             * product_imgs :
             * real_imgs :
             * is_vip : 0
             * profile :
             * shop_profile : <p><span style="color: rgb(102, 102, 102); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 12px; background-color: rgb(242, 242, 242);">15010487565</span></p>
             * contact_us : <p><span style="color: rgb(102, 102, 102); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 12px; background-color: rgb(242, 242, 242);">15010487565</span></p>
             * browse : 3
             * status : 1
             * create_time : 1604389526
             * is_like : 0
             */

            private int id;
            private int user_id;
            private int sort;
            private String shop_name;
            private String key;
            private String logo;
            private String head;
            private String phone;
            private String area;
            private String address;
            private String qr_code;
            private String share_img;
            private String rotation_imgs;
            private String certificate_imgs;
            private String product_imgs;
            private String real_imgs;
            private int is_vip;
            private String profile;
            private String shop_profile;
            private String contact_us;
            private int browse;
            private int status;
            private int create_time;
            private int is_like;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getHead() {
                return head;
            }

            public void setHead(String head) {
                this.head = head;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getQr_code() {
                return qr_code;
            }

            public void setQr_code(String qr_code) {
                this.qr_code = qr_code;
            }

            public String getShare_img() {
                return share_img;
            }

            public void setShare_img(String share_img) {
                this.share_img = share_img;
            }

            public String getRotation_imgs() {
                return rotation_imgs;
            }

            public void setRotation_imgs(String rotation_imgs) {
                this.rotation_imgs = rotation_imgs;
            }

            public String getCertificate_imgs() {
                return certificate_imgs;
            }

            public void setCertificate_imgs(String certificate_imgs) {
                this.certificate_imgs = certificate_imgs;
            }

            public String getProduct_imgs() {
                return product_imgs;
            }

            public void setProduct_imgs(String product_imgs) {
                this.product_imgs = product_imgs;
            }

            public String getReal_imgs() {
                return real_imgs;
            }

            public void setReal_imgs(String real_imgs) {
                this.real_imgs = real_imgs;
            }

            public int getIs_vip() {
                return is_vip;
            }

            public void setIs_vip(int is_vip) {
                this.is_vip = is_vip;
            }

            public String getProfile() {
                return profile;
            }

            public void setProfile(String profile) {
                this.profile = profile;
            }

            public String getShop_profile() {
                return shop_profile;
            }

            public void setShop_profile(String shop_profile) {
                this.shop_profile = shop_profile;
            }

            public String getContact_us() {
                return contact_us;
            }

            public void setContact_us(String contact_us) {
                this.contact_us = contact_us;
            }

            public int getBrowse() {
                return browse;
            }

            public void setBrowse(int browse) {
                this.browse = browse;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public int getIs_like() {
                return is_like;
            }

            public void setIs_like(int is_like) {
                this.is_like = is_like;
            }
        }

        public static class LikeBean {
            /**
             * id : 663
             * nickname : &nbsp
             * avatar : https://thirdwx.qlogo.cn/mmopen/vi_32/MrjGvvhic3Ep14dUfV9H6faicZMKabib3U07km1MRkh5VUyN5Z0Cv4tWqnYqOfGg5yRGvjpb9CH1uWaLWadwsfmnQ/132
             */

            private int id;
            private String nickname;
            private String avatar;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }
    }
}
