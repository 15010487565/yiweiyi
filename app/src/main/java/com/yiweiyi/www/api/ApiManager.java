package com.yiweiyi.www.api;

import com.yiweiyi.www.base.BaseBean;
import com.yiweiyi.www.base.CommonData;
import com.yiweiyi.www.bean.compe.CompeDetailsBean;
import com.yiweiyi.www.bean.compe.ProdCataBean;
import com.yiweiyi.www.bean.compe.ReliableListBean;
import com.yiweiyi.www.bean.compe.ShareImgBean;
import com.yiweiyi.www.bean.login.SigninBean;
import com.yiweiyi.www.bean.main.HomeCategoryBean;
import com.yiweiyi.www.bean.personal.BusinessPhoneListBean;
import com.yiweiyi.www.bean.personal.FreeEntryBean;
import com.yiweiyi.www.bean.personal.UserInfoBean;
import com.yiweiyi.www.bean.raw.RawMaterialBean;
import com.yiweiyi.www.bean.search.CommonAreasListBean;
import com.yiweiyi.www.bean.search.ProximitySearchBean;
import com.yiweiyi.www.bean.search.SearchCompeBean;
import com.yiweiyi.www.bean.search.SearchRecordsBean;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import rx.Observable;

/**
 * @Author: zsh
 * 2020/9/25
 * desc:
 */
public class ApiManager {
    private static ApiService apiService;
    private static ApiManager apiManager;

    private ApiManager() {
        initApi();
    }

    public static ApiManager getInstance() {
        if (apiManager == null) {
            apiManager = new ApiManager();
        }
        return apiManager;
    }

    public static ApiService getApiService() {
        return apiService;
    }

    public static void initApi() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CommonData.mainUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(okHttpClient)
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    /*******************************************登陆************************************************/
    //获取验证码
    public Observable<BaseBean> sendVerifiCode(String phone) {
        return apiService.sendVerifiCode(phone);
    }

    //登陆
    public Observable<SigninBean> signin(String phone,
                                         String code) {
        return apiService.signin(phone, code, "android");
    }

    //微信登录
    public Observable<BaseBean> wxLogin(String unionid) {
        return apiService.wxLogin(unionid);
    }

    //微信登录新增用户绑定手机号
    public Observable<SigninBean> wxBindPhone(String unionid,
                                            String openid,
                                            String nickname,
                                            String avatar,
                                            String phone,String code) {
        return apiService.wxBindPhone(unionid, openid, nickname, avatar, phone,code, "android");
    }

    //用户协议
    public Observable<FreeEntryBean> userAgreement() {
        return apiService.userAgreement();
    }

    //隐私政策
    public Observable<FreeEntryBean> privacyPolicy() {
        return apiService.privacyPolicy();
    }

    //中国移动认证服务条款
    public Observable<FreeEntryBean> certificationServiceTerms() {
        return apiService.certificationServiceTerms();
    }


    /*******************************************个人************************************************/

    //意见反馈
    public Observable<BaseBean> feedback(String user_id,
                                         String content) {
        return apiService.feedback(user_id, content);
    }

    //意见反馈
    public Observable<FreeEntryBean> freeEntry() {
        return apiService.freeEntry();
    }

    //关于我们
    public Observable<FreeEntryBean> aboutUs() {
        return apiService.aboutUs();
    }

    //客服电话
    public Observable<FreeEntryBean> consumerHotline() {
        return apiService.consumerHotline();
    }

    public Observable<BaseBean> addCustomerCall(String user_id,
                                                String form_tel,
                                                String call_time,
                                                String is_connect) {
        return apiService.addCustomerCall(user_id, form_tel, "android", call_time, is_connect);
    }

    //用户基本信息接口
    public Observable<UserInfoBean> userInfo(String user_id) {
        return apiService.userInfo(user_id);
    }

    //修改厂家名称
    public Observable<BaseBean> setCompeName(String user_id, String name) {
        return apiService.setCompeName(user_id, name);
    }

