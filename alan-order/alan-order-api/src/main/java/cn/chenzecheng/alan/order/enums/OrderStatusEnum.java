package cn.chenzecheng.alan.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author JacksonChen
 * @date 2022/7/21 22:34
 */
@AllArgsConstructor
@Getter
public enum OrderStatusEnum {

    /**
     * 订单状态
     */
    CREATED(1, "已创建"),
    HAD_PAY(2, "已支付"),
    CANCEL(3, "已取消"),
    ;

    private Integer code;

    private String desc;
}
