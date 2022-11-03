package cn.yong.mybatis.test;

import cn.yong.mybatis.binding.MapperRegistry;
import cn.yong.mybatis.session.SqlSession;
import cn.yong.mybatis.session.defaults.DefaultSqlSessionFactory;
import cn.yong.mybatis.test.dao.IUserDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Allen
 * @date 2022/11/3
 */
public class ApiTest01 {
    private final static Logger logger = LoggerFactory.getLogger(IUserDao.class);


    @Test
    public void test_MapperProxyFactory() {
        // 1. 注册 Mapper
        MapperRegistry registry = new MapperRegistry();
        registry.addMappers("cn.yong.mybatis.test.dao");

        // 2. 从 SqlSession 工厂获取 Session
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 4. 测试验证
        String res = userDao.queryUserName("100001");
        logger.info("测试结果：{}", res);
    }

}
