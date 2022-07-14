package cn.chenzecheng.alan.mockclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JacksonChen
 * @date 2022/7/14 22:26
 */
@RestController
@RequestMapping("/test")
public class Test {

    @Value("${testkey:default}")
    private String testkey;

    @GetMapping
    public String test() {
        System.out.println("value : " + testkey);
        return testkey;
    }
}
