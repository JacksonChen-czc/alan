package cn.chenzecheng.alan.sharding.service.impl;

import cn.chenzecheng.alan.sharding.entity.Account;
import cn.chenzecheng.alan.sharding.mapper.AccountMapper;
import cn.chenzecheng.alan.sharding.service.IAccountService;
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
