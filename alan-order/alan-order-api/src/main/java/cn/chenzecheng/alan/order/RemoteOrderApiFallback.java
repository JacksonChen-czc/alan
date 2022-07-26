package cn.chenzecheng.alan.order;

import cn.chenzecheng.alan.order.bean.OrderListReq;
import cn.chenzecheng.alan.order.bean.OrderResp;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

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
    public List<OrderResp> list(OrderListReq orderListReq) {
        return null;
    }
}
