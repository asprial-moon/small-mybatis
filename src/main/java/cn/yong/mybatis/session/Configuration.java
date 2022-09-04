package cn.yong.mybatis.session;

import cn.yong.mybatis.executor.parameter.ParameterHandler;
import cn.yong.mybatis.reflection.MetaObject;
import cn.yong.mybatis.reflection.factory.DefaultObjectFactory;
import cn.yong.mybatis.reflection.factory.ObjectFactory;
import cn.yong.mybatis.reflection.wrapper.DefaultObjectWrapperFactory;
import cn.yong.mybatis.reflection.wrapper.ObjectWrapperFactory;
import cn.yong.mybatis.scripting.LanguageDriver;
import cn.yong.mybatis.scripting.LanguageDriverRegistry;
import cn.yong.mybatis.scripting.xmltags.XMLLanguageDriver;
import cn.yong.mybatis.type.TypeAliasRegistry;
import cn.yong.mybatis.binding.MapperRegistry;
import cn.yong.mybatis.datasource.druid.DruidDataSourceFactory;
import cn.yong.mybatis.datasource.pooled.PooledDataSourceFactory;
import cn.yong.mybatis.datasource.unpooled.UnpooledDataSourceFactory;
import cn.yong.mybatis.executor.Executor;
import cn.yong.mybatis.executor.SimpleExecutor;
import cn.yong.mybatis.executor.resultset.DefaultResultSetHandler;
import cn.yong.mybatis.executor.resultset.ResultSetHandler;
import cn.yong.mybatis.executor.statement.PreparedStatementHandler;
import cn.yong.mybatis.executor.statement.StatementHandler;
import cn.yong.mybatis.mapping.BoundSql;
import cn.yong.mybatis.mapping.Environment;
import cn.yong.mybatis.mapping.MappedStatement;
import cn.yong.mybatis.transaction.Transaction;
import cn.yong.mybatis.transaction.jdbc.JdbcTransactionFactory;
import cn.yong.mybatis.type.TypeHandlerRegistry;
import com.sun.org.apache.xpath.internal.objects.XObjectFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 映射器注册机是我们上一章节实现的内容，用于注册 Mapper 映射器锁提供的操作类。
 * 另外一个 MappedStatement 是本章节新添加的 SQL 信息记录对象，包括记录：SQL类型、SQL语句、入参类型、出参类型等
 * @author Allen
 * @date 2022/8/28
 */
public class Configuration {
    /**
     * 环境
     */
    protected Environment environment;
    /**
     * 映射注册机
     */
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);
    /**
     * 映射的语句，存在Map里
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();
    /**
     * 类型别名注册机
     */
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

    protected final LanguageDriverRegistry languageRegistry = new LanguageDriverRegistry();

    /**
     * 类型处理器注册机
     */
    protected final TypeHandlerRegistry typeHandlerRegistry = new TypeHandlerRegistry();

    /**
     * 对象工厂和对象包装器
     */
    protected ObjectFactory objectFactory = new DefaultObjectFactory();
    /**
     * 对象包装器
     */
    protected ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();

    protected final Set<String> loadedResources = new HashSet<>();

    protected String databaseId;

    /**
     * 在 Configuration 配置选项类中，添加类型别名注册机，通过构造函数添加 JDBC、DRUID 注册操作。
     */
    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
        typeAliasRegistry.registerAlias("UNPOOLED", UnpooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);

        languageRegistry.setDefaultDriverClass(XMLLanguageDriver.class);
    }

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

    public Environment getEnvironment() {
        return environment;
    }

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    /**
     * 创建结果集处理器
     * @param executor
     * @param mappedStatement
     * @param boundSql
     * @return
     */
    public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, BoundSql boundSql) {
        return new DefaultResultSetHandler(executor, mappedStatement, boundSql);
    }

    /**
     * 生产执行器
     * @param transaction
     * @return
     */
    public Executor newExecutor(Transaction transaction) {
        return new SimpleExecutor(this, transaction);
    }

    /**
     * 创建语句处理器
     * @param executor
     * @param ms
     * @param boundSql
     * @return
     */
    public StatementHandler newStatementHandler(Executor executor, MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        return new PreparedStatementHandler(executor, ms, parameter, resultHandler, boundSql);
    }

    public MetaObject newMateObject(Object object) {
        return MetaObject.forObject(object, objectFactory, objectWrapperFactory);
    }

    public TypeHandlerRegistry getTypeHandlerRegistry() {
        return typeHandlerRegistry;
    }

    public boolean isResourceLoaded(String resource) {
        return loadedResources.contains(resource);
    }

    public void addLoadedResource(String resource) {
        loadedResources.add(resource);
    }

    public LanguageDriverRegistry getLanguageRegistry() {
        return languageRegistry;
    }

    public ParameterHandler newParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        // 创建参数处理器
        ParameterHandler parameterHandler = mappedStatement.getLang().createParameterHandler(mappedStatement, parameterObject, boundSql);
        // 插件的一些参数，也是在这里处理，暂时不添加这部分内容 interceptorChain.pluginAll(parameterHandler); 、
        return parameterHandler;
    }

    public LanguageDriver getDefaultScriptingLanguageInstance() {
        return languageRegistry.getDefaultDriver();
    }

}
