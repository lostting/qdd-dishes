package cn.qdd.foods.domain.base;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * @author 东方曜
 */
@Slf4j
public class ResultUtil {

     public static final String SUCCESS = "SUCCESS";

    /**
     * 成功
     */
    public static Result success() {
        return new Result(ResultCode.SUCCESS.code(), SUCCESS);
    }

    /**
     * 成功，附带额外数据
     */
    public static Result success(Object data) {
        return new Result(ResultCode.SUCCESS.code(), SUCCESS, data);
    }

    /**
     * 成功，自定义消息及数据
     */
    public static Result success(String message, Object data) {
        return new Result(ResultCode.SUCCESS.code(), message, data);
    }

    /**
     * 失败，附带消息
     */
    public static Result fail() {
        return new Result(ResultCode.FAIL.code(), ResultCode.FAIL.message, new ArrayList<>());
    }

    /**
     * 失败，附带消息
     */
    public static Result fail(String message) {
        return new Result(ResultCode.FAIL.code(), message);
    }

    /**
     * 验证出错,返回错误信息
     */
    public static Result fail(int code, String message) {
        return new Result(code, message);
    }

    /**
     * 失败，自定义消息及数据
     */
    public static Result fail(String message, Object data) {
        return new Result(ResultCode.FAIL.code(), message, data);
    }


}
