package cn.chenzecheng.alan.account;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author JacksonChen
 * @date 2022/7/16 16:42
 */
@Component
public class RemoteAccountShardingApiFallbackFactory implements FallbackFactory<RemoteAccountShardingApi> {

    @Override
    public RemoteAccountShardingApi create(Throwable throwable) {
        return new RemoteAccountShardingApiFallback(throwable);
    }
}
