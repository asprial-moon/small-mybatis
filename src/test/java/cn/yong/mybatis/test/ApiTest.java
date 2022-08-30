package cn.yong.mybatis.test;

import cn.yong.mybatis.binding.MapperRegistry;
import cn.yong.mybatis.builder.xml.XMLConfigBuilder;
import cn.yong.mybatis.io.Resources;
import cn.yong.mybatis.session.Configuration;
import cn.yong.mybatis.session.SqlSession;
import cn.yong.mybatis.session.SqlSessionFactory;
import cn.yong.mybatis.session.SqlSessionFactoryBuilder;
import cn.yong.mybatis.session.defaults.DefaultSqlSession;
import cn.yong.mybatis.session.defaults.DefaultSqlSessionFactory;
import cn.yong.mybatis.test.dao.IUserDao;
import cn.yong.mybatis.test.po.User;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;

/**
 * @author Allen
 * @date 2022/8/28
 */

public class ApiTest {
    
    private static final Logger log = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_MapperProxyFactory() {
//        // 1.注册Mapper
//        MapperRegistry registry = new MapperRegistry();
//        registry.addMappers("cn.yong.mybatis.test.dao");
//
//        // 2.从 sqlSession 工厂中获取 Session
//        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        // 3.获取映射对象
//        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
//        String res = userDao.queryUserName("100001");
//        log.info("测试结果：{}", res);
    }

//    @Test
//    public void test_SqlSessionFactory() throws IOException {
//        // 1.从SqlSessionFactory中获取SqlSession
//        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        // 2.获取映射器对象
//        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
//
//        // 3.测试验证
////        String res = userDao.queryUserInfoById("10001");
////        log.info("测试结果：{}", res);
//    }

    @Test
    public void test_SqlSessionFactory() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3. 测试验证
        User user = userDao.queryUserInfoById(1L);
        log.info("测试结果：{}", JSON.toJSONString(user));
    }

    @Test
    public void test_selectOne() throws IOException {
        // 解析 XML
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        Configuration configuration = xmlConfigBuilder.parse();

        // 获取 DefaultSqlSession
        SqlSession sqlSession = new DefaultSqlSession(configuration);
        // 执行查询：默认是一个集合参数

        Object[] req = {1L};
        Object res = sqlSession.selectOne("cn.yong.mybatis.test.dao.IUserDao.queryUserInfoById", req);
        log.info("测试结果：{}", JSON.toJSONString(res));
    }

}
