package cn.chenzecheng.alan.goods;

import cn.chenzecheng.alan.common.bean.MyPageResult;
import cn.chenzecheng.alan.common.bean.MyResult;
import cn.chenzecheng.alan.goods.bean.GoodsListRep;
import cn.chenzecheng.alan.goods.bean.GoodsResp;
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
}