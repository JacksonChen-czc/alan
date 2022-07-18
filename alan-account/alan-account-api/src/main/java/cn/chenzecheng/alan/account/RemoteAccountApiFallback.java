package cn.chenzecheng.alan.account;

import cn.chenzecheng.alan.account.bean.AccountListRep;
import cn.chenzecheng.alan.account.bean.AccountResp;
import cn.chenzecheng.alan.common.bean.MyPageResult;
import cn.chenzecheng.alan.common.bean.MyResult;
import lombok.extern.slf4j.Slf4j;

/**
 * @author JacksonChen
 * @date 2022/7/16 16:42
 */
@Slf4j
public class RemoteAccountApiFallback implements RemoteAccountApi {

    private Throwable throwable;

    public RemoteAccountApiFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public MyResult<AccountResp> detail(Long id) {
        log.error("获取账号详情异常", throwable);
        return null;
    }

    @Override
    public MyPageResult<AccountResp> list(AccountListRep rep) {
        log.error("获取账号列表异常", throwable);
        return null;
    }
}
