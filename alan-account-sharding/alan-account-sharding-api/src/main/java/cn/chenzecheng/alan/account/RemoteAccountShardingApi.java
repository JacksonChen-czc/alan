package cn.chenzecheng.alan.account;

import cn.chenzecheng.alan.account.bean.AccountShardingListRep;
import cn.chenzecheng.alan.account.bean.AccountShardingResp;
import cn.chenzecheng.alan.common.bean.MyPageResult;
import cn.chenzecheng.alan.common.bean.MyResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 账号接口api
 *
 * @author JacksonChen
 */
@FeignClient(name = "alan-account-sharding", fallbackFactory = RemoteAccountShardingApiFallbackFactory.class)
public interface RemoteAccountShardingApi {

    /**
     * 获取账号详情
     *
     * @param id
     * @return
     */
    @GetMapping("/account/sharding/detail/{id}")
    MyResult<AccountShardingResp> detail(@PathVariable(value = "id") Long id);

    /**
     * 获取账号列表
     *
     * @param rep
     * @return
     */
    @PostMapping("/account/sharding/list")
    MyPageResult<AccountShardingResp> list(@RequestBody AccountShardingListRep rep);
}