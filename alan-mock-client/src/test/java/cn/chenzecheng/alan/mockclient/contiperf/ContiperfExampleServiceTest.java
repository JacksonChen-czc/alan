package cn.chenzecheng.alan.mockclient.contiperf;

import cn.chenzecheng.alan.mockclient.AlanMockClientApplication;
import cn.chenzecheng.alan.mockclient.service.MockClientService;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 使用 contiperf 压测接口
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AlanMockClientApplication.class)
public class ContiperfExampleServiceTest {
    @Rule
    public ContiPerfRule i = new ContiPerfRule();

    @Autowired
    private MockClientService mockClientService;

    @Test
    @PerfTest(threads = 1000, invocations = 1000)
    public void findAll() {
        mockClientService.secKill(1);
    }
}