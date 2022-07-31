package cn.chenzecheng.alan.account;

import cn.chenzecheng.alan.account.bean.AccountShardingListRep;
import cn.chenzecheng.alan.account.bean.AccountShardingResp;
import cn.chenzecheng.alan.common.bean.MyPageResult;
import cn.chenzecheng.alan.common.bean.MyResult;
import lombok.extern.slf4j.Slf4j;

/**
 * @author JacksonChen
 * @date 2022/7/16 16:42
 */
@Slf4j
public class RemoteAccountShardingApiFallback implements RemoteAccountShardingApi {

    private Throwable throwable;

    public RemoteAccountShardingApiFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public MyResult<AccountShardingResp> detail(Long id) {
        log.error("获取账号详情异常", throwable);
        return MyResult.error("获取账号详情异常");
    }

    @Override
    public MyPageResult<AccountShardingResp> list(AccountShardingListRep rep) {
        log.error("获取账号列表异常", throwable);
        return MyPageResult.error("获取账号列表异常");
    }
}
