package cn.yong.mybatis.session;

import cn.yong.mybatis.binding.MapperRegistry;
import cn.yong.mybatis.datasource.druid.DruidDataSourceFactory;
import cn.yong.mybatis.datasource.pooled.PooledDataSourceFactory;
import cn.yong.mybatis.datasource.unpooled.UnpooledDataSourceFactory;
import cn.yong.mybatis.executor.Executor;
import cn.yong.mybatis.executor.SimpleExecutor;
import cn.yong.mybatis.executor.parameter.ParameterHandler;
import cn.yong.mybatis.executor.resultset.DefaultResultSetHandler;
import cn.yong.mybatis.executor.resultset.ResultSetHandler;
import cn.yong.mybatis.executor.statement.PreparedStatementHandler;
import cn.yong.mybatis.executor.statement.StatementHandler;
import cn.yong.mybatis.mapping.BoundSql;
import cn.yong.mybatis.mapping.Environment;
import cn.yong.mybatis.mapping.MappedStatement;
import cn.yong.mybatis.reflection.MetaObject;
import cn.yong.mybatis.reflection.factory.DefaultObjectFactory;
import cn.yong.mybatis.reflection.factory.ObjectFactory;
import cn.yong.mybatis.reflection.wrapper.DefaultObjectWrapperFactory;
import cn.yong.mybatis.reflection.wrapper.ObjectWrapperFactory;
import cn.yong.mybatis.scripting.LanguageDriver;
import cn.yong.mybatis.scripting.LanguageDriverRegistry;
import cn.yong.mybatis.scripting.xmltags.XMLLanguageDriver;
import cn.yong.mybatis.transaction.Transaction;
import cn.yong.mybatis.transaction.jdbc.JdbcTransactionFactory;
import cn.yong.mybatis.type.TypeAliasRegistry;
import cn.yong.mybatis.type.TypeHandlerRegistry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Allen
 * @desc 配置项
 * @date 2022/11/4
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
     * 映射的语句，存在 Map 里
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();
    /**
     * 类型别名注册机
     */
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
    protected final LanguageDriverRegistry languageRegistry = new LanguageDriverRegistry();

    /**
     * 类型处理注册机
     */
    protected final TypeHandlerRegistry typeHandlerRegistry = new TypeHandlerRegistry();
    /**
     * 对象工厂
     */
    protected ObjectFactory objectFactory = new DefaultObjectFactory();
    /**
     * 对象包装器工厂
     */
    protected ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();

    protected final Set<String> loadedResources = new HashSet<>();

    protected String databaseId;

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
        typeAliasRegistry.registerAlias("UNPOOLED", UnpooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);

        languageRegistry.setDefaultDriverClass(XMLLanguageDriver.class);
    }

    public void addMappers(String packageName) {
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

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    /**
     * 执行结果集处理器
     */
    public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, BoundSql boundSql) {
        return new DefaultResultSetHandler(executor, mappedStatement, boundSql);
    }

    /**
     * 生成执行器
     * @param transaction
     * @return
     */
    public Executor newExecutor(Transaction transaction) {
        return new SimpleExecutor(this, transaction);
    }

    /**
     * 创建语句处理器
     */
    public StatementHandler newStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        return new PreparedStatementHandler(executor, mappedStatement, parameter, resultHandler, boundSql);
    }

    /**
     * 创建元对象
     * @param object
     * @return
     */
    public MetaObject newMetaObject(Object object) {
        return MetaObject.forObject(object, objectFactory, objectWrapperFactory);
    }

    public TypeHandlerRegistry getTypeHandlerRegistry() {
        return typeHandlerRegistry;
    }

    public boolean isResourceLoaded(String resource) {
        return loadedResources.contains(resource);
    }

    public void setLoadedResources(String resources) {
        loadedResources.add(resources);
    }

    public LanguageDriverRegistry getLanguageRegistry() {
        return languageRegistry;
    }

    public ParameterHandler newParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        // 创建参数处理器
        ParameterHandler parameterHandler = mappedStatement.getLang().createParameterHandler(mappedStatement, parameterObject, boundSql);
        // 插件的一些参数，也是在这里，暂时不添加这部分内容 interceptorChain.pluginAll(parameterHandler);
        return parameterHandler;
    }

    public LanguageDriver getDefaultScriptingLanguageInstance() {
        return languageRegistry.getDefaultDriver();
    }
}
