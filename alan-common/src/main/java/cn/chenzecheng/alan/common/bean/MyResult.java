package cn.chenzecheng.alan.common.bean;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @program: mqdemo
 * @description: 通用响应格式数据
 * @author: shenning
 * @create: 2020-10-09 11:27
 */
@Data
@NoArgsConstructor
public class MyResult<T> {
    /**
     * 状态码: (如果状态码多,可以用枚举)
     * 200代表响应成功
     * 500代表响应失败
     */
    private int code;
    /**
     * 响应信息，用来说明响应情况
     */
    private String msg;
    /**
     * 响应的具体数据
     */
    private T data;

    /**
     * @param code 状态码
     * @param msg  响应信息，用来说明响应情况
     * @param data 响应的具体数据
     */
    private MyResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private MyResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 成功返回(用于单条)
     *
     * @param data 响应数据
     * @return
     */
    public static <T> MyResult<T> ok(T data) {
        return new MyResult<T>(200, "success", data);
    }

    /**
     * 成功返回(用于单条)
     *
     * @param msg  响应信息，用来说明响应情况
     * @param data 响应数据
     * @return
     */
    public static <T> MyResult<T> ok(String msg, T data) {
        return new MyResult<T>(200, msg, data);
    }

    /**
     * 失败返回
     *
     * @param msg 响应信息，用来说明响应情况
     * @return
     */
    public static <T> MyResult<T> error(String msg) {
        return new MyResult<T>(500, msg);
    }
}