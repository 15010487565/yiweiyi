package com.yiweiyi.www.bean.search;

import com.yiweiyi.www.base.BaseBean;

import java.util.List;

/**
 * @Author: zsh
 * 2020/9/30
 * desc:搜索历史
 */
public class SearchRecordsBean extends BaseBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 4653
         * user_id : 354
         * content : 低压铜缆生产厂家
         * create_time : 1604572695
         */

        private int id;
        private int user_id;
        private String content;
        private int create_time;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }
    }
}
