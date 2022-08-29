package cn.yong.mybatis.transaction.jdbc;

import cn.yong.mybatis.session.TransactionIsolationLevel;
import cn.yong.mybatis.transaction.Transaction;
import cn.yong.mybatis.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * JdbcTransaction 工厂
 * @author Allen
 * @date 2022/8/29
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
