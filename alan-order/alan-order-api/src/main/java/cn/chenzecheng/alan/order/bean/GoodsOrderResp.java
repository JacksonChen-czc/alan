package cn.chenzecheng.alan.order.bean;

import lombok.Data;

/**
 * @author JacksonChen
 * @date 2022/7/25 23:45
 */
@Data
public class GoodsOrderResp {

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品数量
     */
    private int amount;

    /**
     * 订单id
     */
    private int orderId;
}
