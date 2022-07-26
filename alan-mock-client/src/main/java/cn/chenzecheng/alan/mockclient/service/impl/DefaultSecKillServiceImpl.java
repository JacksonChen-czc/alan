package cn.chenzecheng.alan.mockclient.service.impl;

import cn.chenzecheng.alan.account.RemoteAccountApi;
import cn.chenzecheng.alan.account.bean.AccountResp;
import cn.chenzecheng.alan.common.bean.MyResult;
import cn.chenzecheng.alan.goods.RemoteGoodsApi;
import cn.chenzecheng.alan.goods.bean.GoodsResp;
import cn.chenzecheng.alan.mockclient.service.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 秒杀场景实现
 *
 * @author JacksonChen
 * @date 2022/7/25 16:58
 */
@Service
@Slf4j
public class DefaultSecKillServiceImpl implements SecKillService {

    @Resource
    private RemoteAccountApi remoteAccountApi;

    @Resource
    private RemoteGoodsApi remoteGoodsApi;

    @Override
    public void doSecKill(AccountResp account, GoodsResp goods) {
        log.info("用户【{}】执行抢购下单行为", account.getAccountName());
        // 获取用户详情
        MyResult<AccountResp> detail = remoteAccountApi.detail(account.getAccountId());
        AccountResp data = detail.getData();
        // todo 查询库存,需要单独的单表查询接口
        MyResult<GoodsResp> goodsStock = remoteGoodsApi.detail(goods.getGoodsId());
        // 如果没库存，就结束
        if (goodsStock.getData().getSaleNum() <= 0) {
            log.info("{} 没抢到商品 {}。", account.getAccountName(), goods.getGoodsName());
            return;
        }
        // todo 如果有库存就下单，插入订单，扣减库存

    }
}
