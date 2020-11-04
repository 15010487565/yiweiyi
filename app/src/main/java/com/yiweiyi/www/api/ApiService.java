package com.yiweiyi.www.api;

import com.yiweiyi.www.bean.personal.BusinessPhoneListBean;
import com.yiweiyi.www.bean.personal.UserInfoBean;
import com.yiweiyi.www.bean.raw.RawMaterialBean;
import com.yiweiyi.www.base.BaseBean;
import com.yiweiyi.www.bean.compe.CompeDetailsBean;
import com.yiweiyi.www.bean.compe.ProdCataBean;
import com.yiweiyi.www.bean.compe.ReliableListBean;
import com.yiweiyi.www.bean.compe.ShareImgBean;
import com.yiweiyi.www.bean.login.SigninBean;
import com.yiweiyi.www.bean.main.HomeCategoryBean;
import com.yiweiyi.www.bean.personal.FreeEntryBean;
import com.yiweiyi.www.bean.search.CommonAreasListBean;
import com.yiweiyi.www.bean.search.ProximitySearchBean;
import com.yiweiyi.www.bean.search.SearchCompeBean;
import com.yiweiyi.www.bean.search.SearchRecordsBean;

import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @Author: zsh
 * 2020/9/25
 * desc:接口地址
 */
public interface ApiService {

    /*******************************************注册流程************************************************/
    /**
     * 发送验证码
     *
     * @param phone 手机号
     * @return
     */
    @FormUrlEncoded()
    @POST("api/login/sendVerifyCode/")
    Observable<BaseBean> sendVerifiCode(@Field("phone") String phone);

    /**
     * 登陆
     *
     * @param phone  手机
     * @param code   验证码
     * @param source 那一端
     * @return
     */
    @FormUrlEncoded()
    @POST("api/login/appLogin/")
    Observable<SigninBean> signin(@Field("phone") String phone,
                                  @Field("code") String code,
                                  @Field("source") String source);

    /**
     * 微信登录
     *
     * @param unionid
     * @return
     */
    @FormUrlEncoded()
    @POST("api/login/wxLogin/")
    Observable<BaseBean> wxLogin(@Field("unionid") String unionid);

    /**
     * 微信登录新增用户绑定手机号
     *
     * @param unionid
     * @param openid
     * @param nickname
     * @param avatar
     * @param phone
     * @param source
     * @return
     */
    @FormUrlEncoded()
    @POST("api/login/addWxLogin/")
    Observable<SigninBean> wxBindPhone(@Field("unionid") String unionid,
                                     @Field("openid") String openid,
                                     @Field("nickname") String nickname,
                                     @Field("avatar") String avatar,
                                     @Field("phone") String phone,
                                       @Field("code") String code,
                                     @Field("source") String source);

    /**
     * 用户协议
     *
     * @return
     */
    @POST("api/index/user_agreement")
    Observable<FreeEntryBean> userAgreement();

    /**
     * 隐私政策
     *
     * @return
     */
    @POST("api/index/privacyPolicy/")
    Observable<FreeEntryBean> privacyPolicy();

    /**
     * 中国移动认证服务条款
     *
     * @return
     */
    @POST("api/index/serviceTerms/")
    Observable<FreeEntryBean> certificationServiceTerms();
    /*******************************************个人信息************************************************/

    /**
     * 意见反馈
     *
     * @param user_id 用户id
     * @param content 意见内容详情
     * @return
     */
    @FormUrlEncoded()
    @POST("api/index/feedback/")
    Observable<BaseBean> feedback(@Field("user_id") String user_id,
                                  @Field("content") String content);

    /**
     * 获取入驻提示
     *
     * @return
     */
    @POST("api/index/settledTips/")
    Observable<FreeEntryBean> freeEntry();

    /**
     * 关于我们
     *
     * @return
     */
    @POST("api/index/about_us/")
    Observable<FreeEntryBean> aboutUs();

