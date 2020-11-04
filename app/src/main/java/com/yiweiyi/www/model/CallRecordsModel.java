package com.yiweiyi.www.model;

import java.util.List;

/**
 * Created by gs on 2020/11/3.
 */
public class CallRecordsModel {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"total":4,"today":4,"list":[{"id":6244,"user_id":32,"shop_id":262,"create_time":1604396754,"source":"0","nickname":"易为易","avatar":"/upload/20201028/1603888568552150873.png","phone":"13811449210"},{"id":6213,"user_id":312,"shop_id":262,"create_time":1604391404,"source":"0","nickname":"忆直很安静","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eo9bmT2MRXTqqEUawDT4z2cn4TCkjGuYric4r3LHhW7X3sVugVeeoXv86wKC33PSCDviaSlcQk0MtcA/132","phone":"18301585146"},{"id":6212,"user_id":312,"shop_id":262,"create_time":1604390879,"source":"0","nickname":"忆直很安静","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eo9bmT2MRXTqqEUawDT4z2cn4TCkjGuYric4r3LHhW7X3sVugVeeoXv86wKC33PSCDviaSlcQk0MtcA/132","phone":"18301585146"},{"id":6211,"user_id":312,"shop_id":262,"create_time":1604390861,"source":"0","nickname":"忆直很安静","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eo9bmT2MRXTqqEUawDT4z2cn4TCkjGuYric4r3LHhW7X3sVugVeeoXv86wKC33PSCDviaSlcQk0MtcA/132","phone":"18301585146"}],"app_num":0,"xcx_num":0}
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
         * total : 4
         * today : 4
         * list : [{"id":6244,"user_id":32,"shop_id":262,"create_time":1604396754,"source":"0","nickname":"易为易","avatar":"/upload/20201028/1603888568552150873.png","phone":"13811449210"},{"id":6213,"user_id":312,"shop_id":262,"create_time":1604391404,"source":"0","nickname":"忆直很安静","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eo9bmT2MRXTqqEUawDT4z2cn4TCkjGuYric4r3LHhW7X3sVugVeeoXv86wKC33PSCDviaSlcQk0MtcA/132","phone":"18301585146"},{"id":6212,"user_id":312,"shop_id":262,"create_time":1604390879,"source":"0","nickname":"忆直很安静","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eo9bmT2MRXTqqEUawDT4z2cn4TCkjGuYric4r3LHhW7X3sVugVeeoXv86wKC33PSCDviaSlcQk0MtcA/132","phone":"18301585146"},{"id":6211,"user_id":312,"shop_id":262,"create_time":1604390861,"source":"0","nickname":"忆直很安静","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eo9bmT2MRXTqqEUawDT4z2cn4TCkjGuYric4r3LHhW7X3sVugVeeoXv86wKC33PSCDviaSlcQk0MtcA/132","phone":"18301585146"}]
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
             * id : 6244
             * user_id : 32
             * shop_id : 262
             * create_time : 1604396754
             * source : 0
             * nickname : 易为易
             * avatar : /upload/20201028/1603888568552150873.png
             * phone : 13811449210
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
