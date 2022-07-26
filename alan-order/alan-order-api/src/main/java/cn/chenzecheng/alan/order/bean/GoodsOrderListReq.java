package cn.chenzecheng.alan.order.bean;

import cn.chenzecheng.alan.common.bean.BasePageReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author JacksonChen
 * @date 2022/7/25 23:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GoodsOrderListReq extends BasePageReq {

    /**
     * 商品id
     */
    private Long goodsId;
}
