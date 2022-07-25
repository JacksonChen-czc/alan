package cn.chenzecheng.alan.mockclient.service.impl;

import cn.chenzecheng.alan.account.bean.AccountResp;
import cn.chenzecheng.alan.goods.bean.GoodsResp;
import cn.chenzecheng.alan.mockclient.service.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 秒杀场景实现
 *
 * @author JacksonChen
 * @date 2022/7/25 16:58
 */
@Service
@Slf4j
public class DefaultSecKillServiceImpl implements SecKillService {

    @Override
    public void doSecKill(AccountResp account, GoodsResp goods) {
        log.info("用户【{}】执行抢购下单行为", account.getAccountName());
        // 查询库存
        // 如果没库存，就结束
        // 如果有库存就下单，插入订单，扣减库存
    }
}
