package cn.chenzecheng.alan.bank;

import cn.chenzecheng.alan.account.RemoteAccountApi;
import cn.chenzecheng.alan.account.bean.AccountListRep;
import cn.chenzecheng.alan.account.bean.AccountResp;
import cn.chenzecheng.alan.bank.entity.BankAccount;
import cn.chenzecheng.alan.bank.service.IBankAccountService;
import cn.chenzecheng.alan.common.bean.MyPageResult;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 数据库数据初始化
 *
 * @author JacksonChen
 * @date 2022/7/19 22:03
 */
@SpringBootTest
public class BankDbInit {

    @Resource
    private RemoteAccountApi remoteAccountApi;

    @Resource
    private IBankAccountService bankAccountService;

    Random random = new Random();

    /**
     * 最多同行运行32 + 1（当前线程） + 10（等待）
     * 目前瓶颈在于网络上传速度
     */
    private ExecutorService threadPool = new ThreadPoolExecutor(16, 32, 30, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(10), new ThreadPoolExecutor.CallerRunsPolicy());

    @Test
    public void insert() {

    }

    @Test
    public void insertBatch() {
        long start = System.currentTimeMillis();
        // 给所有账号初始化一个金额
        int page = 1;
        boolean hasNext = true;
        AccountListRep req = new AccountListRep();
        int pageSize = 10000;
        req.setSize(pageSize);

        while (hasNext) {
            req.setPageNo(page);
            MyPageResult<AccountResp> accountResult = remoteAccountApi.list(req);
            if (CollUtil.isNotEmpty(accountResult.getData())) {
                int finalPage = page;
                threadPool.submit(() -> {
                    System.out.println("线程开始处理第" + finalPage + "页数据");
                    long start1 = System.currentTimeMillis();
                    List<BankAccount> list = new ArrayList<>(pageSize);
                    accountResult.getData().forEach(account -> {
                        BankAccount bankAccount = new BankAccount();
                        bankAccount.setAccountId(account.getAccountId());
                        // 5000-55000
                        bankAccount.setBanlance(new BigDecimal(random.nextInt(50000) + 5000));
                        list.add(bankAccount);
                    });
                    boolean saveBatch = bankAccountService.saveBatch(list);
                    Assert.isTrue(saveBatch);
                    long end1 = System.currentTimeMillis();
                    System.out.println("第【" + finalPage + "】页账号余额插入完成，数量：" + pageSize + "，耗时：" + (end1 - start1));
                });
            }
            page++;
            hasNext = accountResult.getPage().hasNext();
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
}
