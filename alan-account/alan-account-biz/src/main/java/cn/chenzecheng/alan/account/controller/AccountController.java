package cn.chenzecheng.alan.account.controller;

import cn.chenzecheng.alan.account.bean.AccountListReq;
import cn.chenzecheng.alan.account.bean.AccountResp;
import cn.chenzecheng.alan.account.entity.Account;
import cn.chenzecheng.alan.account.service.IAccountService;
import cn.chenzecheng.alan.common.bean.MyPageInfo;
import cn.chenzecheng.alan.common.bean.MyPageResult;
import cn.chenzecheng.alan.common.bean.MyResult;
import cn.chenzecheng.alan.common.enums.MyErrorEnum;
import cn.chenzecheng.alan.common.exception.MyAssertUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
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
@RequestMapping("/account")
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
    public MyPageResult<AccountResp> list(@RequestBody AccountListReq rep) {
//        RandomExceptionUtil.randomExAndSleep();
        LambdaQueryWrapper<Account> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(rep.getName())) {
            wrapper.eq(Account::getAccountName, rep.getName());
        }
        if (StringUtils.isNotBlank(rep.getNo())) {
            wrapper.eq(Account::getAccountNo, rep.getNo());
        }
        if (StringUtils.isNotBlank(rep.getMobile())) {
            wrapper.eq(Account::getAccountMobile, rep.getMobile());
        }
        Page<Account> page = new Page<>(rep.getPageNo(), rep.getSize());
        Page<Account> pageResult = accountService.page(page, wrapper);
        MyPageInfo pageInfo = new MyPageInfo(pageResult.getCurrent(), pageResult.getSize(), pageResult.getPages(), pageResult.getTotal());
        List<AccountResp> accountRespList = BeanUtil.copyToList(pageResult.getRecords(), AccountResp.class, CopyOptions.create());
        return MyPageResult.ok(accountRespList, pageInfo);
    }
}
