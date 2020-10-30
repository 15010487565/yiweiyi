package com.yiweiyi.www.bean.main;

import android.os.Parcel;
import android.os.Parcelable;

import com.yiweiyi.www.base.BaseBean;

import java.util.List;

/**
 * @Author: zsh
 * 2020/9/25
 * desc:首页分类
 */
public class HomeCategoryBean extends BaseBean {

    /**
     * code : 1
     * msg : 获取成功
     * data : [{"id":1,"name":"常用","list":[{"id":11,"name":"低压铜芯电缆","key_name":"低压铜芯电缆"},{"id":12,"name":"低压铝芯电缆","key_name":"低压铝芯电缆"},{"id":14,"name":"矿物质防火电缆","key_name":"矿物质防火电缆"},{"id":13,"name":"高压电缆","key_name":"高压电缆"},{"id":15,"name":"低烟无卤电缆","key_name":"低烟无卤电缆"},{"id":16,"name":"耐火电缆","key_name":"耐火电缆"},{"id":17,"name":"控制电缆","key_name":"控制电缆"},{"id":18,"name":"屏蔽电缆","key_name":"屏蔽电缆"},{"id":19,"name":"软电缆","key_name":"软电缆"},{"id":20,"name":"橡套电缆","key_name":"橡套电缆"},{"id":21,"name":"计算机电缆","key_name":"计算机电缆"},{"id":22,"name":"光伏线","key_name":"光伏线"},{"id":23,"name":"塑铜线","key_name":"塑铜线"},{"id":24,"name":"铝合金电缆","key_name":"铝合金电缆"},{"id":25,"name":"架空线","key_name":"架空线"},{"id":26,"name":"通讯电缆","key_name":"通讯电缆"},{"id":27,"name":"光纤光缆","key_name":"光纤光缆"},{"id":28,"name":"扁电缆","key_name":"扁电缆"},{"id":29,"name":"矿用电缆","key_name":"矿用电缆"},{"id":30,"name":"线缆附件","key_name":"线缆附件"},{"id":61,"name":"历史铜价","key_name":"原料行情"}]},{"id":5,"name":"低压铜缆","list":[{"id":201,"name":"YJV","key_name":"低压YJV"},{"id":202,"name":"YJV22","key_name":"低压YJV22"},{"id":49,"name":"全项保检现货","key_name":"全项保检低压铜缆现货"},{"id":48,"name":"厂标现货","key_name":"厂标低压铜缆现货"},{"id":246,"name":"单芯YJV","key_name":"单芯YJV"},{"id":52,"name":"生产厂家","key_name":"低压铜缆生产厂家"}]},{"id":6,"name":"低压铝缆","list":[{"id":204,"name":"YJLV","key_name":"低压YJLV"},{"id":205,"name":"YJLV22","key_name":"低压YJLV22"},{"id":240,"name":"VLV","key_name":"VLV"},{"id":242,"name":"VLV22","key_name":"VLV22"},{"id":241,"name":"单芯低压铝缆","key_name":"单芯低压铝缆"},{"id":53,"name":"全项保检现货","key_name":"全项保检低压铝缆现货"},{"id":8,"name":"厂标现货","key_name":"低压铝缆厂标现货"},{"id":54,"name":"生产厂家","key_name":"低压铝缆生产厂家"}]},{"id":7,"name":"防火电缆","list":[{"id":57,"name":"NG-A（BTLY）","key_name":"NG-A（BTLY）"},{"id":56,"name":"BTTRZ","key_name":"BTTRZ"},{"id":55,"name":"BBTRZ","key_name":"BBTRZ"},{"id":58,"name":"BTTZ","key_name":"BTTZ"},{"id":59,"name":"BTTVZ","key_name":"BTTVZ"},{"id":207,"name":"柔性  生产厂家","key_name":"柔性防火电缆生产厂家"},{"id":208,"name":"刚性  生产厂家","key_name":"刚性防火电缆生产厂家"}]},{"id":31,"name":"高压电缆","list":[{"id":62,"name":"YJV22   8.7/15KV","key_name":"YJV22 8.7/15KV高压电缆"},{"id":209,"name":"YJLV22   8.7/15KV","key_name":"YJLV22 8.7/15KV高压电缆"},{"id":211,"name":"YJV22   26/35KV","key_name":"YJV22 26/35KV高压电缆"},{"id":210,"name":"YJLV22   26/35KV","key_name":"YJLV22 26/35KV高压电缆"},{"id":64,"name":"全项保检现货","key_name":"高压电缆全项保检现货"},{"id":65,"name":"YJLV62","key_name":"YJLV62"},{"id":212,"name":"8.7/15KV  生产厂家","key_name":"8.7/15KV高压电缆生产厂家"},{"id":213,"name":"26/35KV  生产厂家","key_name":"26/35KV高压电缆生产厂家"}]},{"id":32,"name":"低烟无卤","list":[{"id":68,"name":"低烟无卤","key_name":"低烟无卤"},{"id":67,"name":"低烟无卤耐火","key_name":"低烟无卤耐火电缆"},{"id":69,"name":"低烟无卤控制线","key_name":"低烟无卤控制电缆"},{"id":70,"name":"低烟无卤塑铜线","key_name":"低烟无卤塑铜线"},{"id":216,"name":"全项保检现货","key_name":"低烟无卤全项保检现货"},{"id":217,"name":"厂标现货","key_name":"低烟无卤厂标现货"},{"id":214,"name":"生产厂家","key_name":"低烟无卤电缆生产厂家"}]},{"id":33,"name":"耐火电缆","list":[{"id":219,"name":"耐火阻燃电缆","key_name":"耐火阻燃电缆"},{"id":71,"name":"耐火控制线","key_name":"耐火控制电缆"},{"id":73,"name":"全项保检现货","key_name":"耐火电缆全项保检现货"},{"id":218,"name":"厂标现货","key_name":"耐火电缆厂标现货"},{"id":72,"name":"低烟无卤耐火控制线","key_name":"低烟无卤耐火控制线"},{"id":215,"name":"生产厂家","key_name":"耐火电缆生产厂家"}]},{"id":34,"name":"控制电缆","list":[{"id":220,"name":"硬芯控制线","key_name":"硬芯控制线"},{"id":78,"name":"软芯控制线","key_name":"软芯控制线"},{"id":76,"name":"耐火控制线","key_name":"耐火控制线"},{"id":77,"name":"全项保检现货","key_name":"控制电缆全项保检现货"},{"id":74,"name":"低烟无卤","key_name":"低烟无卤控制线"},{"id":75,"name":"低烟无卤耐火","key_name":"低烟无卤耐火控制线"},{"id":79,"name":"橡套控制线","key_name":"橡套控制线"},{"id":222,"name":"生产厂家","key_name":"控制电缆生产厂家"}]},{"id":35,"name":"屏蔽电缆","list":[{"id":81,"name":"KVVP","key_name":"KVVP"},{"id":82,"name":"KVVP22","key_name":"KVVP22"},{"id":85,"name":"KVVP2","key_name":"KVVP2"},{"id":86,"name":"KVVP2-22","key_name":"KVVP2-22"},{"id":84,"name":"KVVRP","key_name":"KVVRP"},{"id":80,"name":"RVSP","key_name":"RVSP"},{"id":87,"name":"NH-KVVP","key_name":"NH-KVVP"},{"id":88,"name":"NH-KVVRP","key_name":"NH-KVVRP"},{"id":83,"name":"全项保检现货","key_name":"屏蔽电缆全项保检现货"},{"id":223,"name":"生产厂家","key_name":"屏蔽电缆生产厂家"}]},{"id":36,"name":"软电缆","list":[{"id":91,"name":"RVVZ/VVR","key_name":"RVVZ/VVR"},{"id":89,"name":"全项保检现货","key_name":"软电缆全项保检现货"},{"id":92,"name":"厂标现货","key_name":"软电缆厂标现货"},{"id":245,"name":"单芯软电缆","key_name":"单芯软电缆"},{"id":224,"name":"生产厂家","key_name":"软电缆生产厂家"}]},{"id":37,"name":"橡套电缆","list":[{"id":225,"name":"YC","key_name":"YC"},{"id":95,"name":"焊把线","key_name":"焊把线"},{"id":93,"name":"国标","key_name":"国标橡套线"},{"id":94,"name":"1.5斤","key_name":"橡套电缆1.5斤"},{"id":97,"name":"铝合金橡套线","key_name":"铝合金橡套线"},{"id":243,"name":"铝合金焊把线","key_name":"铝合金焊把线"},{"id":100,"name":"电葫芦专用线","key_name":"电葫芦专用线"},{"id":99,"name":"升降机电缆","key_name":"升降机电缆"},{"id":98,"name":"橡套控制线","key_name":"橡套控制线"},{"id":96,"name":"水泵线","key_name":"水泵线"},{"id":226,"name":"生产厂家","key_name":"橡套电缆生产厂家"}]},{"id":38,"name":"计算机电缆","list":[{"id":101,"name":"DJYPVP","key_name":"DJYPVP"},{"id":102,"name":"DJYVP","key_name":"DJYVP"},{"id":105,"name":"DJYPVP22","key_name":"DJYPVP22"},{"id":104,"name":"DJYPVRP","key_name":"DJYPVRP"},{"id":248,"name":"DJYP2VP2","key_name":"DJYP2VP2"},{"id":227,"name":"生产厂家","key_name":"计算机电缆生产厂家"}]},{"id":39,"name":"光伏线","list":[{"id":110,"name":"PV1-F","key_name":"PV1-F"},{"id":111,"name":"全项保检现货","key_name":"光伏电缆全项保检现货"},{"id":113,"name":"生产厂家","key_name":"光伏电缆生产厂家"}]},{"id":40,"name":"家装电线","list":[{"id":114,"name":"BV","key_name":"BV"},{"id":229,"name":"RV","key_name":"RV"},{"id":120,"name":"RVS","key_name":"RVS"},{"id":123,"name":"NH-RVS","key_name":"NH-RVS"},{"id":230,"name":"WDZN-RYS","key_name":"WDZN-RYS"},{"id":121,"name":"WDZ-BV","key_name":"WDZ-BV"},{"id":124,"name":"WDZN-BV","key_name":"WDZN-BV"},{"id":122,"name":"NH-BV","key_name":"NH-BV"},{"id":119,"name":"RVV软芯护套线","key_name":"RVV软芯护套线"},{"id":115,"name":"BVV硬芯护套线","key_name":"BVV硬芯护套线"},{"id":118,"name":"BVVB","key_name":"BVVB"},{"id":117,"name":"BLVV","key_name":"BLVV"},{"id":184,"name":"防老化线","key_name":"防老化线"},{"id":175,"name":"电话线","key_name":"电话线"},{"id":231,"name":"网线","key_name":"网线"},{"id":232,"name":"视频线","key_name":"视频线"},{"id":244,"name":"音响线","key_name":"音响线"},{"id":116,"name":"生产厂家","key_name":"家装电线生产厂家"}]},{"id":41,"name":"铝合金电缆","list":[{"id":233,"name":"YJHLV","key_name":"YJHLV"},{"id":234,"name":"YJHLV22","key_name":"YJHLV22"},{"id":126,"name":"铝合金橡套线","key_name":"铝合金橡套线"},{"id":125,"name":"生产厂家","key_name":"铝合金电缆生产厂家"}]},{"id":42,"name":"架空线","list":[{"id":236,"name":"JKLYJ","key_name":"JKLYJ"},{"id":235,"name":"JKYJ","key_name":"JKYJ"},{"id":131,"name":"10KV架空线","key_name":"10KV架空线"},{"id":237,"name":"JKTRYJ","key_name":"JKTRYJ"},{"id":132,"name":"全项保检现货","key_name":"架空电缆全项保检现货"},{"id":129,"name":"钢芯铝绞线","key_name":"钢芯铝绞线"},{"id":238,"name":"铝绞线  LJ","key_name":"铝绞线 LJ"},{"id":128,"name":"集束导线","key_name":"集束导线"},{"id":239,"name":"生产厂家","key_name":"架空线生产厂家"}]},{"id":43,"name":"通讯电缆","list":[{"id":133,"name":"HYA","key_name":"HYA"},{"id":134,"name":"HYAT","key_name":"HYAT"},{"id":135,"name":"HYA23","key_name":"HYA23"},{"id":136,"name":"HYAT53","key_name":"HYAT53"},{"id":137,"name":"HSYV","key_name":"HSYV"},{"id":200,"name":"RS485","key_name":"RS485型通讯电缆"},{"id":138,"name":"矿用通信线","key_name":"矿用通信线"},{"id":139,"name":"生产厂家","key_name":"通信电缆生产厂家"}]},{"id":44,"name":"光纤光缆","list":[{"id":141,"name":"GYTA","key_name":"GYTA"},{"id":140,"name":"GYXTW","key_name":"GYXTW"},{"id":142,"name":"GYTA53","key_name":"GYTA53"},{"id":143,"name":"MGTSV","key_name":"MGTSV"},{"id":144,"name":"GJFJV","key_name":"GJFJV"},{"id":146,"name":"皮线光纤","key_name":"皮线光纤"},{"id":145,"name":"生产厂家","key_name":"光纤光缆生产厂家"}]},{"id":45,"name":"扁电缆","list":[{"id":147,"name":"扁电缆现货","key_name":"扁电缆现货"},{"id":150,"name":"电梯专用扁电缆","key_name":"电梯专用扁电缆"},{"id":149,"name":"氟塑料扁电缆","key_name":"氟塑料扁电缆"},{"id":148,"name":"生产厂家","key_name":"扁电缆生产厂家"}]},{"id":46,"name":"矿用电缆","list":[{"id":151,"name":"MY","key_name":"MY"},{"id":152,"name":"MYP","key_name":"MYP"},{"id":153,"name":"MCP","key_name":"MCP"},{"id":156,"name":"MHYV","key_name":"MHYV"},{"id":157,"name":"MHYVP","key_name":"MHYVP"},{"id":158,"name":"MHYA23","key_name":"MHYA23"},{"id":159,"name":"MHYAV","key_name":"MHYAV"},{"id":160,"name":"MGTSV","key_name":"MGTSV"},{"id":155,"name":"MCPT","key_name":"MCPT"},{"id":154,"name":"MYPTJ","key_name":"MYPTJ"},{"id":161,"name":"生产厂家","key_name":"矿用电缆生产厂家"},{"id":179,"name":"盾构机电缆","key_name":"盾构机电缆"}]},{"id":163,"name":"特种电缆","list":[{"id":164,"name":"硅橡胶电缆","key_name":"硅橡胶电缆"},{"id":165,"name":"弹性体电缆","key_name":"弹性体电缆"},{"id":166,"name":"行车电缆","key_name":"行车电缆"},{"id":168,"name":"补偿导线","key_name":"补偿导线"},{"id":169,"name":"本安电缆","key_name":"本安电缆"},{"id":171,"name":"充电桩电缆","key_name":"充电桩电缆"},{"id":172,"name":"铲运机电缆","key_name":"铲运机电缆"},{"id":173,"name":"船用电缆","key_name":"船用电缆"},{"id":177,"name":"地埋线","key_name":"地埋线"},{"id":178,"name":"电源线","key_name":"电源线"},{"id":180,"name":"低温电缆","key_name":"低温电缆"},{"id":181,"name":"丁腈电缆","key_name":"丁腈电缆"},{"id":183,"name":"防水电缆","key_name":"防水电缆"},{"id":185,"name":"防爆电缆","key_name":"防爆电缆"},{"id":186,"name":"防白蚁（鼠）电缆","key_name":"防白蚁（鼠）电缆"},{"id":187,"name":"港口机械用电缆","key_name":"港口机械用电缆"},{"id":192,"name":"卷筒电缆","key_name":"卷筒电缆"},{"id":193,"name":"聚氨酯电缆","key_name":"聚氨酯电缆"},{"id":196,"name":"腊克线","key_name":"腊克线"},{"id":198,"name":"耐高温电缆","key_name":"耐高温电缆"},{"id":247,"name":"电梯电缆","key_name":"电梯电缆"}]},{"id":47,"name":"线缆附件","list":[{"id":162,"name":"防火电缆附件","key_name":"防火电缆附件"}]}]
     */
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 常用
         * list : [{"id":11,"name":"低压铜芯电缆","key_name":"低压铜芯电缆"},{"id":12,"name":"低压铝芯电缆","key_name":"低压铝芯电缆"},{"id":14,"name":"矿物质防火电缆","key_name":"矿物质防火电缆"},{"id":13,"name":"高压电缆","key_name":"高压电缆"},{"id":15,"name":"低烟无卤电缆","key_name":"低烟无卤电缆"},{"id":16,"name":"耐火电缆","key_name":"耐火电缆"},{"id":17,"name":"控制电缆","key_name":"控制电缆"},{"id":18,"name":"屏蔽电缆","key_name":"屏蔽电缆"},{"id":19,"name":"软电缆","key_name":"软电缆"},{"id":20,"name":"橡套电缆","key_name":"橡套电缆"},{"id":21,"name":"计算机电缆","key_name":"计算机电缆"},{"id":22,"name":"光伏线","key_name":"光伏线"},{"id":23,"name":"塑铜线","key_name":"塑铜线"},{"id":24,"name":"铝合金电缆","key_name":"铝合金电缆"},{"id":25,"name":"架空线","key_name":"架空线"},{"id":26,"name":"通讯电缆","key_name":"通讯电缆"},{"id":27,"name":"光纤光缆","key_name":"光纤光缆"},{"id":28,"name":"扁电缆","key_name":"扁电缆"},{"id":29,"name":"矿用电缆","key_name":"矿用电缆"},{"id":30,"name":"线缆附件","key_name":"线缆附件"},{"id":61,"name":"历史铜价","key_name":"原料行情"}]
         */

        private int id;
        private String name;
        private List<ListBean> list;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Parcelable {
            /**
             * id : 11
             * name : 低压铜芯电缆
             * key_name : 低压铜芯电缆
             */

            private int id;
            private String name;
            private String key_name;

            protected ListBean(Parcel in) {
                id = in.readInt();
                name = in.readString();
                key_name = in.readString();
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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getKey_name() {
                return key_name;
            }

            public void setKey_name(String key_name) {
                this.key_name = key_name;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(id);
                parcel.writeString(name);
                parcel.writeString(key_name);
            }
        }
    }
}
