package com.chat.app.util;

/**
 * Created by zbs on 16/9/1.
 */
public class Constant {

    /**服务订单/子类型 --> 1 : 医美订单**/
    public final static int ORDER_FW_SUB_TYPE_YM = 1;
    /**服务订单/子类型 --> 2 : 按摩订单**/
    public final static int ORDER_FW_SUB_TYPE_AM = 2;
    /**商城订单/子类型 --> 1 : 商城订单**/
    public final static int ORDER_SC_SUB_TYPE_SC = 3;

//    public final static String url_path = "http://server_url";
  //  public final static String url_path = "http://test.us-app.com";
    public final static String url_path = "http://hotr.us-app.com";
    public final static String uri_find_surgery= url_path + "/service/surgery/findbyid";

    public final static String uri_find_surgery_collection  = url_path + "/service/surgery/findbyid";

    public final static String uri_enable_collection  = url_path + "/service/returnEnableCollection";
    public final static String uri_find_technician = url_path + "/service/technician/findbyid";
public final static String uri_find_user_coupon_num = url_path+"/order/promotion/getUserCouponNum";	
     /** 评论类型 --> 1 回复评论 **/
    public final static int comment_type_reply = 1;
     /** 评论类型 --> 0 回复文章 **/
    public final static int comment_type_comment = 0;

    /** 是否收藏 --> 1 收藏 **/
    public final static int state_collection = 1;
    /** 是否收藏 --> 0 没收藏 **/
    public final static int state_not_collection = 0;

    /** 删除标识 --> 1 删除 **/
    public final static int  enable_invalid= 1;
    /** 删除标识 --> 0 正常 **/
    public final static int enable_effect = 0;


    //hotr的微信小程序相关
    //hotr.............
    public final static String appid = "wxb5e84dbc608d23b6";
    public final static String secret = "f3e0ed821c2a6d3b090af590e6f20d65";
    public final static String wx_login_path = "https://api.weixin.qq.com/sns/jscode2session?";

    //12345678952
    public static final String DEFAULT_PASSWORD = "def677132701779c74c9bd95dae4de57";

    //腾讯地址api的key
    public static final String tencent_location_key = "5CABZ-O5QK6-P3KSE-MNBMQ-IOTUH-YOBBN";
    //腾讯地址api的地址
    public static final String tencent_location_url = "https://apis.map.qq.com/ws/geocoder/v1";



    //文档导入
    /** 导入的文章类型 --> 0 文字 **/
    public static final Integer import_articles_type_text = 0;
    /** 导入的文章类型 --> 1 图片 **/
    public static final Integer import_articles_type_image = 1;

    public static final String oss_endpoint = "http://oss-cn-shanghai.aliyuncs.com";
    public static final String oss_accessKeyId = "bbCSP8lqgIzv3yV4";
    public static final String oss_accessKeySecret = "FABewRUw1YdgVnKmolR40Ot5rKpe1s";
    public static final String oss_bucketName = "robotfile";
    public static final String oss_articles_key = "articles";
    public static final String oss_localFile = "/home";


    public static final String default_avatar = "http://us-shop-2.oss-cn-beijing.aliyuncs.com/images/temp/avatar/new_ava.png";
  public static final String MAX_PAGE_SIZE  = "100";
}
