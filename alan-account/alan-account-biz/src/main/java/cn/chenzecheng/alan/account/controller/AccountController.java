package cn.chenzecheng.alan.account.controller;

import cn.chenzecheng.alan.account.bean.AccountListRep;
import cn.chenzecheng.alan.account.bean.AccountResp;
import cn.chenzecheng.alan.account.entity.Account;
import cn.chenzecheng.alan.account.service.IAccountService;
import cn.chenzecheng.alan.common.bean.MyPageInfo;
import cn.chenzecheng.alan.common.bean.MyPageResult;
import cn.chenzecheng.alan.common.bean.MyResult;
import cn.chenzecheng.alan.common.util.RandomExceptionUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/detail/{id}")
    public MyResult<AccountResp> detail(@PathVariable String id) {
        RandomExceptionUtil.randomExAndSleep();
        Account byId = accountService.getById(id);
        Assert.notNull(byId, "查询不到账号信息id：%s", id);
        return MyResult.ok(BeanUtil.copyProperties(byId, AccountResp.class));
    }

    @GetMapping("/list")
    public MyPageResult<AccountResp> list(AccountListRep rep) {
        RandomExceptionUtil.randomExAndSleep();
        LambdaQueryWrapper<Account> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Account::getAccountName, rep.getName());
        wrapper.eq(Account::getAccountNo, rep.getNo());
        wrapper.eq(Account::getAccountMobile, rep.getMobile());
        Page<Account> page = new Page<>(rep.getPageNo(), rep.getSize());
        // fixme 没有执行查询？
        accountService.page(page, wrapper);
        MyPageInfo pageInfo = new MyPageInfo(page.getCurrent(), page.getSize(), page.getPages(), page.getTotal());
        List<AccountResp> accountRespList = BeanUtil.copyToList(page.getRecords(), AccountResp.class, CopyOptions.create());
        return MyPageResult.ok(accountRespList, pageInfo);
    }
}
