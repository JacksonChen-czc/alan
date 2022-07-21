package cn.chenzecheng.alan.order;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 账号接口api
 *
 * @author JacksonChen
 */
@FeignClient(name = "alan-order", fallbackFactory = RemoteOrderApiFallbackFactory.class)
public interface RemoteOrderApi {


}