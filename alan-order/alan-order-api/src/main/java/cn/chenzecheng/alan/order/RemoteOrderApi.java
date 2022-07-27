package cn.chenzecheng.alan.order;

import cn.chenzecheng.alan.common.bean.MyPageResult;
import cn.chenzecheng.alan.common.bean.MyResult;
import cn.chenzecheng.alan.order.bean.AddOrderReq;
import cn.chenzecheng.alan.order.bean.GoodsOrderListReq;
import cn.chenzecheng.alan.order.bean.GoodsOrderResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
     * @param goodsOrderListReq
     * @return
     */
    @PostMapping("/order/goods/list")
    MyPageResult<GoodsOrderResp> goodsOrderList(@RequestBody GoodsOrderListReq goodsOrderListReq);

    /**
     * 新增订单
     *
     * @param req
     * @return
     */
    @PostMapping("/order/add")
    MyResult<Boolean> addOrder(@RequestBody AddOrderReq req);
}