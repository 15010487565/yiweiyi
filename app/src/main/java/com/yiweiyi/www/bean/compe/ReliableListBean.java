package com.yiweiyi.www.bean.compe;

import com.yiweiyi.www.base.BaseBean;

import java.util.List;

/**
 * @author: zsh
 * 2020/10/11 0011
 * @Description: 靠谱列表对象
 */
public class ReliableListBean extends BaseBean {

    /**
     * data : {"total":2,"today":0,"list":[{"id":393,"user_id":244,"shop_id":13,"create_time":1599622857,"source":null,"nickname":"易为易","avatar":"/upload/20200912/1599888164599257475.png","phone":"15148248108"},{"id":324,"user_id":32,"shop_id":13,"create_time":1598872848,"source":null,"nickname":"易为易","avatar":"/upload/20200829/1598659002483488912.png","phone":"13811449210"}]}
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
         * total : 2
         * today : 0
         * list : [{"id":393,"user_id":244,"shop_id":13,"create_time":1599622857,"source":null,"nickname":"易为易","avatar":"/upload/20200912/1599888164599257475.png","phone":"15148248108"},{"id":324,"user_id":32,"shop_id":13,"create_time":1598872848,"source":null,"nickname":"易为易","avatar":"/upload/20200829/1598659002483488912.png","phone":"13811449210"}]
         */

        private int total;
        private int today;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getToday() {
            return today;
        }

        public void setToday(int today) {
            this.today = today;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 393
             * user_id : 244
             * shop_id : 13
             * create_time : 1599622857
             * source : null
             * nickname : 易为易
             * avatar : /upload/20200912/1599888164599257475.png
             * phone : 15148248108
             */

            private int id;
            private int user_id;
            private int shop_id;
            private int create_time;
            private Object source;
            private String nickname;
            private String avatar;
            private String phone;

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

            public int getShop_id() {
                return shop_id;
            }

            public void setShop_id(int shop_id) {
                this.shop_id = shop_id;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public Object getSource() {
                return source;
            }

            public void setSource(Object source) {
                this.source = source;
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
        }
    }
}