    /**
     * 客服电话
     *
     * @return
     */
    @POST("api/index/service/")
    Observable<FreeEntryBean> consumerHotline();

    /**
     * 添加客服通话记录
     *
     * @param user_id    用户ID
     * @param form_tel   谁打的客服电话
     * @param client     客服端,写死 ios 就行
     * @param call_time  通话时间
     * @param is_connect 是否接通,值: 是 或 否
     * @return
     */
    @FormUrlEncoded()
    @POST("api/index/serviceCallLog/")
    Observable<BaseBean> addCustomerCall(@Field("user_id") String user_id,
                                         @Field("form_tel") String form_tel,
                                         @Field("client") String client,
                                         @Field("call_time") String call_time,
                                         @Field("is_connect") String is_connect);

    /**
     * 用户基本信息接口
     *
     * @param user_id
     * @return
     */
    @FormUrlEncoded()
    @POST("api/user/index/")
    Observable<UserInfoBean> userInfo(@Field("user_id") String user_id);

    /**
     * 修改厂家名称
     * @param user_id
     * @param name
     * @return
     */
    @FormUrlEncoded()
    @POST("api/shop/editShopName/")
    Observable<BaseBean> setCompeName(@Field("user_id") String user_id,
                                      @Field("name") String name);

    /**
     * 修改公司 Logo
     *
     * @param user_id
     * @param logo
     * @return
     */
    @FormUrlEncoded()
    @POST("api/shop/editLogo/")
    Observable<BaseBean> setCompeLogo(@Field("user_id") String user_id,
                                      @Field("logo") String logo);

    /**
     * 获取商家联系电话
     *
     * @param user_id
     * @return
     */
    @FormUrlEncoded()
    @POST("api/shop/getShopPhone/")
    Observable<BusinessPhoneListBean> businessPhoneList(@Field("user_id") String user_id);

    /**
     * 修改用户昵称
     * @param user_id
     * @param nickname
     * @return
     */
    @FormUrlEncoded()
    @POST("api/user/editNickname/")
    Observable<BaseBean> setUserName(@Field("user_id") String user_id,
                                      @Field("nickname") String nickname);

    /**
     * 修改用户头像
     *
     * @param user_id
     * @param avatar
     * @return
     */
    @FormUrlEncoded()
    @POST("api/user/editAvatar/")
    Observable<BaseBean> setUserHeader(@Field("user_id") String user_id,
                                      @Field("avatar") String avatar);

    /*******************************************首页************************************************/

    /**
     * 首页材料系列
     *
     * @return
     */
    @POST("api/index/index/")
    Observable<HomeCategoryBean> homeCategory();

    /*******************************************搜索************************************************/


    /**
     * 用户搜索记录
     *
     * @param user_id 用户id
     * @return
     */
    @FormUrlEncoded()
    @POST("api/search/searchLog/")
    Observable<SearchRecordsBean> searchRecords(@Field("user_id") String user_id);

    /**
     * 用户清空搜索记录
     *
     * @param user_id 用户id
     * @return
     */
    @FormUrlEncoded()
    @POST("api/search/emptySearchLog/")
    Observable<BaseBean> clearRecords(@Field("user_id") String user_id);

    /**
     * 用户删除搜索记录
     *
     * @param map
     * @return
     */
    @POST("api/search/delSearchLog/")
    Observable<BaseBean> deleteRecord(@Body Map<String, Object> map);

    /**
     * 返回接近关键词
     *
     * @param search 用户输入的内容
     * @return
     */
    @FormUrlEncoded()
    @POST("api/search/tab/")
    Observable<ProximitySearchBean> proximitySearch(@Field("search") String search);

    /**
     * 搜索接口
     *
     * @param search  搜索内容
     * @param user_id 用户ID,非必填
     * @param area    地区,非必填
     * @return
     */
    @FormUrlEncoded()
    @POST("api/search/index/")
    Observable<SearchCompeBean> searchCompe(@Field("search") String search,
                                            @Field("user_id") String user_id,
                                            @Field("area") String area);

