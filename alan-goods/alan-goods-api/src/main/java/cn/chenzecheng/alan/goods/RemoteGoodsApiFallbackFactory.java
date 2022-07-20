package cn.chenzecheng.alan.goods;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author JacksonChen
 * @date 2022/7/16 16:42
 */
@Component
public class RemoteGoodsApiFallbackFactory implements FallbackFactory<RemoteGoodsApi> {

    @Override
    public RemoteGoodsApi create(Throwable throwable) {
        return new RemoteGoodsApiFallback(throwable);
    }
}
