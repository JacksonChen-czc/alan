package cn.chenzecheng.alan.mockclient.service;

import cn.chenzecheng.alan.account.bean.AccountResp;
import cn.chenzecheng.alan.goods.bean.GoodsResp;

/**
 * 秒杀场景接口
 *
 * @author JacksonChen
 * @date 2022/7/25 16:58
 */
public interface SecKillService {

    /**
     * 用户执行秒杀操作
     *
     * @param account
     * @param goods
     */
    void doSecKill(AccountResp account, GoodsResp goods);
}
