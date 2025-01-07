package cn.qdd.foods.domain.base;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 返回结果类
 *
 * @author 东方曜
 */
@Slf4j
@Data
public class Result implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 状态码
     */
    private int code;
    /**
     * 消息
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;


    public Result(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, Object data) {
        this(code, msg);
        this.data = data;
    }

    public Result() {

    }


}
