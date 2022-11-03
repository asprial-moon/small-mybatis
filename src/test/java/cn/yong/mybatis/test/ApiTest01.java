package cn.yong.mybatis.test;

import cn.yong.mybatis.binding.MapperProxyFactory;
import cn.yong.mybatis.test.dao.IUserDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Allen
 * @date 2022/11/3
 */
public class ApiTest01 {
    private final static Logger logger = LoggerFactory.getLogger(IUserDao.class);


    @Test
    public void test_MapperProxyFactory() {
        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);

        Map<String, String> sqlSession = new HashMap<>();
        sqlSession.put("cn.yong.mybatis.test.dao.IUserDao.queryUserName", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名");
        sqlSession.put("cn.yong.mybatis.test.dao.IUserDao.queryUserAge", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户年龄");

        IUserDao userDao = factory.newInstance(sqlSession);


        logger.info("测试结果Name：{}", userDao.queryUserName("10001"));
        logger.info("测试结果Age ：{}", userDao.queryUserAge("10002"));
    }

}
