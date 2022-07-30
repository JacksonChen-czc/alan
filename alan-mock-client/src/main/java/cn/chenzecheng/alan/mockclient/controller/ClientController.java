package cn.chenzecheng.alan.mockclient.controller;

import cn.chenzecheng.alan.common.bean.MyResult;
import cn.chenzecheng.alan.mockclient.service.MockClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 模拟客户端
 *
 * @author JacksonChen
 */
@RestController
@RequestMapping("/client/mock")
public class ClientController {

    @Resource
    private MockClientService mockClientService;

    /**
     * 场景一、大量用户抢购少数商品
     *
     * @param currentNum
     * @return
     */
    @GetMapping
    public MyResult<Boolean> secKill(int currentNum) {
        return MyResult.ok(mockClientService.secKill(currentNum));
    }

}
