package cn.chenzecheng.alan.bank.service.impl;

import cn.chenzecheng.alan.bank.entity.BankAccount;
import cn.chenzecheng.alan.bank.mapper.BankAccountMapper;
import cn.chenzecheng.alan.bank.service.IBankAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author JacksonChen
 * @since 2022-07-21
 */
@Service
public class BankAccountServiceImpl extends ServiceImpl<BankAccountMapper, BankAccount> implements IBankAccountService {

}
