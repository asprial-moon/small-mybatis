package cn.yong.mybatis.session.defaults;

import cn.yong.mybatis.binding.MapperRegistry;
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
    private MapperRegistry mapperRegistry;

    public DefaultSqlSession(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T) ("你被代理了！" + "方法：" + statement + " 入参：" + parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperRegistry.getMapper(type, this);
    }
}
