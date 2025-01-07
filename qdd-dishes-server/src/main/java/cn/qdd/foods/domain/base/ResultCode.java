package cn.qdd.foods.domain.base;

/**
 * 全局状态码
 */
public enum ResultCode {
    /**
     * 自定义的返回状态码
     */

    SUCCESS(200, "请求成功"),
    NOT_LOGIN(403, "未登录"),
    FAIL(500, "服务器响应失败"),
    NOT_ACCOUNT(1000, "账号或密码错误"),
    LOGIN_ISEXIST(1001, "用户名已经存在"),
    NO_ROLE(1002, "权限不足"),
    NOT_USER(1003, "该用户不存在"),
    NO_PERMISSION(1004, "无权限登录");


    private final int code;

    public String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }
}