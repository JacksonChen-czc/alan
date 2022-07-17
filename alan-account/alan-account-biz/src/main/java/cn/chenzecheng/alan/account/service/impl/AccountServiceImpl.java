package cn.chenzecheng.alan.account.service.impl;

import cn.chenzecheng.alan.account.entity.Account;
import cn.chenzecheng.alan.account.mapper.AccountMapper;
import cn.chenzecheng.alan.account.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author JacksonChen
 * @since 2022-07-17
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

}
