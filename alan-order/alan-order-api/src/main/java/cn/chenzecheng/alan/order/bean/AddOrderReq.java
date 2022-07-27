package cn.chenzecheng.alan.order.bean;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author JacksonChen
 * @date 2022/7/27 22:12
 */
@Data
public class AddOrderReq {

    /**
     * 用户id
     */
    @NotNull(message = "用户id不为空")
    private Long accountId;

    /**
     * 商品明细
     */
    @NotEmpty(message = "商品明细不能为空")
    private List<OrderGoodsReq> orderGoodsReqList;

    @Data
    public static class OrderGoodsReq {

        private Long goodsId;

        /**
         * 商品数
         */
        private Integer goodsAmount;

        /**
         * 商品总价,单位：分
         */
        private Long goodsPriceSum;
    }

}
