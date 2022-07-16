package cn.chenzecheng.alan.mockclient.controller;

import cn.chenzecheng.alan.account.RemoteAccountApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author JacksonChen
 * @date 2022/7/14 22:26
 */
@RestController
@RequestMapping("/client/test")
@RefreshScope
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

    @GetMapping("/feign")
    public String feign() {
        String s = remoteAccountApi.accountDetail();
        System.out.println(s);
        return s;
    }
}
