import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import springbootyml.StartYmlModule;
import springbootyml.testClass.TestConfigurationProperties;

import javax.annotation.Resource;

@SpringBootTest(classes = StartYmlModule.class)
@RunWith(SpringRunner.class)
public class Test {

    @Resource
    private TestConfigurationProperties testConfigurationProperties;
    @org.junit.Test
    public void testConfigurationProperties() {
        System.out.println(testConfigurationProperties);
    }
}
