package com.cxy.zjbg.common.constant;

/**
 * @Author: xing-yu-chen
 * @Project: zj-bg
 * @Description: 基础配置静态类
 * @Data:Created in 2021/7/13 2:01 下午
 */
public interface Constant {

    /**
     * @Author: xingyuchen
     * @Discription: APP名称
     * @param null
     * @Date: 2021/7/14 8:07 下午
     */
    public static final String APP_NAME = "在浙";

    /**
     * @Author: xingyuchen
     * @Discription: 发送者邮箱
     * @param null
     * @Date: 2021/7/14 8:14 下午
     */

    public static final String OWN_EMAIL = "c1540351685@163.com";

    /**
     * @Author: xingyuchen
     * @Discription:
     * @param null
     * @Date: 2021/7/14 8:16 下午
     */

    /**
     * @Author: xingyuchen
     * @Discription: 日期数据格式
     * @param null
     * @Date: 2021/7/13 2:03 下午
     */
    public static final String DATETIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * @Author: xingyuchen
     * @Discription: 请求状态码
     * @Param 200 SUCCESS_CODE 请求成功数据
     * @Param 400 BAD_REQUEST_CODE 请求内容错误或者参数错误
     * @Param 401 UNAUTHORIZED_CODE 登陆失败没有认证，未授权
     * @Param 404 NOT_FOUND_CODE 无法找到请求资源
     * @Param 405 METGOD_NOT_ALLOWED 不合法的请求方
     * @Param 500 INTER_SERVER_ERROR_CODE 内部服务错误/权限
     * @Date: 2021/7/13 2:16 下午
     */
    public static final Integer SUCCESS_CODE = 200;
    public static final Integer BAD_REQUEST_CODE = 400;
    public static final Integer UNAUTHORIZED_CODE = 401;
    public static final Integer NOT_FOUND_CODE = 404;
    public static final Integer METGOD_NOT_ALLOWED = 405;
    public static final Integer INTER_SERVER_ERROR_CODE = 500;

    /**
     * @Author: xingyuchen
     * @Discription: 设置分页每页的记录数目为10
     * @param null
     * @Date: 2021/7/13 2:22 下午
     */
    public static final Integer PAGE_RECORD = 10;

    /**
     * @Author: xingyuchen
     * @Discription: 设置token的过期时间
     * @param null
     * @Date: 2021/7/13 2:23 下午
     */
    public static final Integer TOKEN_EXPIRE = 12;

    /**
     * @Author: xingyuchen
     * @Discription: 设置邮箱验证码的缓存时间3分钟
     * @param null
     * @Date: 2021/7/14 6:09 下午
     */

    public static final Integer EMAIL_EXIT_TIME = 180;

    /**
     * @Author: xingyuchen
     * @Discription: 设置发送邮箱的头信息
     * @param null
     * @Date: 2021/7/16 10:52 下午
     */
    public static final String REGISTER_CODE = "email";
    public static final String FIND_PASSWORD_CODE= "find";

    /**
     * @Author: xingyuchen
     * @Discription: 设置邮件的相关信息
     * @param null
     * @Date: 2021/7/16 11:31 下午
    */
    public static final String VERIFY_CODE="验证码";
    public static final String FIND_PASSWORD="密码";

    public static final String VERIFY_MSG=",在3分钟内输入有效，请立即登陆。";
    public static final String FIND_PASSWORD_MSG=",请前往登陆。";

}
