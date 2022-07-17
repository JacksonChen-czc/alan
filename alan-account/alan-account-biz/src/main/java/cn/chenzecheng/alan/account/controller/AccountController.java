package cn.chenzecheng.alan.account.controller;

import cn.chenzecheng.alan.account.entity.Account;
import cn.chenzecheng.alan.account.service.IAccountService;
import cn.chenzecheng.alan.common.util.RandomExceptionUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author JacksonChen
 * @since 2022-07-17
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private IAccountService accountService;

    @GetMapping("/detail")
    public String detail() {
        RandomExceptionUtil.randomExAndSleep();
        return "asasadada";
    }

    @GetMapping("/list")
    public String list() {
        RandomExceptionUtil.randomExAndSleep();
        List<Account> list = accountService.list();
        return JSON.toJSONString(list);
    }
}
