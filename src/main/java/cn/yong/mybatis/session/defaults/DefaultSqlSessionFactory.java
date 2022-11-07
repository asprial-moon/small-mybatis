package cn.yong.mybatis.session.defaults;

import cn.yong.mybatis.executor.Executor;
import cn.yong.mybatis.mapping.Environment;
import cn.yong.mybatis.session.Configuration;
import cn.yong.mybatis.session.SqlSession;
import cn.yong.mybatis.session.SqlSessionFactory;
import cn.yong.mybatis.session.TransactionIsolationLevel;
import cn.yong.mybatis.transaction.Transaction;
import cn.yong.mybatis.transaction.TransactionFactory;

import java.sql.SQLException;

/**
 * @author Allen
 * @desc 默认的 DefaultSqlSessionFactory
 * @date 2022/11/3
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        Transaction tx = null;
        try {
            final Environment environment = configuration.getEnvironment();
            TransactionFactory transactionFactory = environment.getTransactionFactory();
            tx = transactionFactory.newTransaction(configuration.getEnvironment().getDataSource(), TransactionIsolationLevel.READ_COMMITTED, false);

            // 创建执行器
            final Executor executor = configuration.newExecutor(tx);
            // 创建DefaultSqlSession
            return new DefaultSqlSession(configuration, executor);
        } catch (Exception e) {
            try {
                assert tx != null;
                tx.close();
            } catch (SQLException ignore) {
            }
            throw new RuntimeException("Error opening session. Cause: " + e);
        }
    }
}
