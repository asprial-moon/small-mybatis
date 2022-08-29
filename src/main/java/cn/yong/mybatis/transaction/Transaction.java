package cn.yong.mybatis.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 事务接口
 * @author Allen
 * @date 2022/8/29
 */
public interface Transaction {

    /**
     * 获取连接对象
     * @return
     * @throws SQLException
     */
    Connection getConnection() throws SQLException;

    /**
     * 提交事务
     * @throws SQLException
     */
    void commit() throws SQLException;

    /**
     * 回滚事务
     * @throws SQLException
     */
    void rollback() throws SQLException;

    /**
     * 关闭连接
     * @throws SQLException
     */
    void close() throws SQLException;
}
