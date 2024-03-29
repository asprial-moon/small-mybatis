package cn.yong.mybatis.test;

import cn.hutool.json.JSONUtil;
import cn.yong.mybatis.binding.MapperRegistry;
import cn.yong.mybatis.builder.xml.XMLConfigBuilder;
import cn.yong.mybatis.datasource.pooled.PooledConnection;
import cn.yong.mybatis.datasource.pooled.PooledDataSource;
import cn.yong.mybatis.datasource.unpooled.UnpooledDataSource;
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
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Allen
 * @date 2022/8/28
 */

public class ApiTest {

    private static final Logger log = LoggerFactory.getLogger(ApiTest.class);
/*
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
        for (int i = 0; i < 50; i++) {
            User user = userDao.queryUserInfoById(1L);
            log.info("测试结果：{}", JSON.toJSONString(user));
        }
    }

    @Test
    public void test_pooled() throws SQLException, InterruptedException {
        UnpooledDataSource dataSource = new UnpooledDataSource();
        dataSource.setDriver("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://rm-wz943nx257hyn554v2o.mysql.rds.aliyuncs.com/mybatis?useUnicode=true");
        dataSource.setUsername("root");
        dataSource.setPassword("Sunnyday1711");

        while (true) {
            Connection connection = dataSource.getConnection();
            System.out.println(connection);
            Thread.sleep(1000);
            connection.close();
        }
    }


    @Test
    public void test_selectOne() throws IOException {
        // 解析 XML
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        Configuration configuration = xmlConfigBuilder.parse();

        // 获取 DefaultSqlSession
//        SqlSession sqlSession = new DefaultSqlSession(configuration);
//        // 执行查询：默认是一个集合参数
//
//        Object[] req = {1L};
//        Object res = sqlSession.selectOne("cn.yong.mybatis.test.dao.IUserDao.queryUserInfoById", req);
//        log.info("测试结果：{}", JSON.toJSONString(res));
    }


    @Test
    public void test_SqlSessionFactory_07() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3. 测试验证
        User user = userDao.queryUserInfoById(1L);
        log.info("测试结果：{}", JSONUtil.toJsonStr(user));
    }

    @Test
    public void test_SqlSessionFactory_08() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3. 测试验证
        User user = userDao.queryUserInfoById(1L);
        log.info("测试结果：{}", JSON.toJSONString(user));
    }*/

    // ==========================第十章===============================================
    private SqlSession sqlSession;

    @Before
    public void init() throws IOException {
        // 1.从SqlSessionFactory中获取SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void test_queryUserInfoById() {
        // 1.获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 2.测试验证：基本参数
        User user = userDao.queryUserInfoById(1L);
        log.info("测试结果：{}", JSONUtil.toJsonStr(user));
    }

    @Test
    public void test_queryUserInfo() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 2. 测试验证：对象参数
        User user = userDao.queryUserInfo(new User(1L, "10001"));
        log.info("测试结果：{}", JSON.toJSONString(user));
    }

    // ==========================第十一章===============================================
    @Test
    public void test_queryUserInfoById_11() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        // 2. 测试验证：基本参数
        User user = userDao.queryUserInfoById(1L);
        log.info("测试结果：{}", JSON.toJSONString(user));
    }

    // ==========================第十二章===============================================
    @Test
    public void test_insertUserInfo() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 2. 测试验证
        User user = new User();
        user.setUserId("10002");
        user.setUserName("小白");
        user.setUserHead("1_05");
        userDao.insertUserInfo(user);
        log.info("测试结果：{}", "Insert OK");

        // 3. 提交事务
        sqlSession.commit();
    }

    @Test
    public void test_queryUserInfoList() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        // 2. 测试验证：对象参数
        List<User> users = userDao.queryUserInfoList();
        log.info("测试结果：{}", JSON.toJSONString(users));
    }

    @Test
    public void test_updateUserInfo() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        // 2. 测试验证
        int count = userDao.updateUserInfo(new User(1L, "10001", "叮当猫"));
        log.info("测试结果：{}", count);
        // 3. 提交事务
        sqlSession.commit();
    }

    @Test
    public void test_deleteUserInfoByUserId() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        // 2. 测试验证
        int count = userDao.deleteUserInfoByUserId("10002");
        log.info("测试结果：{}", count == 1);
        // 3. 提交事务
        sqlSession.commit();
    }

    // ==========================第十三章===============================================
    @Test
    public void test_insertUserInfo_13() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 2. 测试验证
        User user = new User();
        user.setUserId("10002");
        user.setUserName("小白");
        user.setUserHead("1_05");
        userDao.insertUserInfo(user);
        log.info("测试结果：{}", "Insert OK");

        // 3. 提交事务
        sqlSession.commit();
    }

    @Test
    public void test_deleteUserInfoByUserId_13() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 2. 测试验证
        int count = userDao.deleteUserInfoByUserId("10002");
        log.info("测试结果：{}", count == 1);

        // 3. 提交事务
        sqlSession.commit();
    }

    @Test
    public void test_updateUserInfo_13() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 2. 测试验证
        int count = userDao.updateUserInfo(new User(1L, "10001", "叮当猫"));
        log.info("测试结果：{}", count);

        // 3. 提交事务
        sqlSession.commit();
    }

    @Test
    public void test_queryUserInfoById_13() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 2. 测试验证：基本参数
        User user = userDao.queryUserInfoById(1L);
        log.info("测试结果：{}", JSON.toJSONString(user));
    }

    @Test
    public void test_queryUserInfo_13() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 2. 测试验证：对象参数
        User user = userDao.queryUserInfo(new User(1L));
        log.info("测试结果：{}", JSON.toJSONString(user));
    }

    @Test
    public void test_queryUserInfoList_13() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 2. 测试验证：对象参数
        List<User> users = userDao.queryUserInfoList();
        log.info("测试结果：{}", JSON.toJSONString(users));
    }
}
