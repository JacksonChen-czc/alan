package cn.chenzecheng.alan.goods;

import cn.chenzecheng.alan.common.bean.MyPageResult;
import cn.chenzecheng.alan.common.bean.MyResult;
import cn.chenzecheng.alan.goods.bean.GoodsListRep;
import cn.chenzecheng.alan.goods.bean.GoodsResp;
import lombok.extern.slf4j.Slf4j;

/**
 * @author JacksonChen
 * @date 2022/7/16 16:42
 */
@Slf4j
public class RemoteGoodsApiFallback implements RemoteGoodsApi {

    private Throwable throwable;

    public RemoteGoodsApiFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public MyResult<GoodsResp> detail(Long id) {
        log.error("获取商品详情异常", throwable);
        return MyResult.error("获取商品详情异常");
    }

    @Override
    public MyPageResult<GoodsResp> list(GoodsListRep rep) {
        log.error("获取商品列表异常", throwable);
        return MyPageResult.error("获取商品列表异常");
    }
}
