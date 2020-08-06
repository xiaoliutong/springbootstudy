package Test;

import com.springboot.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class TestIo {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("sql.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
       UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        System.out.println(userMapper.findAll());
    }
}
