package cn.yong.mybatis.executor;

import cn.yong.mybatis.mapping.BoundSql;
import cn.yong.mybatis.mapping.MappedStatement;
import cn.yong.mybatis.session.ResultHandler;
import cn.yong.mybatis.transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Line
 * @desc 执行器
 * @date 2022/11/7
 */
public interface Executor {

    ResultHandler NO_RESULT_HANDLER = null;

    <E> List<E> query(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql);

    Transaction getTransaction();

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    void close(boolean forceRollback);
}
