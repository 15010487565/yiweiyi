package com.yiweiyi.www.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by gs on 2020/10/30.
 */
public class ProdcataModel {
    /**
     * code : 1
     * msg : 成功
     * data : [{"id":36,"type":2,"user_id":null,"shop_id":72,"album_name":"库房实景","imgs":"/upload/20200926/1601107742335658679.jpg,/upload/20200926/1601107742334886312.jpg,/upload/20200926/1601107742418280916.jpg,/upload/20200926/1601107742430627423.jpg,/upload/20200926/1601107742463735804.jpg,/upload/20200926/1601107742459994957.jpg,/upload/20200926/1601107742308388386.jpg,/upload/20200926/1601107742479092439.jpg","sort":1,"create_time":1601107756}]
     */

    private String code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * id : 36
         * type : 2
         * user_id : null
         * shop_id : 72
         * album_name : 库房实景
         * imgs : /upload/20200926/1601107742335658679.jpg,/upload/20200926/1601107742334886312.jpg,/upload/20200926/1601107742418280916.jpg,/upload/20200926/1601107742430627423.jpg,/upload/20200926/1601107742463735804.jpg,/upload/20200926/1601107742459994957.jpg,/upload/20200926/1601107742308388386.jpg,/upload/20200926/1601107742479092439.jpg
         * sort : 1
         * create_time : 1601107756
         */

        private int id;
        private int type;
        private Object user_id;
        private int shop_id;
        private String album_name;
        private String imgs;
        private int sort;
        private int create_time;

        protected DataBean(Parcel in) {
            id = in.readInt();
            imgs = in.readString();

        }

        public static final Creator<ProdcataModel.DataBean> CREATOR = new Creator<ProdcataModel.DataBean>() {
            @Override
            public ProdcataModel.DataBean createFromParcel(Parcel in) {
                return new ProdcataModel.DataBean(in);
            }

            @Override
            public ProdcataModel.DataBean[] newArray(int size) {
                return new ProdcataModel.DataBean[size];
            }
        };

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getUser_id() {
            return user_id;
        }

        public void setUser_id(Object user_id) {
            this.user_id = user_id;
        }

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public String getAlbum_name() {
            return album_name;
        }

        public void setAlbum_name(String album_name) {
            this.album_name = album_name;
        }

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(id);
            parcel.writeString(imgs);
        }
    }

}
