package cn.chenzecheng.alan.mockclient.service.impl;

import cn.chenzecheng.alan.mockclient.service.MockClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author JacksonChen
 * @date 2022/7/28 16:39
 */
@RunWith(SpringRunner.class)
public class MockClientServiceTest {

    @Resource
    private MockClientService mockClientService;

    @Test
    public void secKill() {
        boolean b = mockClientService.secKill(100);
    }


}