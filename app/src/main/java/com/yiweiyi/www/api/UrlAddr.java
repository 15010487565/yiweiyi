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

    String ALNUM = path+"api/shop/alnum/";//搜索页猜你喜欢
//    String SEARCH = path+"api/search/list/";//搜索关键字
//
//    String CHANNEL = path+"api/channel/list/";//获取频道
//    String CHANNEL_EDIT = path+"api/channel/edit/";//频道编辑
//
//    String USERINFO = path+"api/user/info/";//个人信息
//    String USERINFO_AD = path+"api/ad/list";//个人信息广告
//    String USERINFO_LAUNCH_AD = path+"api/ad/initad";//欢迎页广告
//    String AD_ADD = path+"api/ad/add";//广告
//
//    String UPLOADIMG = path+"api/upload/img";//上传图片
//    String USERINFO_EDIT = path+"api/user/edit";//修改头像
//
//    String ATTENTION_SAVE = path+"api/focus/save";//增加关注
//    String ATTENTION_REMOVE = path+"api/focus/remove";//移除关注
//    String ATTENTION_REMOVE_All = path+"api/focus/remove";//移除全部关注
//    String ATTENTION_LIST = path+"api/focus/history";//关注
//
//    String BROWSE_LIST = path+"api/browse/history";//浏览
//    String BROWSE_SAVE = path+"api/browse/save";//浏览新增
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
