package cn.yong.mybatis.transaction;

import cn.yong.mybatis.session.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 事务工厂
 * 以工厂方法模式包装 JDBC 事务实现，为每一个事务实现都提供一个对应的工厂
 * @author Allen
 * @date 2022/8/29
 */
public interface TransactionFactory {

    /**
     * 根据Connection 创建 Transaction
     * @param conn Existing database connection
     * @return
     */
    Transaction newTransaction(Connection conn);

    /**
     * 根据数据源和事务隔离级别创建Transaction
     * @param dataSource
     * @param level
     * @param autoCommit
     * @return
     */
    Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit);

}
