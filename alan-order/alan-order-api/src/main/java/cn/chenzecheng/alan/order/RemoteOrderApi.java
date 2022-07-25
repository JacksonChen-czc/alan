package cn.chenzecheng.alan.order;

import cn.chenzecheng.alan.order.bean.OrderListReq;
import cn.chenzecheng.alan.order.bean.OrderResp;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

/**
 * 账号接口api
 *
 * @author JacksonChen
 */
@FeignClient(name = "alan-order", fallbackFactory = RemoteOrderApiFallbackFactory.class)
public interface RemoteOrderApi {

    /**
     * 查询订单列表
     *
     * @param orderListReq
     * @return
     */
    List<OrderResp> list(OrderListReq orderListReq);
}