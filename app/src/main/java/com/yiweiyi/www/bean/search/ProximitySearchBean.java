package com.yiweiyi.www.bean.search;

import com.yiweiyi.www.base.BaseBean;

import java.util.List;

/**
 * @Author: zsh
 * 2020/10/8
 * desc:返回接近关键词Bean
 */
public class ProximitySearchBean extends BaseBean {

    /**
     * data : {"list":[{"id":75,"key_name":"1×150","voltage":null,"level":4,"pid":66,"p_name":"单芯YJLV"},{"id":111,"key_name":"1×150","voltage":null,"level":4,"pid":101,"p_name":null},{"id":162,"key_name":"8.7/15KV","voltage":null,"level":2,"pid":11,"p_name":""},{"id":170,"key_name":"8.7/15KV高压电缆生产厂家","voltage":null,"level":3,"pid":131,"p_name":""},{"id":352,"key_name":"1×150","voltage":null,"level":4,"pid":43,"p_name":"单芯YJV"}],"search_param":"15"}
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
         * list : [{"id":75,"key_name":"1×150","voltage":null,"level":4,"pid":66,"p_name":"单芯YJLV"},{"id":111,"key_name":"1×150","voltage":null,"level":4,"pid":101,"p_name":null},{"id":162,"key_name":"8.7/15KV","voltage":null,"level":2,"pid":11,"p_name":""},{"id":170,"key_name":"8.7/15KV高压电缆生产厂家","voltage":null,"level":3,"pid":131,"p_name":""},{"id":352,"key_name":"1×150","voltage":null,"level":4,"pid":43,"p_name":"单芯YJV"}]
         * search_param : 15
         */

        private String search_param;
        private List<ListBean> list;

        public String getSearch_param() {
            return search_param;
        }

        public void setSearch_param(String search_param) {
            this.search_param = search_param;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 75
             * key_name : 1×150
             * voltage : null
             * level : 4
             * pid : 66
             * p_name : 单芯YJLV
             */

            private int id;
            private String key_name;
            private Object voltage;
            private int level;
            private int pid;
            private String p_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getKey_name() {
                return key_name;
            }

            public void setKey_name(String key_name) {
                this.key_name = key_name;
            }

            public Object getVoltage() {
                return voltage;
            }

            public void setVoltage(Object voltage) {
                this.voltage = voltage;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public String getP_name() {
                return p_name;
            }

            public void setP_name(String p_name) {
                this.p_name = p_name;
            }
        }
    }
}
