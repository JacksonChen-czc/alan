package cn.chenzecheng.alan.goods;

import cn.chenzecheng.alan.goods.entity.Goods;
import cn.chenzecheng.alan.goods.entity.Stock;
import cn.chenzecheng.alan.goods.service.IGoodsService;
import cn.chenzecheng.alan.goods.service.IStockService;
import cn.chenzecheng.alan.goods.util.CreateGoodsUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
public class GoodsDbInit {

    @Resource
    private IGoodsService iGoodsService;
    @Resource
    private IStockService stockService;

    private ExecutorService threadPool = new ThreadPoolExecutor(16, 32, 10L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), new ThreadPoolExecutor.CallerRunsPolicy());

    @Test
    public void insert() {
        Goods goods = new Goods();
        goods.setGoodsName("小米10");
        goods.setGoodsPrice(new BigDecimal("3999.99"));
        boolean saveGoods = iGoodsService.save(goods);
        Assert.isTrue(saveGoods);

        Stock stock = new Stock();
        stock.setGoodsId(goods.getGoodsId());
        stock.setSaleNum(10);
        stock.setTotal(100);
        boolean saveStock = stockService.save(stock);
        Assert.isTrue(saveStock);
    }

    @Test
    public void insertBatch() {
        // 生成100w的商品，16线程大概需要20分钟，可以根据性能自助调整
        /* 数据库连接增加 rewriteBatchedStatements=true 参数，可以大量提升批量插入的效率，*/
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(() -> {
                long start1 = System.currentTimeMillis();
                int size = 1000;
                List<Goods> goodsList = new ArrayList<>(size);
                List<Stock> stockList = new ArrayList<>(size);
                for (int j = 0; j < size; j++) {
                    Goods goods = new Goods();
                    goods.setGoodsName(CreateGoodsUtil.getGoodsName());
                    goods.setGoodsPrice(CreateGoodsUtil.getGoodsPrice());
                    goodsList.add(goods);
                }
                iGoodsService.saveBatch(goodsList);
                for (Goods goods : goodsList) {
                    Stock stock = new Stock();
                    stock.setGoodsId(goods.getGoodsId());
                    stock.setSaleNum(10);
                    stock.setTotal(100);
                    stockList.add(stock);
                }
                stockService.saveBatch(stockList);
                long end1 = System.currentTimeMillis();
                System.out.println("第【" + finalI + "】批商品插入完成，数量：" + size + "，耗时：" + (end1 - start1));
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
}
