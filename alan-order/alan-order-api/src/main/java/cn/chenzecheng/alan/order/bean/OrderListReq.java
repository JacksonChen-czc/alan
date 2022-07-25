package cn.chenzecheng.alan.order.bean;

import cn.chenzecheng.alan.common.bean.BasePageReq;
import lombok.Data;

/**
 * @author JacksonChen
 * @date 2022/7/25 23:45
 */
@Data
public class OrderListReq extends BasePageReq {

    /**
     * 商品id
     */
    private Long goodsId;
}
