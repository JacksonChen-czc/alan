package cn.chenzecheng.alan.common.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * @program: mqdemo
 * @description: 通用响应格式数据
 * @author: shenning
 * @create: 2020-10-09 11:27
 */
@Data
@NoArgsConstructor
public class MyPageResult<T> {
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
    private List<T> data;
    /**
     * 分页数据
     */
    private MyPageInfo page;

    /**
     * @param code 状态码
     * @param msg  响应信息，用来说明响应情况
     * @param data 响应的具体数据
     * @param page 分页信息
     */
    private MyPageResult(int code, String msg, List<T> data, MyPageInfo page) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.page = page;
    }

    private MyPageResult(int code, String msg, List<T> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private MyPageResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    /**
     * 成功返回(用于单条)
     *
     * @param msg  响应信息，用来说明响应情况
     * @param data 响应数据
     * @return
     */
    public static <T> MyPageResult<T> ok(String msg, List<T> data) {
        return new MyPageResult<T>(200, msg, data);
    }

    /**
     * 成功返回(用于数量多的数据)
     *
     * @param data 响应数据
     * @param page 分页数据
     * @return
     */
    public static <T> MyPageResult<T> ok(List<T> data, MyPageInfo page) {
        return new MyPageResult<T>(200, "success", data, page);
    }

    /**
     * 成功返回(用于数量多的数据)
     *
     * @param msg  响应信息，用来说明响应情况
     * @param data 响应数据
     * @return
     */
    public static <T> MyPageResult<T> ok(String msg, List<T> data, MyPageInfo page) {
        return new MyPageResult<T>(200, msg, data, page);
    }

    /**
     * 失败返回
     *
     * @param msg 响应信息，用来说明响应情况
     * @return
     */
    public static <T> MyPageResult<T> error(String msg) {
        return new MyPageResult<T>(500, msg);
    }
}