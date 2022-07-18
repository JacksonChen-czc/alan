package cn.chenzecheng.alan.account;

import cn.chenzecheng.alan.account.bean.AccountListRep;
import cn.chenzecheng.alan.account.bean.AccountResp;
import cn.chenzecheng.alan.common.bean.MyPageResult;
import cn.chenzecheng.alan.common.bean.MyResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 账号接口api
 *
 * @author JacksonChen
 */
@FeignClient(name = "alan-account", fallbackFactory = RemoteAccountApiFallbackFactory.class)
public interface RemoteAccountApi {

    /**
     * 获取账号详情
     *
     * @return
     */
    @GetMapping(value = "/account/detail")
    String accountDetail();

    @GetMapping("/detail/{id}")
    MyResult<AccountResp> detail(@PathVariable(value = "id") String id);

    @GetMapping("/list")
    MyPageResult<AccountResp> list(AccountListRep rep);
}