package cn.chenzecheng.alan.goods;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 账号接口api
 *
 * @author JacksonChen
 */
@FeignClient(name = "alan-bank", fallbackFactory = RemoteBankApiFallbackFactory.class)
public interface RemoteBankApi {


}