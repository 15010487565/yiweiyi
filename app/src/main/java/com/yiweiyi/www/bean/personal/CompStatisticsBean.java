package com.yiweiyi.www.bean.personal;

import com.yiweiyi.www.base.BaseBean;

/**
 * @author: zsh
 * 2020/10/13 0013
 * @Description:商家管理页统计数据对象
 */
public class CompStatisticsBean extends BaseBean {

    /**
     * data : {"browse_total":42,"call_log_total":19,"like_num":1}
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
         * browse_total : 42
         * call_log_total : 19
         * like_num : 1
         */

        private int browse_total;
        private int call_log_total;
        private int like_num;

        public int getBrowse_total() {
            return browse_total;
        }

        public void setBrowse_total(int browse_total) {
            this.browse_total = browse_total;
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
    }
}
