package com.yiweiyi.www.bean.personal;

import com.yiweiyi.www.base.BaseBean;

/**
 * @Author: zsh
 * 2020/10/11
 * desc:用户基本信息接口对象
 */
public class UserInfoBean extends BaseBean {

    /**
     * data : {"id":295,"nickname":"15710028432","avatar":"","phone":"15710028432","call_num":0,"area":"北京","source":"ios","unionid":"oy8cnwA2ojrq3G9yckHFrcVkxxOI","android_openid":null,"ios_openid":"oBD1f5TIjPAjX0HoVKCUymzkPVXI","openid":null,"remarks":null,"is_shop":0,"shop_id":0,"status":1,"black_time":null,"create_time":1601361419,"update_time":"","use_num":0,"use_time":""}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 295
         * nickname : 15710028432
         * avatar :
         * phone : 15710028432
         * call_num : 0
         * area : 北京
         * source : ios
         * unionid : oy8cnwA2ojrq3G9yckHFrcVkxxOI
         * android_openid : null
         * ios_openid : oBD1f5TIjPAjX0HoVKCUymzkPVXI
         * openid : null
         * remarks : null
         * is_shop : 0
         * shop_id : 0
         * status : 1
         * black_time : null
         * create_time : 1601361419
         * update_time :
         * use_num : 0
         * use_time :
         */

        private int id;
        private String nickname;
        private String avatar;
        private String phone;
        private int call_num;
        private String area;
        private String source;
        private String unionid;
        private Object android_openid;
        private String ios_openid;
        private Object openid;
        private Object remarks;
        private int is_shop;
        private int shop_id;
        private int status;
        private Object black_time;
        private int create_time;
        private String update_time;
        private int use_num;
        private String use_time;

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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getCall_num() {
            return call_num;
        }

        public void setCall_num(int call_num) {
            this.call_num = call_num;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getUnionid() {
            return unionid;
        }

        public void setUnionid(String unionid) {
            this.unionid = unionid;
        }

        public Object getAndroid_openid() {
            return android_openid;
        }

        public void setAndroid_openid(Object android_openid) {
            this.android_openid = android_openid;
        }

        public String getIos_openid() {
            return ios_openid;
        }

        public void setIos_openid(String ios_openid) {
            this.ios_openid = ios_openid;
        }

        public Object getOpenid() {
            return openid;
        }

        public void setOpenid(Object openid) {
            this.openid = openid;
        }

        public Object getRemarks() {
            return remarks;
        }

        public void setRemarks(Object remarks) {
            this.remarks = remarks;
        }

        public int getIs_shop() {
            return is_shop;
        }

        public void setIs_shop(int is_shop) {
            this.is_shop = is_shop;
        }

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getBlack_time() {
            return black_time;
        }

        public void setBlack_time(Object black_time) {
            this.black_time = black_time;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public int getUse_num() {
            return use_num;
        }

        public void setUse_num(int use_num) {
            this.use_num = use_num;
        }

        public String getUse_time() {
            return use_time;
        }

        public void setUse_time(String use_time) {
            this.use_time = use_time;
        }
    }
}
