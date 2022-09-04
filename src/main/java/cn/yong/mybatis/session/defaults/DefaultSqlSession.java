package cn.yong.mybatis.session.defaults;

import cn.yong.mybatis.executor.Executor;
import cn.yong.mybatis.mapping.MappedStatement;
import cn.yong.mybatis.session.Configuration;
import cn.yong.mybatis.session.RowBounds;
import cn.yong.mybatis.session.SqlSession;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 默认SqlSession实现类
 * 用于定义执行SQL标准，获取映射器以及将来管理事务等方面的操作。基本我们平常使用Mybatis的API接口也都是从这个几口定义的方法进行的
 * @author Allen
 * @date 2022/8/28
 */
public class DefaultSqlSession implements SqlSession {
    private Logger log = LoggerFactory.getLogger(DefaultSqlSession.class);
    /**
     * 映射器注册机
     */
    private Configuration configuration;

    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        log.info("执行查询 statement：{} parameter：{}", statement, JSON.toJSONString(parameter));
        MappedStatement ms = configuration.getMappedStatement(statement);
        List<T> list = executor.query(ms, parameter, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER, ms.getSqlSource().getBoundSql(parameter));
        return list.get(0);
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
