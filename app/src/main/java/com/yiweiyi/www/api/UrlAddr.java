package com.yiweiyi.www.api;

import com.yiweiyi.www.base.CommonData;

/**
 * Created by vic on 2016/7/13.
 */
public interface UrlAddr {

    String path = CommonData.mainUrl;
    String EDITNICKNAME = path+"api/user/editNickname";//修改昵称

    String EDITAVATAR = path+"api/user/editAvatar/";//修改用户头像
    String UPLOADIMG = path+"api/upload/uploadImg";//上传图片接口

    String RAWMARKET = path+"api/market/index";//原料行情
    String SETTLEDTIPS = path+"api/index/settledTips";//获取入驻提示

    String ABOUT_US = path+"api/index/about_us";//关于我们
    String SHOP_DETAILS = path+"api/shop/info";//商家详情表

    String ALNUM = path+"api/shop/album/";//
    String ALBUMLIST3 = path+"api/shop/ALBUMLIST3/";//资质证书
    String LIKE = path+"api/log/like/";//靠谱列表

    String EDITLOGO = path+"api/shop/editLogo";//修改公司 Logo
    String EDITSHOPNAME = path+"api/shop/editShopName";//修改厂家名称

    String GETSHOPPHONE = path+"api/shop/getShopPhone";//获取商家联系电话
    String EDITSHOPPHONE = path+"api/shop/editShopPhone";//编辑联系电话


    String BROWSE = path+"api/log/browse";//访问记录
    String BROWSEADD = path+"api/log/browseAdd";//新增 访问记录

    String CALLLOG = path+"api/log/callLog";//通话记录

    String ADDCALLLOG = path+"api/log/addCallLog";//新增 通话记录

    String USER_AGREEMENT = path+"api/index/user_agreement";//用户协议
    String PRIVACYPOLICY = path+"api/index/privacyPolicy";//隐私政策

    String LIKEADD = path+"api/log/likeAdd";//点赞靠谱
    String LIKEDEL = path+"api/log/likeDel";//取消 靠谱

    String SEARCH_INDEX = path+"api/search/index";//浏览
    String FEEDBACK = path+"api/index/feedback";//意见反馈
//    String BROWSE_REMOVE_SELECT = path+"api/browse/remove";//移除选中浏览记录
//    String BROWSE_REMOVE_REMOVEALL = path+"api/browse/removeAll";//移除所有浏览记录
//
//    String RELATION = path+"api/platform/relation";//联系
//
//    String CLAUSE = url+"agreement/clause.html";//服务条款
//    String PRIVACY = url+"agreement/privacy.html";//隐私政策
//
//    String EDITSTATE = path+"api/user/editstate";//修改用户状态
//    String TOPIC_SAVE = path+"api/topic/save";//发布内容
//    String TOPIC_LIST = path+"api/topic/list";//更多话题
//    String TOPIC_ARRAY = path+"api/topic/array";//获取话题中发布列表
//
//    String UPDATEUPDATE = path+"api/platform/update";//版本更新
//    String LOGOUT = path+"api/user/logout/";//注销账户
}