    //修改公司 Logo
    public Observable<BaseBean> setCompeLogo(String user_id, String logo) {
        return apiService.setCompeLogo(user_id, logo);
    }

    //获取商家联系电话
    public Observable<BusinessPhoneListBean> businessPhoneList(String user_id) {
        return apiService.businessPhoneList(user_id);
    }
    //修改用户昵称
    public Observable<BaseBean> setUserName(String user_id, String nickname) {
        return apiService.setUserName(user_id, nickname);
    }

    //修改用户头像
    public Observable<BaseBean> setUserHeader(String user_id, String avatar) {
        return apiService.setUserHeader(user_id, avatar);
    }

    /*******************************************首页************************************************/
    //获取主页系列
    public Observable<HomeCategoryBean> homeCategory() {
        return apiService.homeCategory();
    }

    /*******************************************搜索************************************************/
    //用户搜索记录
    public Observable<SearchRecordsBean> searchRecords(String user_id) {
        return apiService.searchRecords(user_id);
    }

    //用户清空搜索记录
    public Observable<BaseBean> clearRecords(String user_id) {
        return apiService.clearRecords(user_id);
    }

    //用户删除搜索记录
    public Observable<BaseBean> deleteRecord(Map<String, Object> map) {
        return apiService.deleteRecord(map);
    }

    //返回接近关键词
    public Observable<ProximitySearchBean> proximitySearch(String search) {
        return apiService.proximitySearch(search);
    }

    //搜索接口
    public Observable<SearchCompeBean> searchCompe(String search,
                                                   String user_id,
                                                   String area,
                                                   String page) {
        return apiService.searchCompe(search, user_id, area,page);
    }

    //用户添加常用地区
    public Observable<BaseBean> addCommonAreas(String user_id,
                                               String used_area) {
        return apiService.addCommonAreas(user_id, used_area);
    }

    //用户删除常用地区
    public Observable<BaseBean> delCommonAreas(String user_id,
                                               String area) {
        return apiService.delCommonAreas(user_id, area);
    }

    //返回搜索厂家包含地区
    public Observable<CommonAreasListBean> commonAreasList(String user_id,
                                                           String area) {
        return apiService.commonAreasList(user_id, area);
    }

    /*******************************************商家************************************************/
    //搜索接口
    public Observable<CompeDetailsBean> compeDetails(int shop_id,
                                                     String user_id) {
        return apiService.compeDetails(shop_id, user_id);
    }

    //产品图册
    public Observable<ProdCataBean> prodCata(int shop_id,
                                             String album_name) {
        return apiService.prodCata(shop_id, album_name);
    }

    //实景图册列表
    public Observable<ProdCataBean> realPicture(int shop_id,
                                                String album_name) {
        return apiService.realPicture(shop_id, album_name);
    }

    //资质证书图册列表
    public Observable<ProdCataBean> certification(int shop_id,
                                                  String album_name) {
        return apiService.certification(shop_id, album_name);
    }

    //获取分享图片
    public Observable<ShareImgBean> shareImg(int shop_id) {
        return apiService.shareImg(shop_id);
    }

    //靠谱列表
    public Observable<ReliableListBean> reliableList(@Field("shop_id") int shop_id) {
        return apiService.reliableList(shop_id);
    }

    //添加 靠谱
    public Observable<BaseBean> addReliable(@Field("shop_id") int shop_id,
                                            @Field("user_id") String user_id) {
        return apiService.addReliable(shop_id, user_id);
    }

    //取消 靠谱
    public Observable<BaseBean> delReliable(@Field("shop_id") int shop_id,
                                            @Field("user_id") String user_id) {
        return apiService.delReliable(shop_id, user_id);
    }

    /*******************************************原料行情************************************************/

    //原料行情
    public Observable<RawMaterialBean> rawMaterial(int type, String year) {
        return apiService.rawMaterial(type, year);
    }
}
