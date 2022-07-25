package cn.chenzecheng.alan.mockclient.service.impl;

import cn.chenzecheng.alan.account.RemoteAccountApi;
import cn.chenzecheng.alan.account.bean.AccountListRep;
import cn.chenzecheng.alan.account.bean.AccountResp;
import cn.chenzecheng.alan.common.bean.MyPageResult;
import cn.chenzecheng.alan.common.bean.MyResult;
import cn.chenzecheng.alan.goods.RemoteGoodsApi;
import cn.chenzecheng.alan.goods.bean.GoodsListRep;
import cn.chenzecheng.alan.goods.bean.GoodsResp;
import cn.chenzecheng.alan.mockclient.service.MockClientService;
import cn.chenzecheng.alan.mockclient.service.SecKillService;
import cn.chenzecheng.alan.order.RemoteOrderApi;
import cn.chenzecheng.alan.order.bean.OrderListReq;
import cn.chenzecheng.alan.order.bean.OrderResp;
import cn.hutool.core.collection.CollUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

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
        if (Objects.isNull(goods)) {
            return;
        }
        CountDownLatch countDownLatch = new CountDownLatch(currentNum);
        List<Thread> threads = Lists.newArrayList();
        for (AccountResp account : accounts) {
            Thread thread = new Thread(() -> {
                countDownLatch.countDown();
                log.info("打开页面准备抢购，用户：{}，id：{}", account.getAccountName(), account.getAccountId());
                try {
                    countDownLatch.await();
                    log.info("到点开抢了，用户：{}，id：{}", account.getAccountName(), account.getAccountId());
                    // 执行单个用户对单个商品的抢购行为（也可以提前准备好数据，用ab，jmeter，contiperf等方式测试）
                    secKillService.doSecKill(account, goods);
                } catch (InterruptedException e) {
                    log.warn("countDownLatch.wait error", e);
                    throw new RuntimeException(e);
                }
            });
            thread.start();
            threads.add(thread);
        }
        // 子线程全部结束后，再继续主线程
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        log.info("------------- 抢购完毕，统计战绩 -------------");
        // 查询商品库存
        MyResult<GoodsResp> detail = remoteGoodsApi.detail(goods.getGoodsId());
        // 查询订单数
        OrderListReq orderListReq = new OrderListReq();
        orderListReq.setGoodsId(goods.getGoodsId());
        List<OrderResp> orderRespList = remoteOrderApi.list(orderListReq);
        // 核对订单和库存数扣减数量是否一致

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
