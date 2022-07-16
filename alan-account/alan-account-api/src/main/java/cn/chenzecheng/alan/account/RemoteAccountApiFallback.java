package cn.chenzecheng.alan.account;

/**
 * @author JacksonChen
 * @date 2022/7/16 16:42
 */
public class RemoteAccountApiFallback implements RemoteAccountApi {

    private Throwable throwable;

    public RemoteAccountApiFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public String accountDetail() {
        return "获取账号信息异常:" + throwable.getMessage();
    }

}
