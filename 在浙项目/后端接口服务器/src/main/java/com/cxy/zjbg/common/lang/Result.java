package com.cxy.zjbg.common.lang;

import com.cxy.zjbg.common.constant.Constant;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: xing-yu-chen
 * @Project: zj-bg
 * @Description: 请求返回数据统一封装格式
 * @Data:Created in 2021/7/13 2:24 下午
 */
@Data
public class Result implements Serializable {

    //状态码
    private Integer code;

    //返回前端的提示信息
    private String msg;

    //返回的数据
    private Object data;
    /**
     * @param code 状态码
     * @param msg  提示信息
     * @param data 数据
     * @Author: xingyuchen
     * @Discription: 返回succ结果的底层封装
     * @Date: 2021/7/13 2:45 下午
     */

    public static Result succ(int code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }


    /**
     * @Author: xingyuchen
     * @Discription: 返回succ结果的200成功状态码的信息
     * @param data 数据
     * @Date: 2021/7/13 2:47 下午
     */
    public static Result succ(Object data) {
        return succ(Constant.SUCCESS_CODE, "操作成功", data);
    }

    //异常数据的调用方法

    /**
     * @Author: xingyuchen
     * @Discription: 错误信息的底层封装，用于返回异常信息
     * @param code 状态码
     * @param msg 提示信息
     * @param data 数据
     * @Date: 2021/7/13 2:53 下午
     */

    public static Result fail(int code,String msg,Object data){
        Result result=new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * @Author: xingyuchen
     * @Discription: 500错误,所有归结于服务器响应失败的错误
     * @param msg 提示信息
     * @param data 数据
     * @Date: 2021/7/13 3:05 下午
     */

    public static Result fail(String msg,Object data){
        return fail(Constant.INTER_SERVER_ERROR_CODE,msg,data);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 400错误数据异常，密码错误
     * @param msg 提示信息
     * @Date: 2021/7/13 3:06 下午
     */

    public static Result fail(String msg){
        return fail(Constant.BAD_REQUEST_CODE,msg,null);
    }
}
