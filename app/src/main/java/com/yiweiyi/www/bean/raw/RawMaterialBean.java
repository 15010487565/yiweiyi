package com.yiweiyi.www.bean.raw;

import android.os.Parcel;
import android.os.Parcelable;

import com.yiweiyi.www.base.BaseBean;

import java.util.List;

/**
 * @author: zsh
 * 2020/10/11 0011
 * @Description:原料行情
 */
public class RawMaterialBean extends BaseBean {

    /**
     * data : {"type":"1","class_list":["1#铜","A00铝"],"year_list":["2020","2019","2018","2017"],"list":[{"date":1600272000,"price":51560,"wave":-480},{"date":1600185600,"price":52040,"wave":-220},{"date":1600099200,"price":52260,"wave":-270},{"date":1600012800,"price":52530,"wave":910},{"date":1599753600,"price":51620,"wave":-590},{"date":1599667200,"price":52210,"wave":40},{"date":1599580800,"price":52170,"wave":-430},{"date":1599494400,"price":52600,"wave":-90},{"date":1599408000,"price":52690,"wave":1140},{"date":1599148800,"price":51550,"wave":-630},{"date":1599062400,"price":52180,"wave":210},{"date":1598976000,"price":51970,"wave":-430},{"date":1598889600,"price":14690,"wave":-70},{"date":1598803200,"price":52030,"wave":210},{"date":1598544000,"price":51820,"wave":330},{"date":1598457600,"price":51490,"wave":20},{"date":1598371200,"price":51470,"wave":20},{"date":1598284800,"price":51450,"wave":50},{"date":1598198400,"price":51400,"wave":-730},{"date":1597939200,"price":52130,"wave":-130},{"date":1597852800,"price":52260,"wave":-110},{"date":1597766400,"price":52370,"wave":1180},{"date":1597680000,"price":51190,"wave":730},{"date":1597593600,"price":50460,"wave":320},{"date":1597334400,"price":50140,"wave":-440},{"date":1597248000,"price":50580,"wave":470},{"date":1597161600,"price":50110,"wave":-780},{"date":1597075200,"price":50890,"wave":690},{"date":1596988800,"price":50200,"wave":-1360},{"date":1596729600,"price":51560,"wave":70},{"date":1596643200,"price":51490,"wave":60},{"date":1596556800,"price":51430,"wave":-370},{"date":1596470400,"price":51800,"wave":830},{"date":1596384000,"price":50970,"wave":-980},{"date":1596124800,"price":51950,"wave":40},{"date":1596038400,"price":51910,"wave":10},{"date":1595952000,"price":51900,"wave":-130},{"date":1595865600,"price":52030,"wave":250},{"date":1595779200,"price":51780,"wave":-270},{"date":1595520000,"price":52050,"wave":40},{"date":1595433600,"price":52010,"wave":-590},{"date":1595347200,"price":52600,"wave":660},{"date":1595260800,"price":51940,"wave":570},{"date":1595174400,"price":51370,"wave":-390},{"date":1594915200,"price":51760,"wave":460},{"date":1594828800,"price":51300,"wave":-1250},{"date":1594742400,"price":52550,"wave":350},{"date":1594656000,"price":52200,"wave":-1200},{"date":1594569600,"price":53400,"wave":2680},{"date":1594310400,"price":50720,"wave":40},{"date":1594224000,"price":50680,"wave":750},{"date":1594137600,"price":49930,"wave":210},{"date":1594051200,"price":49720,"wave":570},{"date":1593964800,"price":49150,"wave":-330},{"date":1593705600,"price":49480,"wave":-110},{"date":1593619200,"price":49590,"wave":130},{"date":1593532800,"price":49460,"wave":440},{"date":1593446400,"price":49020,"wave":250},{"date":1593360000,"price":48770,"wave":550},{"date":1592928000,"price":48220,"wave":240},{"date":1592841600,"price":47980,"wave":20},{"date":1592755200,"price":47960,"wave":430},{"date":1592496000,"price":47530,"wave":380},{"date":1592409600,"price":47150,"wave":170},{"date":1592323200,"price":46980,"wave":-40},{"date":1592236800,"price":47020,"wave":60},{"date":1592150400,"price":46960,"wave":50},{"date":1591891200,"price":46910,"wave":-550},{"date":1591804800,"price":47460,"wave":510},{"date":1591718400,"price":46950,"wave":550},{"date":1591632000,"price":46400,"wave":610},{"date":1591545600,"price":45790,"wave":750},{"date":1591286400,"price":45040,"wave":100},{"date":1591200000,"price":44940,"wave":-180},{"date":1591113600,"price":45120,"wave":130},{"date":1591027200,"price":44990,"wave":230},{"date":1590940800,"price":44760,"wave":530},{"date":1590681600,"price":44230,"wave":80},{"date":1590595200,"price":44150,"wave":-70},{"date":1590508800,"price":44220,"wave":130},{"date":1590422400,"price":44090,"wave":260},{"date":1590336000,"price":43830,"wave":70},{"date":1590076800,"price":43760,"wave":-880},{"date":1589990400,"price":44640,"wave":560},{"date":1589904000,"price":44080,"wave":-50},{"date":1589817600,"price":44130,"wave":780},{"date":1589731200,"price":43350,"wave":-60},{"date":1589472000,"price":43410,"wave":220},{"date":1589385600,"price":43190,"wave":50},{"date":1589299200,"price":43140,"wave":-570},{"date":1589212800,"price":43710,"wave":-530},{"date":1589126400,"price":44240,"wave":550},{"date":1588867200,"price":43690,"wave":620},{"date":1588780800,"price":43070,"wave":140},{"date":1588694400,"price":42930,"wave":-140},{"date":1588176000,"price":43070,"wave":180},{"date":1588089600,"price":42890,"wave":380},{"date":1588003200,"price":42510,"wave":-540},{"date":1587916800,"price":43050,"wave":890},{"date":1587657600,"price":42160,"wave":100}]}
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
         * type : 1
         * class_list : ["1#铜","A00铝"]
         * year_list : ["2020","2019","2018","2017"]
         * list : [{"date":1600272000,"price":51560,"wave":-480},{"date":1600185600,"price":52040,"wave":-220},{"date":1600099200,"price":52260,"wave":-270},{"date":1600012800,"price":52530,"wave":910},{"date":1599753600,"price":51620,"wave":-590},{"date":1599667200,"price":52210,"wave":40},{"date":1599580800,"price":52170,"wave":-430},{"date":1599494400,"price":52600,"wave":-90},{"date":1599408000,"price":52690,"wave":1140},{"date":1599148800,"price":51550,"wave":-630},{"date":1599062400,"price":52180,"wave":210},{"date":1598976000,"price":51970,"wave":-430},{"date":1598889600,"price":14690,"wave":-70},{"date":1598803200,"price":52030,"wave":210},{"date":1598544000,"price":51820,"wave":330},{"date":1598457600,"price":51490,"wave":20},{"date":1598371200,"price":51470,"wave":20},{"date":1598284800,"price":51450,"wave":50},{"date":1598198400,"price":51400,"wave":-730},{"date":1597939200,"price":52130,"wave":-130},{"date":1597852800,"price":52260,"wave":-110},{"date":1597766400,"price":52370,"wave":1180},{"date":1597680000,"price":51190,"wave":730},{"date":1597593600,"price":50460,"wave":320},{"date":1597334400,"price":50140,"wave":-440},{"date":1597248000,"price":50580,"wave":470},{"date":1597161600,"price":50110,"wave":-780},{"date":1597075200,"price":50890,"wave":690},{"date":1596988800,"price":50200,"wave":-1360},{"date":1596729600,"price":51560,"wave":70},{"date":1596643200,"price":51490,"wave":60},{"date":1596556800,"price":51430,"wave":-370},{"date":1596470400,"price":51800,"wave":830},{"date":1596384000,"price":50970,"wave":-980},{"date":1596124800,"price":51950,"wave":40},{"date":1596038400,"price":51910,"wave":10},{"date":1595952000,"price":51900,"wave":-130},{"date":1595865600,"price":52030,"wave":250},{"date":1595779200,"price":51780,"wave":-270},{"date":1595520000,"price":52050,"wave":40},{"date":1595433600,"price":52010,"wave":-590},{"date":1595347200,"price":52600,"wave":660},{"date":1595260800,"price":51940,"wave":570},{"date":1595174400,"price":51370,"wave":-390},{"date":1594915200,"price":51760,"wave":460},{"date":1594828800,"price":51300,"wave":-1250},{"date":1594742400,"price":52550,"wave":350},{"date":1594656000,"price":52200,"wave":-1200},{"date":1594569600,"price":53400,"wave":2680},{"date":1594310400,"price":50720,"wave":40},{"date":1594224000,"price":50680,"wave":750},{"date":1594137600,"price":49930,"wave":210},{"date":1594051200,"price":49720,"wave":570},{"date":1593964800,"price":49150,"wave":-330},{"date":1593705600,"price":49480,"wave":-110},{"date":1593619200,"price":49590,"wave":130},{"date":1593532800,"price":49460,"wave":440},{"date":1593446400,"price":49020,"wave":250},{"date":1593360000,"price":48770,"wave":550},{"date":1592928000,"price":48220,"wave":240},{"date":1592841600,"price":47980,"wave":20},{"date":1592755200,"price":47960,"wave":430},{"date":1592496000,"price":47530,"wave":380},{"date":1592409600,"price":47150,"wave":170},{"date":1592323200,"price":46980,"wave":-40},{"date":1592236800,"price":47020,"wave":60},{"date":1592150400,"price":46960,"wave":50},{"date":1591891200,"price":46910,"wave":-550},{"date":1591804800,"price":47460,"wave":510},{"date":1591718400,"price":46950,"wave":550},{"date":1591632000,"price":46400,"wave":610},{"date":1591545600,"price":45790,"wave":750},{"date":1591286400,"price":45040,"wave":100},{"date":1591200000,"price":44940,"wave":-180},{"date":1591113600,"price":45120,"wave":130},{"date":1591027200,"price":44990,"wave":230},{"date":1590940800,"price":44760,"wave":530},{"date":1590681600,"price":44230,"wave":80},{"date":1590595200,"price":44150,"wave":-70},{"date":1590508800,"price":44220,"wave":130},{"date":1590422400,"price":44090,"wave":260},{"date":1590336000,"price":43830,"wave":70},{"date":1590076800,"price":43760,"wave":-880},{"date":1589990400,"price":44640,"wave":560},{"date":1589904000,"price":44080,"wave":-50},{"date":1589817600,"price":44130,"wave":780},{"date":1589731200,"price":43350,"wave":-60},{"date":1589472000,"price":43410,"wave":220},{"date":1589385600,"price":43190,"wave":50},{"date":1589299200,"price":43140,"wave":-570},{"date":1589212800,"price":43710,"wave":-530},{"date":1589126400,"price":44240,"wave":550},{"date":1588867200,"price":43690,"wave":620},{"date":1588780800,"price":43070,"wave":140},{"date":1588694400,"price":42930,"wave":-140},{"date":1588176000,"price":43070,"wave":180},{"date":1588089600,"price":42890,"wave":380},{"date":1588003200,"price":42510,"wave":-540},{"date":1587916800,"price":43050,"wave":890},{"date":1587657600,"price":42160,"wave":100}]
         */

        private String type;
        private List<String> class_list;
        private List<String> year_list;
        private List<ListBean> list;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<String> getClass_list() {
            return class_list;
        }

        public void setClass_list(List<String> class_list) {
            this.class_list = class_list;
        }

        public List<String> getYear_list() {
            return year_list;
        }

        public void setYear_list(List<String> year_list) {
            this.year_list = year_list;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Parcelable {
            /**
             * date : 1600272000
             * price : 51560
             * wave : -480
             */

            private int date;
            private int price;
            private int wave;

            protected ListBean(Parcel in) {
                date = in.readInt();
                price = in.readInt();
                wave = in.readInt();
            }

            public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
                @Override
                public ListBean createFromParcel(Parcel in) {
                    return new ListBean(in);
                }

                @Override
                public ListBean[] newArray(int size) {
                    return new ListBean[size];
                }
            };

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getWave() {
                return wave;
            }

            public void setWave(int wave) {
                this.wave = wave;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(date);
                dest.writeInt(price);
                dest.writeInt(wave);
            }
        }
    }
}
