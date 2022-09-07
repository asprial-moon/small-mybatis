package cn.yong.mybatis.test;

import cn.yong.mybatis.io.Resources;
import cn.yong.mybatis.session.SqlSession;
import cn.yong.mybatis.session.SqlSessionFactory;
import cn.yong.mybatis.session.SqlSessionFactoryBuilder;
import cn.yong.mybatis.test.dao.IActivityDao;
import cn.yong.mybatis.test.po.Activity;
import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Line
 * @desc
 * @date 2022/9/6
 */
public class ApiTest2 {
    private Logger log = LoggerFactory.getLogger(ApiTest2.class);

    private SqlSession sqlSession;

    @Before
    public void init() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void test_queryActivityById(){
        // 1. 获取映射器对象
        IActivityDao dao = sqlSession.getMapper(IActivityDao.class);
        // 2. 测试验证
//        Activity res = dao.queryActivityById(100001L);
//        log.info("测试结果：{}", JSON.toJSONString(res));
    }
}
