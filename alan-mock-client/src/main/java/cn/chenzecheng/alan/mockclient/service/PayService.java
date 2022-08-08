package cn.chenzecheng.alan.mockclient.service;

import cn.chenzecheng.alan.account.bean.AccountResp;

/**
 * 支付接口
 *
 * @author JacksonChen
 * @date 2022/8/8 23:02
 */
public interface PayService {


    /**
     * 用户查询订单并支付
     *
     * @param aAccount
     * @param aNull
     */
    void doPay(AccountResp aAccount, Void aNull);
}
