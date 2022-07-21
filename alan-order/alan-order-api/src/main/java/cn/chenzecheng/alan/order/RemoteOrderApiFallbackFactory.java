package cn.chenzecheng.alan.order;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author JacksonChen
 * @date 2022/7/16 16:42
 */
@Component
public class RemoteOrderApiFallbackFactory implements FallbackFactory<RemoteOrderApi> {

    @Override
    public RemoteOrderApi create(Throwable throwable) {
        return new RemoteOrderApiFallback(throwable);
    }
}
