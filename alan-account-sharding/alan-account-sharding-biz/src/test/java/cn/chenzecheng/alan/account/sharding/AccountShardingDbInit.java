package cn.chenzecheng.alan.account.sharding;

import cn.chenzecheng.alan.account.sharding.entity.Account;
import cn.chenzecheng.alan.account.sharding.service.IAccountService;
import cn.chenzecheng.alan.account.sharding.util.CreatAccountUtil;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 初始化数据库数据
 *
 * @author chenzc
 * @date 2022/7/18 14:16
 */
@SpringBootTest
public class AccountShardingDbInit {

    @Resource
    private IAccountService accountService;

    private final ExecutorService threadPool = new ThreadPoolExecutor(1, 2, 10L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1), new ThreadPoolExecutor.CallerRunsPolicy());
//    private final ExecutorService threadPool = new ThreadPoolExecutor(32, 64, 10L, TimeUnit.MILLISECONDS,
//            new LinkedBlockingQueue<Runnable>(1024), new ThreadPoolExecutor.CallerRunsPolicy());

    @Test
    public void insert() {
        Account account = createAccount();
        boolean save = accountService.save(account);
        Assert.isTrue(save, "插入失败");
    }

    @Test
    public void batchInsertAccount() {
        // 生成1000w的账号，可以根据性能自助调整线程数量和每次生成的数量
        /* 数据库连接增加 rewriteBatchedStatements=true 参数，可以大量提升批量插入的效率，*/
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            int finalI = i;
            threadPool.submit(() -> {
                long start1 = System.currentTimeMillis();
                int size = 10000;
                List<Account> list = new ArrayList<>(size);
                for (int j = 0; j < size; j++) {
                    Account account = createAccount();
                    list.add(account);
                }
                accountService.saveBatch(list);
                long end1 = System.currentTimeMillis();
                System.out.println("第【" + finalI + "】批用户插入完成，数量：" + size + "，耗时：" + (end1 - start1));
            });
        }
        threadPool.shutdown();
        while (!threadPool.isTerminated()) {
            System.out.println("任务未完成，继续等待...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("线程池执行任务结束，耗时：" + (end - start));
    }

    @NotNull
    private Account createAccount() {
        Account account = new Account();
        Map<String, String> accountInfo = CreatAccountUtil.getAccount();
        account.setAccountNo(accountInfo.get(CreatAccountUtil.EMAIL));
        account.setAccountName(accountInfo.get(CreatAccountUtil.NAME));
        account.setAccountMobile(accountInfo.get(CreatAccountUtil.TEL));
        account.setAccountGender((byte) (CreatAccountUtil.MAN.equals(accountInfo.get(CreatAccountUtil.SEX)) ? 1 : 2));
        return account;
    }
}
