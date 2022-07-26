package cn.chenzecheng.alan.goods;

import cn.chenzecheng.alan.common.bean.MyPageResult;
import cn.chenzecheng.alan.common.bean.MyResult;
import cn.chenzecheng.alan.goods.bean.GoodsListRep;
import cn.chenzecheng.alan.goods.bean.GoodsResp;
import cn.chenzecheng.alan.goods.bean.StockResp;
import cn.chenzecheng.alan.goods.bean.TryReduceStockReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 账号接口api
 *
 * @author JacksonChen
 */
@FeignClient(name = "alan-goods", fallbackFactory = RemoteGoodsApiFallbackFactory.class)
public interface RemoteGoodsApi {

    /**
     * 获取详情
     *
     * @param id
     * @return
     */
    @GetMapping("/goods/detail/{id}")
    MyResult<GoodsResp> detail(@PathVariable(value = "id") Long id);

    /**
     * 获取列表
     *
     * @param rep
     * @return
     */
    @PostMapping("/goods/list")
    MyPageResult<GoodsResp> list(@RequestBody GoodsListRep rep);

    /**
     * 尝试减少
     *
     * @param req
     * @return
     */
    @PostMapping("/goods/stock/try-reduce")
    MyResult<Boolean> tryReduceStock(@RequestBody TryReduceStockReq req);

    /**
     * 查询商品库存信息
     *
     * @param goodsId
     * @return
     */
    @PostMapping("/goods/stock/{goodsId}")
    MyResult<StockResp> queryStock(@PathVariable(value = "goodsId") Long goodsId);
}