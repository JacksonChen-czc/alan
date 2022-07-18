package cn.chenzecheng.alan.mockclient.controller;

import cn.chenzecheng.alan.account.RemoteAccountApi;
import cn.chenzecheng.alan.account.bean.AccountListRep;
import cn.chenzecheng.alan.account.bean.AccountResp;
import cn.chenzecheng.alan.common.bean.MyPageResult;
import cn.chenzecheng.alan.common.bean.MyResult;
import com.alibaba.fastjson.JSON;
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
        log.info("detail:{}", JSON.toJSONString(result));
        return result;
    }
}
