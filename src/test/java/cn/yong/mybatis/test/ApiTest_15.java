package cn.yong.mybatis.test;

import cn.yong.mybatis.io.Resources;
import cn.yong.mybatis.session.SqlSession;
import cn.yong.mybatis.session.SqlSessionFactory;
import cn.yong.mybatis.session.SqlSessionFactoryBuilder;
import cn.yong.mybatis.test.dao.IActivityDao;
import cn.yong.mybatis.test.po.Activity;
import com.alibaba.fastjson.JSON;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Line
 * @desc
 * @date 2022/9/7
 */
public class ApiTest_15 {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_queryActivityById() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 2. 获取映射器对象
        IActivityDao dao = sqlSession.getMapper(IActivityDao.class);
        // 3. 测试验证
        Activity req = new Activity();
        req.setActivityId(100001L);
        Activity res = dao.queryActivityById(req);
        logger.info("测试结果：{}", JSON.toJSONString(res));
    }

    @Test
    public void test_ognl() throws OgnlException {
        Activity req = new Activity();
        req.setActivityId(1L);
        req.setActivityName("测试活动");
        req.setActivityDesc("小傅哥的测试内容");

        OgnlContext context = new OgnlContext();
        context.setRoot(req);
        Object root = context.getRoot();

        Object activityName = Ognl.getValue("activityName", context, root);
        Object activityDesc = Ognl.getValue("activityDesc", context, root);
        Object value = Ognl.getValue("activityDesc.length()", context, root);

        System.out.println(activityName + "\t" + activityDesc + " length：" + value);
    }


}
