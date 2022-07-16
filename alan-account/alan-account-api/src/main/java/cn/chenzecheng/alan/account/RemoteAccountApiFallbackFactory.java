package cn.chenzecheng.alan.account;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author JacksonChen
 * @date 2022/7/16 16:42
 */
@Component
public class RemoteAccountApiFallbackFactory implements FallbackFactory<RemoteAccountApi> {

    @Override
    public RemoteAccountApi create(Throwable throwable) {
        return new RemoteAccountApiFallback(throwable);
    }
}
