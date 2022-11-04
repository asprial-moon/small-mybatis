package cn.yong.mybatis.session.defaults;

import cn.yong.mybatis.session.Configuration;
import cn.yong.mybatis.session.SqlSession;
import cn.yong.mybatis.session.SqlSessionFactory;

/**
 * @author Allen
 * @desc 默认的 DefaultSqlSessionFactory
 * @date 2022/11/3
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
