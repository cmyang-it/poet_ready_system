package com.sinocontact.app.common;

/**
 * 静态常量
 * @author todd
 * @since 2019/1/21
 */
public class CommonConstant {
    //登录成功
    public final static String SESSION_KEY="user";

    /**
     * 图形验证码
     */
    public static final String VERCODE_KEY = "verCode";

    /**
     * 短信验证码
     */
    public static final String PHONE = "phone";


    /**
     * 短信应用sdk APPID
     */
    public final static Integer APP_ID = 1400182140;

    /**
     * 短信应用SDK APPkey
     */
    public final static String APP_KEY = "215c6a0a0b4347b1034d0151a4a046dc";

    /**
     * 短信模板ID（注册验证模板）
     */
    public final static Integer TEMPLATE_ID = 323792;

    /**
     * 短信模板id（找回密码）
     */
    public static final Integer TEMPLATE_FORGET_ID = 274720;

    /**
     * 短信签名 “羲阳阅读网”
     */
    public final static String SIGN = "羲阳阅读网";

    /**
     * 检索标记
     */
    public final static Integer CLICK_FLAG = 1;
    public final static Integer RECOMMEND_FLAG = 2;
    public final static Integer COLLECT_FLAG = 3;
    public final static Integer TOTAL_FLAG = 0;


    /**
     * 未登录
     */
    public final static String ONE_MONTH_NO_LOGIN = "未登录";


    /**
     * 登录过
     */
    public final static String ONE_MONTH_LOGIN = "登录过";

    /**
     * 分页数据大小
     */
    public final static Integer PAGE_SIZE = 10;


    /**
     * 当昵称为空时留言显示
     */
    public final static String NICKNAME_IS_NULL = "佚名";

}
