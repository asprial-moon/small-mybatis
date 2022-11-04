package cn.yong.mybatis.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Allen
 * @desc 事务接口
 * @date 2022/11/4
 */
public interface Transaction {

    Connection getConnection() throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

    void close() throws SQLException;

}
