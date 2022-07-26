package cn.chenzecheng.alan.goods.bean;

import lombok.Data;

/**
 * @author JacksonChen
 * @date 2022/7/26 22:06
 */
@Data
public class TryReduceStockReq {


    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品数量
     */
    private int num;
}
