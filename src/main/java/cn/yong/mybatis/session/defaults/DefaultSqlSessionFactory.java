package cn.yong.mybatis.session.defaults;

import cn.yong.mybatis.session.Configuration;
import cn.yong.mybatis.session.SqlSession;
import cn.yong.mybatis.session.SqlSessionFactory;

/**
 * 这其实就是一个简单工厂的定义，在工厂中提供接口实现类的能力，也就是 SqlSessionFactory 工厂中提供的开启 SqlSession 的能力
 * @author Allen
 * @date 2022/8/28
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
