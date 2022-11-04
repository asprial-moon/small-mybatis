package cn.yong.mybatis.transaction.jdbc;

import cn.yong.mybatis.session.TransactionIsolationLevel;
import cn.yong.mybatis.transaction.Transaction;
import cn.yong.mybatis.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author Allen
 * @desc JdbcTransaction 工厂
 * @date 2022/11/4
 */
public class JdbcTransactionFactory implements TransactionFactory {
    @Override
    public Transaction newTransaction(Connection conn) {
        return new JdbcTransaction(conn);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(dataSource, level, autoCommit);
    }
}
