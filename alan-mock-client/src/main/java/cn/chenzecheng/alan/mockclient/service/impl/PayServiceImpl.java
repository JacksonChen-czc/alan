package cn.chenzecheng.alan.mockclient.service.impl;

import cn.chenzecheng.alan.account.bean.AccountResp;
import cn.chenzecheng.alan.mockclient.service.PayService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author JacksonChen
 * @date 2022/8/8 23:02
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {


    @GlobalTransactional
    @Override
    public void doPay(AccountResp aAccount, Void aNull) {
        // TODO: 2022/8/8 查询所有订单，逐一支付，如余额不足，则取消订单，归还库存
    }
}
