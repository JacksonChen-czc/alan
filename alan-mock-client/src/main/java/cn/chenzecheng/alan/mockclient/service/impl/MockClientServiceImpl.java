package cn.chenzecheng.alan.mockclient.service.impl;

import cn.chenzecheng.alan.account.RemoteAccountApi;
import cn.chenzecheng.alan.account.bean.AccountListRep;
import cn.chenzecheng.alan.account.bean.AccountResp;
import cn.chenzecheng.alan.common.bean.MyPageResult;
import cn.chenzecheng.alan.common.bean.MyResult;
import cn.chenzecheng.alan.goods.RemoteGoodsApi;
import cn.chenzecheng.alan.goods.bean.GoodsListRep;
import cn.chenzecheng.alan.goods.bean.GoodsResp;
import cn.chenzecheng.alan.goods.bean.StockResp;
import cn.chenzecheng.alan.mockclient.service.MockClientService;
import cn.chenzecheng.alan.mockclient.service.SecKillService;
import cn.chenzecheng.alan.order.RemoteOrderApi;
import cn.chenzecheng.alan.order.bean.GoodsOrderListReq;
import cn.chenzecheng.alan.order.bean.GoodsOrderResp;
import cn.hutool.core.collection.CollUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 模拟客户端实现
 *
 * @author JacksonChen
 * @date 2022/7/25 16:57
 */
@Service
@Slf4j
public class MockClientServiceImpl implements MockClientService {

    private final Random RANDOM = new Random();

    @Resource(name = "secKillThreadPool")
    private ExecutorService executorService;

    @Resource
    private RemoteAccountApi remoteAccountApi;

    @Resource
    private RemoteGoodsApi remoteGoodsApi;

    @Resource
    private RemoteOrderApi remoteOrderApi;

    @Resource
    private SecKillService secKillService;

    @SneakyThrows
    @Override
    public void secKill(int currentNum) {
        // 查询大量用户，模拟大批用户同时操作（并发度可以配置）
        List<AccountResp> accounts = getAccounts(currentNum);
        if (CollUtil.isEmpty(accounts)) {
            return;
        }
        log.info("商城同时来了{}个用户", currentNum);
        // 查询商品列表，获得一种商品的秒杀库存
        GoodsResp goods = getRandomGoods();
        if (Objects.isNull(goods) || goods.getSaleNum() <= 0) {
            log.info("没有找到商品，或者商品没库存了");
            return;
        }
        log.info("商品【{}】 id【{}】推广秒杀，可售数量是【{}】", goods.getGoodsName(), goods.getGoodsId(), goods.getSaleNum());
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        List<Future<Integer>> futures = Lists.newArrayList();
        readWriteLock.writeLock().lock();
        try {
            for (AccountResp account : accounts) {
                // 使用线程池或实现并发
                Future<Integer> submit = executorService.submit(() -> {
                    log.info("打开页面准备抢购，用户：{}，id：{}", account.getAccountName(), account.getAccountId());
                    readWriteLock.readLock().lock();
                    try {
                        log.info("到点开抢了，用户：{}，id：{}", account.getAccountName(), account.getAccountId());
                        // 执行单个用户对单个商品的抢购行为（也可以提前准备好数据，用ab，jmeter，contiperf等方式测试）
                        secKillService.doSecKill(account, goods);
                    } finally {
                        readWriteLock.readLock().unlock();
                    }
                    return 1;
                });
                futures.add(submit);
            }
        } finally {
            readWriteLock.writeLock().unlock();
        }
        // 子线程全部结束后，再继续主线程
        futures.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                log.warn("future.get异常", e);
                throw new RuntimeException(e);
            }
        });
        log.info("------------- 抢购完毕，检查超卖 -------------");
        // 查询商品库存
        MyResult<StockResp> stock = remoteGoodsApi.queryStock(goods.getGoodsId());
        int soldNum = goods.getSaleNum() - stock.getData().getSaleNum();

        // 查询订单数
        GoodsOrderListReq goodsOrderListReq = new GoodsOrderListReq();
        goodsOrderListReq.setGoodsId(goods.getGoodsId());
        // 理论上应该查询全部的该商品订单数量，实际应该不超过商品可售数量，随便查2倍数量看看有没有超卖
        goodsOrderListReq.setSize(goods.getSaleNum() * 2);
        MyPageResult<GoodsOrderResp> orderRespMyPageResult = remoteOrderApi.goodsOrderList(goodsOrderListReq);
        int goodsOrderAmount = orderRespMyPageResult.getData().stream().mapToInt(GoodsOrderResp::getAmount).sum();

        // 核对订单和库存数扣减数量是否一致
        if (orderRespMyPageResult.getPage().hasNext()
                || goodsOrderAmount != soldNum) {
            log.warn("×××××××××× 卖出数量[{}]与库存扣减数量[{}]不一致。", goodsOrderAmount, soldNum);
        } else {
            log.info("√√√√√√√√√√ 卖出数量[{}]与库存扣减数量[{}]一致。", goodsOrderAmount, soldNum);
        }
    }


    private GoodsResp getRandomGoods() {
        int randomInt = RANDOM.nextInt(1000000);
        GoodsListRep remoteReq = new GoodsListRep();
        remoteReq.setSize(1);
        remoteReq.setPageNo(randomInt);
        MyPageResult<GoodsResp> list = remoteGoodsApi.list(remoteReq);
        if (CollUtil.isEmpty(list.getData())) {
            log.warn("找不到商品 randomInt:[{}]", randomInt);
            return null;
        }
        return list.getData().get(0);
    }

    private List<AccountResp> getAccounts(Integer num) {
        AccountListRep remoteReq = new AccountListRep();
        remoteReq.setSize(num);
        remoteReq.setPageNo(1);
        MyPageResult<AccountResp> list = remoteAccountApi.list(remoteReq);
        if (CollUtil.isEmpty(list.getData())) {
            log.warn("找不到用户 randomInt:[{}]", num);
            return Lists.newArrayList();
        }
        return list.getData();
    }

    private AccountResp getRandomAccount() {
        int randomInt = RANDOM.nextInt(1000000);
        log.info("调用MockClientServiceImpl.secKill randomInt:[{}]", randomInt);
        AccountListRep remoteReq = new AccountListRep();
        remoteReq.setSize(1);
        remoteReq.setPageNo(randomInt);
        MyPageResult<AccountResp> list = remoteAccountApi.list(remoteReq);
        if (CollUtil.isEmpty(list.getData())) {
            log.warn("找不到用户randomInt:[{}]", randomInt);
            return null;
        }
        return list.getData().get(0);
    }
}
