package cn.yong.mybatis.session;

import cn.yong.mybatis.binding.MapperRegistry;
import cn.yong.mybatis.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;

/**
 * 映射器注册机是我们上一章节实现的内容，用于注册 Mapper 映射器锁提供的操作类。
 * 另外一个 MappedStatement 是本章节新添加的 SQL 信息记录对象，包括记录：SQL类型、SQL语句、入参类型、出参类型等
 * @author Allen
 * @date 2022/8/28
 */
public class Configuration {
    /**
     * 映射注册机
     */
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    /**
     * 映射的语句，存在Map里
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    public <T> void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }
}
