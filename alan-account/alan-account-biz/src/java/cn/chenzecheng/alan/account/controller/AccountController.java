package cn.chenzecheng.alan.account.controller;

import cn.chenzecheng.alan.common.util.RandomExceptionUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JacksonChen
 * @date 2022/7/15 21:31
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/detail")
    public String detail() {
        RandomExceptionUtil.randomExAndSleep();
        return "asasadada";
    }
}
