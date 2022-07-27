package cn.chenzecheng.alan.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author JacksonChen
 * @date 2022/7/18 22:09
 */
@AllArgsConstructor
@Getter
public enum MyErrorEnum {

    /**
     * 通用异常
     */
    SYS_ERR("SYS_ERR", "系统异常"),

    /**
     * 业务异常
     */
    NOT_EXIST("NOT_EXIST", "找不到记录"),

    /**
     * 远程请求异常
     */
    REMOTE_ERROR("REMOTE_ERROR", "远程请求异常"),
    ;

    private String code;
    private String desc;
}
