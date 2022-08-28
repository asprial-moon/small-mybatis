package cn.yong.mybatis.session.defaults;

import cn.yong.mybatis.binding.MapperRegistry;
import cn.yong.mybatis.mapping.MappedStatement;
import cn.yong.mybatis.session.Configuration;
import cn.yong.mybatis.session.SqlSession;

/**
 * 用于定义执行SQL标准，获取映射器以及将来管理事务等方面的操作。基本我们平常使用Mybatis的API接口也都是从这个几口定义的方法进行的
 * @author Allen
 * @date 2022/8/28
 */
public class DefaultSqlSession implements SqlSession {

    /**
     * 映射器注册机
     */
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        return (T) ("你被代理了！" + "\n方法：" + statement + "\n入参：" + parameter + "\n待执行SQL：" + mappedStatement.getSql());
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
