package cn.chenzecheng.alan.mockclient.service.impl;

import cn.chenzecheng.alan.account.RemoteAccountApi;
import cn.chenzecheng.alan.account.bean.AccountResp;
import cn.chenzecheng.alan.common.bean.MyResult;
import cn.chenzecheng.alan.common.exception.MyAssertUtil;
import cn.chenzecheng.alan.common.util.PriceUtil;
import cn.chenzecheng.alan.goods.RemoteGoodsApi;
import cn.chenzecheng.alan.goods.bean.GoodsResp;
import cn.chenzecheng.alan.goods.bean.StockResp;
import cn.chenzecheng.alan.goods.bean.TryReduceStockReq;
import cn.chenzecheng.alan.mockclient.service.SecKillService;
import cn.chenzecheng.alan.order.RemoteOrderApi;
import cn.chenzecheng.alan.order.bean.AddOrderReq;
import com.alibaba.fastjson.JSON;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private SecKillService secKillService;

    @Override
    public void doSecKill(AccountResp account, GoodsResp goods) {
        log.info("用户【{}】开始抢购", account.getAccountName());

        // 获取用户详情，一般进入页面都会获取用户信息
        AccountResp data = getAccountDetail(account);
        log.info("当前用户详情【{}】", JSON.toJSON(data));

        // 查询库存，如果没库存，就结束
        MyResult<StockResp> goodsStock = getStock(goods);
        if (goodsStock.getData().getSaleNum() <= 0) {
            log.info("用户【{}】查询【{}】的库存为【{}】，无法抢购 。", account.getAccountName(), goods.getGoodsName(), goodsStock.getData().getSaleNum());
            return;
        }
        // 扣减库存，增加订单
        secKillService.tryReduceStockAndAddOrder(account, goods);
    }

    @GlobalTransactional
    @Override
    public void tryReduceStockAndAddOrder(AccountResp account, GoodsResp goods) {
        // 有库存，扣减库存
        int num = 1;
        Boolean reduceResult = tryReduceStock(goods, num);
        if (!Boolean.TRUE.equals(reduceResult)) {
            log.info("用户【{}】扣减【{}】的库存失败，无法下单 。", account.getAccountName(), goods.getGoodsName());
            return;
        }

        // 扣减库存成功，则新增订单
        addOrder(account, goods, num);
        System.out.println("aaa");
    }

    protected void addOrder(AccountResp account, GoodsResp goods, int num) {
        AddOrderReq addOrderReq = buildAddOrderReq(account, goods, num);
        MyResult<Boolean> addOrderResult = remoteOrderApi.addOrder(addOrderReq);
        MyAssertUtil.isResultSuc(addOrderResult);
    }

    protected Boolean tryReduceStock(GoodsResp goods, int num) {
        TryReduceStockReq tryReduceStockReq = new TryReduceStockReq();
        tryReduceStockReq.setGoodsId(goods.getGoodsId());
        tryReduceStockReq.setNum(num);
        MyResult<Boolean> reduceStockResult = remoteGoodsApi.tryReduceStock(tryReduceStockReq);
        MyAssertUtil.isResultSuc(reduceStockResult);
        return reduceStockResult.getData();
    }

    protected AccountResp getAccountDetail(AccountResp account) {
        MyResult<AccountResp> detail = remoteAccountApi.detail(account.getAccountId());
        MyAssertUtil.isResultSuc(detail);
        return detail.getData();
    }

    protected MyResult<StockResp> getStock(GoodsResp goods) {
        MyResult<StockResp> goodsStock = remoteGoodsApi.queryStock(goods.getGoodsId());
        MyAssertUtil.isResultSuc(goodsStock);
        return goodsStock;
    }

    private AddOrderReq buildAddOrderReq(AccountResp account, GoodsResp goods, int num) {
        List<AddOrderReq.OrderGoodsReq> orderGoodsReqList = Lists.newArrayList();
        AddOrderReq.OrderGoodsReq orderGoodsReq = new AddOrderReq.OrderGoodsReq();
        orderGoodsReq.setGoodsId(goods.getGoodsId());
        orderGoodsReq.setGoodsAmount(num);
        orderGoodsReq.setGoodsPriceSum(PriceUtil.convertToLong(goods.getGoodsPrice()) * num);
        orderGoodsReqList.add(orderGoodsReq);
        AddOrderReq addOrderReq = new AddOrderReq();
        addOrderReq.setAccountId(account.getAccountId());
        addOrderReq.setOrderGoodsReqList(orderGoodsReqList);
        return addOrderReq;
    }
}
