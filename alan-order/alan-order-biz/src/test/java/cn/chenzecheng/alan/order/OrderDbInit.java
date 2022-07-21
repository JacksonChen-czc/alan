package cn.chenzecheng.alan.order;

import cn.chenzecheng.alan.account.RemoteAccountApi;
import cn.chenzecheng.alan.account.bean.AccountListRep;
import cn.chenzecheng.alan.account.bean.AccountResp;
import cn.chenzecheng.alan.common.bean.MyPageResult;
import cn.chenzecheng.alan.common.exception.BizException;
import cn.chenzecheng.alan.goods.RemoteGoodsApi;
import cn.chenzecheng.alan.goods.bean.GoodsListRep;
import cn.chenzecheng.alan.goods.bean.GoodsResp;
import cn.chenzecheng.alan.order.entity.Order;
import cn.chenzecheng.alan.order.entity.OrderGoods;
import cn.chenzecheng.alan.order.enums.OrderStatusEnum;
import cn.chenzecheng.alan.order.service.IOrderGoodsService;
import cn.chenzecheng.alan.order.service.IOrderService;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 数据库数据初始化
 *
 * @author JacksonChen
 * @date 2022/7/19 22:03
 */
@SpringBootTest
@Slf4j
public class OrderDbInit {

    @Resource
    private IOrderGoodsService orderGoodsService;

    @Resource
    private IOrderService orderService;

    @Resource
    private RemoteAccountApi remoteAccountApi;
    @Resource
    private RemoteGoodsApi remoteGoodsApi;


    private ExecutorService threadPool = new ThreadPoolExecutor(16, 32, 10L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024), new ThreadPoolExecutor.CallerRunsPolicy());

    @Test
    public void insert() {
        MyPageResult<AccountResp> accountResult = remoteAccountApi.list(new AccountListRep());
        if (CollUtil.isEmpty(accountResult.getData())) {
            log.warn("请先初始化账号数据");
            throw new BizException("请先初始化账号数据");
        }
        MyPageResult<GoodsResp> goodsResult = remoteGoodsApi.list(new GoodsListRep());
        if (CollUtil.isEmpty(goodsResult.getData())) {
            log.warn("请先初始化商品数据");
            throw new BizException("请先初始化商品数据");
        }
        GoodsResp goodsResp = goodsResult.getData().get(0);
        AccountResp accountResp = accountResult.getData().get(0);
        Order order = new Order();
        order.setAccountId(accountResp.getAccountId());
        order.setOrderPrice(goodsResp.getGoodsPrice());
        order.setOrderStatus(OrderStatusEnum.CREATED.getCode());
        orderService.save(order);

        OrderGoods orderGoods = new OrderGoods();
        orderGoods.setOrderId(order.getOrderId());
        orderGoods.setGoodsId(goodsResp.getGoodsId());
        orderGoods.setAmount(1);
        orderGoods.setGoodsPriceSum(goodsResp.getGoodsPrice().multiply(new BigDecimal(1)));
        orderGoodsService.save(orderGoods);
    }
}
