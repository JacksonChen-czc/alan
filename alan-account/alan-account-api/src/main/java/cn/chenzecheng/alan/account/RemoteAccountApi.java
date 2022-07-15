package cn.chenzecheng.alan.account;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 账号接口api
 *
 * @author JacksonChen
 */
@FeignClient(name = "alan-account")
public interface RemoteAccountApi {

    /**
     * 获取账号详情
     *
     * @return
     */
    @GetMapping(value = "/account/detail")
    String accountDetail();

}