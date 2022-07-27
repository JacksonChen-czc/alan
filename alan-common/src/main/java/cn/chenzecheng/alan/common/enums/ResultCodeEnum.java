package cn.chenzecheng.alan.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 结果代码枚举
 *
 * @author JacksonChen
 * @date 2022/7/27 22:26
 */
@AllArgsConstructor
@Getter
public enum ResultCodeEnum {

    /**
     * 请求成功
     */
    SUCCESS(200, "请求成功"),

    /**
     * 业务异常
     */
    ERROR(500, "系统异常"),
    ;

    private Integer code;
    private String desc;
}
