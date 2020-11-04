package com.yiweiyi.www.model;

import java.util.List;

/**
 * Created by gs on 2020/11/3.
 */
public class LikeModel {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"total":3,"today":0,"list":[{"id":652,"user_id":330,"shop_id":72,"create_time":1603718945,"source":"0","nickname":"AA-非凡","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKia5VyC4PYGIltwuCqlaaU4nrurTJz2Y1ICozzicePVT08eW8icX5Tf8zEE9drHVXVaO9xkRR7ia8iajA/132","phone":"13241184111"},{"id":504,"user_id":278,"shop_id":72,"create_time":1600150390,"source":null,"nickname":"爬山虎线缆-北京库（雷","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIlmUXHftk5uOsJickrnEDmBCotbOwt1wFZxNUKDCpGe0rfh4LLqP4ic3KXVODDAwNteRfg5ia9XHPvg/132","phone":"13269882999"},{"id":367,"user_id":32,"shop_id":72,"create_time":1599198139,"source":null,"nickname":"易为易","avatar":"/upload/20201028/1603888568552150873.png","phone":"13811449210"}],"app_num":0,"xcx_num":0}
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
         * total : 3
         * today : 0
         * list : [{"id":652,"user_id":330,"shop_id":72,"create_time":1603718945,"source":"0","nickname":"AA-非凡","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKia5VyC4PYGIltwuCqlaaU4nrurTJz2Y1ICozzicePVT08eW8icX5Tf8zEE9drHVXVaO9xkRR7ia8iajA/132","phone":"13241184111"},{"id":504,"user_id":278,"shop_id":72,"create_time":1600150390,"source":null,"nickname":"爬山虎线缆-北京库（雷","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIlmUXHftk5uOsJickrnEDmBCotbOwt1wFZxNUKDCpGe0rfh4LLqP4ic3KXVODDAwNteRfg5ia9XHPvg/132","phone":"13269882999"},{"id":367,"user_id":32,"shop_id":72,"create_time":1599198139,"source":null,"nickname":"易为易","avatar":"/upload/20201028/1603888568552150873.png","phone":"13811449210"}]
         * app_num : 0
         * xcx_num : 0
         */

        private int total;
        private int today;
        private int app_num;
        private int xcx_num;
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

        public int getApp_num() {
            return app_num;
        }

        public void setApp_num(int app_num) {
            this.app_num = app_num;
        }

        public int getXcx_num() {
            return xcx_num;
        }

        public void setXcx_num(int xcx_num) {
            this.xcx_num = xcx_num;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 652
             * user_id : 330
             * shop_id : 72
             * create_time : 1603718945
             * source : 0
             * nickname : AA-非凡
             * avatar : https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKia5VyC4PYGIltwuCqlaaU4nrurTJz2Y1ICozzicePVT08eW8icX5Tf8zEE9drHVXVaO9xkRR7ia8iajA/132
             * phone : 13241184111
             */

            private int id;
            private int user_id;
            private int shop_id;
            private int create_time;
            private String source;
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

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
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