    /**
     * 用户添加常用地区
     *
     * @param user_id   用户ID
     * @param used_area 常用地区名称
     * @return
     */
    @FormUrlEncoded()
    @POST("api/Area/addUsedArea/")
    Observable<BaseBean> addCommonAreas(@Field("user_id") String user_id,
                                        @Field("used_area") String used_area);

    /**
     * 用户删除常用地区
     *
     * @param user_id
     * @param area
     * @return
     */
    @FormUrlEncoded()
    @POST("api/Area/delUsedArea/")
    Observable<BaseBean> delCommonAreas(@Field("user_id") String user_id,
                                        @Field("area") String area);

    /**
     * 返回搜索厂家包含地区
     *
     * @param user_id
     * @param area
     * @return
     */
    @FormUrlEncoded()
    @POST("api/Area/index/")
    Observable<CommonAreasListBean> commonAreasList(@Field("user_id") String user_id,
                                                    @Field("area") String area);



    /*******************************************商家************************************************/

    /**
     * 商家详情表
     *
     * @param shop_id 商家ID
     * @param user_id
     * @return
     */
    @FormUrlEncoded()
    @POST("api/shop/info/")
    Observable<CompeDetailsBean> compeDetails(@Field("shop_id") int shop_id,
                                              @Field("user_id") String user_id);

    /**
     * 产品图册
     *
     * @param shop_id
     * @param album_name
     * @return
     */
    @FormUrlEncoded()
    @POST("api/shop/albumList1/")
    Observable<ProdCataBean> prodCata(@Field("shop_id") int shop_id,
                                      @Field("album_name") String album_name);

    /**
     * 实景图册列表
     *
     * @param shop_id
     * @param album_name
     * @return
     */
    @FormUrlEncoded()
    @POST("api/shop/albumList2/")
    Observable<ProdCataBean> realPicture(@Field("shop_id") int shop_id,
                                         @Field("album_name") String album_name);

    /**
     * 资质证书图册列表
     *
     * @param shop_id
     * @param album_name
     * @return
     */
    @FormUrlEncoded()
    @POST("api/shop/albumList3/")
    Observable<ProdCataBean> certification(@Field("shop_id") int shop_id,
                                           @Field("album_name") String album_name);

    /**
     * 获取分享图片
     *
     * @param shop_id
     * @return
     */
    @FormUrlEncoded()
    @POST("api/shop/getShareImg/")
    Observable<ShareImgBean> shareImg(@Field("shop_id") int shop_id);

    /**
     * 靠谱列表
     *
     * @param shop_id
     * @return
     */
    @FormUrlEncoded()
    @POST("api/log/like/")
    Observable<ReliableListBean> reliableList(@Field("shop_id") int shop_id);

    /**
     * 添加 靠谱
     *
     * @param shop_id
     * @param user_id
     * @return
     */
    @FormUrlEncoded()
    @POST("api/log/likeAdd/")
    Observable<BaseBean> addReliable(@Field("shop_id") int shop_id,
                                     @Field("user_id") String user_id);

    /**
     * 取消 靠谱
     *
     * @param shop_id
     * @param user_id
     * @return
     */
    @FormUrlEncoded()
    @POST("api/log/likeDel/")
    Observable<BaseBean> delReliable(@Field("shop_id") int shop_id,
                                     @Field("user_id") String user_id);

    /*******************************************原料行情************************************************/

    /**
     * 原料行情
     *
     * @param type (1 '1#铜' , 2 'A00铝')
     * @param year 年份
     * @return
     */
    @FormUrlEncoded()
    @POST("api/market/index/")
    Observable<RawMaterialBean> rawMaterial(@Field("type") int type,
                                            @Field("year") String year);
}
