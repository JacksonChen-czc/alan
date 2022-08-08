package cn.chenzecheng.alan.account.sharding.controller;

import cn.chenzecheng.alan.account.bean.AccountListReq;
import cn.chenzecheng.alan.account.bean.AccountResp;
import cn.chenzecheng.alan.account.sharding.entity.Account;
import cn.chenzecheng.alan.account.sharding.service.IAccountService;
import cn.chenzecheng.alan.common.bean.MyPageInfo;
import cn.chenzecheng.alan.common.bean.MyPageResult;
import cn.chenzecheng.alan.common.bean.MyResult;
import cn.chenzecheng.alan.common.enums.MyErrorEnum;
import cn.chenzecheng.alan.common.exception.MyAssertUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/account/sharding")
public class AccountController {

    @Resource
    private IAccountService accountService;

    @GetMapping("/detail/{id}")
    public MyResult<AccountResp> detail(@PathVariable Long id) {
//        RandomExceptionUtil.randomExAndSleep();
        Account byId = accountService.getById(id);
        MyAssertUtil.notNull(byId, MyErrorEnum.NOT_EXIST);
        return MyResult.ok(BeanUtil.copyProperties(byId, AccountResp.class));
    }

    @PostMapping("/list")
    public MyPageResult<AccountResp> list(@RequestBody AccountListReq req) {
//        RandomExceptionUtil.randomExAndSleep();
        LambdaQueryWrapper<Account> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(req.getName())) {
            wrapper.eq(Account::getAccountName, req.getName());
        }
        if (StringUtils.isNotBlank(req.getNo())) {
            wrapper.eq(Account::getAccountNo, req.getNo());
        }
        if (StringUtils.isNotBlank(req.getMobile())) {
            wrapper.eq(Account::getAccountMobile, req.getMobile());
        }
        if (CollUtil.isNotEmpty(req.getAccountIds())) {
            wrapper.in(Account::getAccountId, req.getAccountIds());
        }
        Page<Account> page = new Page<>(req.getPageNo(), req.getSize());
        Page<Account> pageResult = accountService.page(page, wrapper);
        MyPageInfo pageInfo = new MyPageInfo(pageResult.getCurrent(), pageResult.getSize(), pageResult.getPages(), pageResult.getTotal());
        List<AccountResp> accountRespList = BeanUtil.copyToList(pageResult.getRecords(), AccountResp.class, CopyOptions.create());
        return MyPageResult.ok(accountRespList, pageInfo);
    }
}
