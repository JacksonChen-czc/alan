package cn.chenzecheng.alan.goods.bean;

import lombok.Data;

/**
 * @author JacksonChen
 * @date 2022/7/26 22:14
 */
@Data
public class StockResp {

    /**
     * 库存对应的商品id
     */
    private Long goodsId;

    /**
     * 商品可售数量
     */
    private Integer saleNum;

    /**
     * 库存总数
     */
    private Integer total;
}
