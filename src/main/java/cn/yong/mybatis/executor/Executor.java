package cn.yong.mybatis.executor;

import cn.yong.mybatis.mapping.BoundSql;
import cn.yong.mybatis.mapping.MappedStatement;
import cn.yong.mybatis.session.ResultHandler;
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
     * 查询
     * @param ms
     * @param parameter
     * @param resultHandler
     * @param boundSql
     * @return
     * @param <E>
     */
    <E> List<E> query(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql);

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
}
