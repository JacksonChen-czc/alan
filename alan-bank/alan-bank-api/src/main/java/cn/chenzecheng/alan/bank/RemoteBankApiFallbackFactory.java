package cn.chenzecheng.alan.bank;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author JacksonChen
 * @date 2022/7/16 16:42
 */
@Component
public class RemoteBankApiFallbackFactory implements FallbackFactory<RemoteBankApi> {

    @Override
    public RemoteBankApi create(Throwable throwable) {
        return new RemoteBankApiFallback(throwable);
    }
}
