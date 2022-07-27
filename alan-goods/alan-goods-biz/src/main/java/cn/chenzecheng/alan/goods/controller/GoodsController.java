package cn.chenzecheng.alan.goods.controller;

import cn.chenzecheng.alan.common.bean.MyPageInfo;
import cn.chenzecheng.alan.common.bean.MyPageResult;
import cn.chenzecheng.alan.common.bean.MyResult;
import cn.chenzecheng.alan.common.enums.MyErrorEnum;
import cn.chenzecheng.alan.common.exception.MyAssertUtil;
import cn.chenzecheng.alan.common.util.RedissonUtil;
import cn.chenzecheng.alan.goods.bean.GoodsListRep;
import cn.chenzecheng.alan.goods.bean.GoodsResp;
import cn.chenzecheng.alan.goods.bean.StockResp;
import cn.chenzecheng.alan.goods.bean.TryReduceStockReq;
import cn.chenzecheng.alan.goods.entity.Goods;
import cn.chenzecheng.alan.goods.entity.Stock;
import cn.chenzecheng.alan.goods.service.IGoodsService;
import cn.chenzecheng.alan.goods.service.IStockService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author JacksonChen
 * @since 2022-07-19
 */
@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

    @Resource
    private IGoodsService goodsService;
    @Resource
    private IStockService stockService;

    @Resource
    private RedissonUtil redissonUtil;

    @GetMapping("/detail/{id}")
    public MyResult<GoodsResp> detail(@PathVariable Long id) {
        Goods goods = goodsService.getById(id);
        MyAssertUtil.notNull(goods, MyErrorEnum.NOT_EXIST);
        Stock stock = stockService.getById(goods.getGoodsId());
        MyAssertUtil.notNull(goods, MyErrorEnum.NOT_EXIST);
        GoodsResp resp = new GoodsResp();
        BeanUtils.copyProperties(goods, resp);
        resp.setSaleNum(stock.getSaleNum());
        resp.setTotal(stock.getTotal());
        return MyResult.ok(resp);
    }

    @PostMapping("/list")
    public MyPageResult<GoodsResp> list(@RequestBody GoodsListRep req) {
        IPage<Goods> page = new Page<>(req.getPageNo(), req.getSize());
        IPage<Goods> result = goodsService.page(page);
        List<GoodsResp> goodsResps = BeanUtil.copyToList(result.getRecords(), GoodsResp.class, CopyOptions.create());
        goodsResps.forEach(goodsResp -> {
            Stock stock = stockService.getById(goodsResp.getGoodsId());
            goodsResp.setTotal(stock.getTotal());
            goodsResp.setSaleNum(stock.getSaleNum());
        });
        return MyPageResult.ok(goodsResps, new MyPageInfo(result.getCurrent(), result.getSize(), result.getPages(), result.getTotal()));
    }

    @PostMapping("/stock/{goodsId}")
    public MyResult<StockResp> queryStock(@PathVariable Long goodsId) {
        Stock stock = stockService.getById(goodsId);
        return MyResult.ok(Convert.convert(StockResp.class, stock));
    }

    @PostMapping("/stock/try-reduce")
    public MyResult<Boolean> tryReduceStock(@RequestBody TryReduceStockReq req) {
        // 线程安全地扣减库存
        RLock lock = redissonUtil.getRedisLock(req.getGoodsId().toString());
        lock.lock();
        try {
            Stock stock = stockService.getById(req.getGoodsId());
            if (stock.getSaleNum() >= req.getNum()) {
                stock.setSaleNum(stock.getSaleNum() - req.getNum());
                stockService.updateById(stock);
                log.info("扣减存库[{}]成功", req.getNum());
                return MyResult.ok(true);
            }
        } finally {
            lock.unlock();
        }
        log.info("扣减存库[{}]失败", req.getNum());
        return MyResult.ok(false);
    }
}
