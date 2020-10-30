package com.yiweiyi.www.bean.login;

import com.yiweiyi.www.base.BaseBean;

/**
 * @Author: zsh
 * 2020/9/29
 * desc:登陆对象
 */
public class SigninBean extends BaseBean {

    /**
     * data : {"id":243,"is_shop":2,"nickname":"15832573308","avatar":"/upload/20200901/1598946678659425238.png","shop_id":254,"phone":"15832573308"}
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
         * id : 243
         * is_shop : 2
         * nickname : 15832573308
         * avatar : /upload/20200901/1598946678659425238.png
         * shop_id : 254
         * phone : 15832573308
         */

        private int id;
        private int is_shop;
        private String nickname;
        private String avatar;
        private int shop_id;
        private String phone;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIs_shop() {
            return is_shop;
        }

        public void setIs_shop(int is_shop) {
            this.is_shop = is_shop;
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

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
