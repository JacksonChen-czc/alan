package cn.chenzecheng.alan.order;

import cn.chenzecheng.alan.common.bean.MyPageResult;
import cn.chenzecheng.alan.common.bean.MyResult;
import cn.chenzecheng.alan.order.bean.AddOrderReq;
import cn.chenzecheng.alan.order.bean.GoodsOrderListReq;
import cn.chenzecheng.alan.order.bean.GoodsOrderResp;
import lombok.extern.slf4j.Slf4j;

/**
 * @author JacksonChen
 * @date 2022/7/16 16:42
 */
@Slf4j
public class RemoteOrderApiFallback implements RemoteOrderApi {

    private Throwable throwable;

    public RemoteOrderApiFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public MyPageResult<GoodsOrderResp> goodsOrderList(GoodsOrderListReq goodsOrderListReq) {
        log.error("获取订单列表异常", throwable);
        return MyPageResult.error("获取订单列表异常");
    }

    @Override
    public MyResult<Boolean> addOrder(AddOrderReq req) {
        log.error("新增订单异常", throwable);
        return MyResult.error("新增订单异常");
    }
}
