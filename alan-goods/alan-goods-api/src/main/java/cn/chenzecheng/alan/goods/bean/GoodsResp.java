package cn.chenzecheng.alan.goods.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author JacksonChen
 * @date 2022/7/19 23:08
 */
@Data
public class GoodsResp {

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;

    /**
     * 商品描述
     */
    private String goodDesc;

    /**
     * 商品可售数量
     */
    private Integer saleNum;

    /**
     * 库存总数
     */
    private Integer total;
}
