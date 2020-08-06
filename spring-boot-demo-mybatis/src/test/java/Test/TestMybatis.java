package Test;

import com.springboot.StartMybatisApplication;
import com.springboot.mapper.TestUserMapper;
import com.springboot.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.InputStream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartMybatisApplication.class)
public class TestMybatis {
    @Resource
    private TestUserMapper userMapper;

    @Test
    public void testFinAll() {
        System.out.println(userMapper.findAll());
    }



}
