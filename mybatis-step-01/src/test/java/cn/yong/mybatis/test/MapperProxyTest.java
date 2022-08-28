package cn.yong.mybatis.test;

import cn.yong.mybatis.binding.MapperProxyFactory;
import cn.yong.mybatis.test.dao.IUserDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Allen
 * @date 2022/8/28
 */
@Slf4j
public class MapperProxyTest {

    @Test
    public void test_MapperProxyFactory() {
        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);
        Map<String, String> sqlSession = new HashMap<>();
        sqlSession.put("cn.yong.mybatis.test.dao.IUserDao.queryUserName", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名");
        sqlSession.put("cn.yong.mybatis.test.dao.IUserDao.queryUserAge", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户年龄");

        IUserDao userDao = factory.newInstance(sqlSession);
        String res = userDao.queryUserAge("100001");

        log.info("测试结果：{}", res);
    }

}
