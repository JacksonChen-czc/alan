package cn.chenzecheng.alan.mockclient.service;

import org.springframework.stereotype.Service;

/**
 * 模拟客户端接口
 *
 * @author JacksonChen
 * @date 2022/7/25 16:53
 */
@Service
public interface MockClientService {

    /**
     * 模拟秒杀场景
     *
     * @param currentNum
     */
    boolean secKill(int currentNum);

    boolean pay(int currentNum);
}
