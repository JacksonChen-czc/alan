package cn.chenzecheng.alan.order;

import lombok.extern.slf4j.Slf4j;

/**
 * @author JacksonChen
 * @date 2022/7/16 16:42
 */
@Slf4j
public class RemoteOrderApiFallback implements RemoteOrderApi {

    private Throwable throwable;

    public RemoteOrderApiFallback(Throwable throwable) {
        this.throwable = throwable;
    }


}
