package cn.chenzecheng.alan.bank;

import lombok.extern.slf4j.Slf4j;

/**
 * @author JacksonChen
 * @date 2022/7/16 16:42
 */
@Slf4j
public class RemoteBankApiFallback implements RemoteBankApi {

    private Throwable throwable;

    public RemoteBankApiFallback(Throwable throwable) {
        this.throwable = throwable;
    }


}
