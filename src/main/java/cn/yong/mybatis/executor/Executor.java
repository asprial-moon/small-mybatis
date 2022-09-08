package cn.yong.mybatis.executor;

import cn.yong.mybatis.cache.CacheKey;
import cn.yong.mybatis.mapping.BoundSql;
import cn.yong.mybatis.mapping.MappedStatement;
import cn.yong.mybatis.session.ResultHandler;
import cn.yong.mybatis.session.RowBounds;
import cn.yong.mybatis.transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

/**
 * 执行器
 * @author Allen
 * @date 2022/8/30
 */
public interface Executor {

    ResultHandler NO_RESULT_HANDLER = null;

    /**
     * 更新
     * @param ms
     * @param parameter
     * @return
     * @throws SQLException
     */
    int update(MappedStatement ms, Object parameter) throws SQLException;

    /**
     * 查询
     * @param ms
     * @param parameter
     * @param rowBounds
     * @param resultHandler
     * @return
     * @param <E>
     * @throws SQLException
     */
    <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler) throws SQLException;

    /**
     * 查询
     * @param ms
     * @param parameter
     * @param rowBounds
     * @param resultHandler
     * @param key
     * @param boundSql
     * @return
     * @param <E>
     * @throws SQLException
     */
    <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, CacheKey key, BoundSql boundSql) throws SQLException;

    /**
     * 获取事务
     * @return
     */
    Transaction getTransaction();

    /**
     * 提交
     * @param required
     * @throws SQLException
     */
    void commit(boolean required) throws SQLException;

    /**
     * 回滚
     * @param required
     * @throws SQLException
     */
    void rollback(boolean required) throws SQLException;

    /**
     * 回滚
     * @param forceRollback
     */
    void close(boolean forceRollback);

    /**
     * 清理Session缓存
     */
    void clearLocalCache();

    /**
     * 创建缓存 Key
     * @param ms
     * @param parameterObject
     * @param rowBounds
     * @param boundSql
     * @return
     */
    CacheKey createCacheKey(MappedStatement ms, Object parameterObject, RowBounds rowBounds, BoundSql boundSql);

    /**
     * 设置执行器包装器
     * @param cachingExecutor
     */
    void setExecutorWrapper(CachingExecutor cachingExecutor);
}
