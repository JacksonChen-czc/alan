package cn.chenzecheng.alan.mockclient.service.impl;

import cn.chenzecheng.alan.account.RemoteAccountApi;
import cn.chenzecheng.alan.account.bean.AccountResp;
import cn.chenzecheng.alan.common.bean.MyResult;
import cn.chenzecheng.alan.goods.RemoteGoodsApi;
import cn.chenzecheng.alan.goods.bean.GoodsResp;
import cn.chenzecheng.alan.goods.bean.StockResp;
import cn.chenzecheng.alan.goods.bean.TryReduceStockReq;
import cn.chenzecheng.alan.mockclient.service.SecKillService;
import cn.chenzecheng.alan.order.RemoteOrderApi;
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

    @Resource
    private RemoteOrderApi remoteOrderApi;

    @Override
    public void doSecKill(AccountResp account, GoodsResp goods) {
        log.info("用户【{}】开始抢购", account.getAccountName());
        // 获取用户详情
        MyResult<AccountResp> detail = remoteAccountApi.detail(account.getAccountId());
        AccountResp data = detail.getData();
        // 查询库存
        MyResult<StockResp> goodsStock = remoteGoodsApi.queryStock(goods.getGoodsId());
        // 如果没库存，就结束
        if (goodsStock.getData().getSaleNum() <= 0) {
            log.info("用户【{}】查询【{}】的库存为【{}】，无法抢购 。", account.getAccountName(), goods.getGoodsName(), goodsStock.getData().getSaleNum());
            return;
        }
        // 如果有库存，扣减库存
        TryReduceStockReq tryReduceStockReq = new TryReduceStockReq();
        tryReduceStockReq.setGoodsId(goods.getGoodsId());
        tryReduceStockReq.setNum(1);
        MyResult<Boolean> reduceStockResult = remoteGoodsApi.tryReduceStock(tryReduceStockReq);
        if (!Boolean.TRUE.equals(reduceStockResult.getData())) {
            log.info("用户【{}】扣减【{}】的库存失败，无法下单 。", account.getAccountName(), goods.getGoodsName());
            return;
        }
        // todo 扣减库存成功，则新增订单
//        MyResult<Boolean> addOrderResult = remoteOrderApi.addOrder();


    }
}
