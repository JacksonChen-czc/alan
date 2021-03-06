package cn.chenzecheng.alan.mockclient.controller;

import cn.chenzecheng.alan.account.RemoteAccountApi;
import cn.chenzecheng.alan.account.bean.AccountListRep;
import cn.chenzecheng.alan.account.bean.AccountResp;
import cn.chenzecheng.alan.common.bean.MyPageResult;
import cn.chenzecheng.alan.common.bean.MyResult;
import cn.chenzecheng.alan.goods.RemoteGoodsApi;
import cn.chenzecheng.alan.goods.bean.GoodsListRep;
import cn.chenzecheng.alan.goods.bean.GoodsResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author JacksonChen
 * @date 2022/7/14 22:26
 */
@RestController
@RequestMapping("/client/test")
@RefreshScope
@Slf4j
public class TestController {

    @Value("${testkey:default}")
    private String testkey;

    @Resource
    private RemoteAccountApi remoteAccountApi;

    @Resource
    private RemoteGoodsApi remoteGoodsApi;

    @GetMapping("/value")
    public String value() {
        System.out.println("value : " + testkey);
        return testkey;
    }

    @GetMapping("/account")
    public MyPageResult<AccountResp> feign(int size) {
        AccountListRep rep = new AccountListRep();
        rep.setSize(size);
        MyPageResult<AccountResp> result = remoteAccountApi.list(rep);
        List<AccountResp> data = result.getData();
        for (AccountResp account : data) {
            MyResult<AccountResp> detail = remoteAccountApi.detail(account.getAccountId());
        }
        return result;
    }

    @GetMapping("/goods")
    public MyPageResult<GoodsResp> getGoods(int size) {
        GoodsListRep rep = new GoodsListRep();
        rep.setSize(size);
        MyPageResult<GoodsResp> result = remoteGoodsApi.list(rep);
        List<GoodsResp> data = result.getData();
        for (GoodsResp goods : data) {
            MyResult<GoodsResp> detail = remoteGoodsApi.detail(goods.getGoodsId());
        }
        return result;
    }
}
