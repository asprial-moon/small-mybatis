package cn.yong.mybatis.executor;

import cn.yong.mybatis.mapping.BoundSql;
import cn.yong.mybatis.mapping.MappedStatement;
import cn.yong.mybatis.session.Configuration;
import cn.yong.mybatis.session.ResultHandler;
import cn.yong.mybatis.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * 执行器抽象基础类
 * @author Allen
 * @date 2022/8/30
 */
public abstract class BaseExecutor implements Executor {

    private Logger logger = LoggerFactory.getLogger(BaseExecutor.class);

    protected Configuration configuration;

    protected Transaction transaction;

    protected Executor wrapper;

    private boolean closed;

    public BaseExecutor(Configuration configuration, Transaction transaction) {
        this.configuration = configuration;
        this.transaction = transaction;
        this.wrapper = this;
    }

    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        if (closed) {
            throw new RuntimeException("Executor was closed.");
        }
        return doQuery(ms, parameter, resultHandler, boundSql);
    }

    /**
     * 执行查询
     * @param ms
     * @param parameter
     * @param resultHandler
     * @param boundSql
     * @return
     * @param <E>
     */
    protected abstract <E> List<E> doQuery(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql);

    @Override
    public Transaction getTransaction() {
        if (closed) {
            throw new RuntimeException("Executor was closed.");
        }
        return transaction;
    }

    @Override
    public void commit(boolean required) throws SQLException {
        if (closed) {
            throw new RuntimeException("Cannot commit, transaction is already closed");
        }
        if (required) {
            transaction.commit();
        }
    }

    @Override
    public void rollback(boolean required) throws SQLException {
        if (!closed) {
            if (required) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void close(boolean forceRollback) {
        try {
            try {
                rollback(forceRollback);
            } finally {
                transaction.close();
            }
        } catch (SQLException e) {
            logger.warn("Unexpected exception on closing transaction. Cause: " + e);
        }
    }
}