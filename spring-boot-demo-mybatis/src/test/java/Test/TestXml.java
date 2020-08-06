package Test;

import com.springboot.StartMybatisApplication;
import com.springboot.mapper.TestUserMapper;
import com.springboot.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = {StartMybatisApplication.class})
@RunWith(SpringRunner.class)
public class TestXml {
    @Resource
    private TestUserMapper testUserMapper;

    @Test
    public void testXml() {
        System.out.println(testUserMapper.findAll());
    }
}
