package cn.yong.mybatis.executor.resultset;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 结果集处理器
 * @author Allen
 * @date 2022/8/30
 */
public interface ResultSetHandler {
    /**
     * 结果集处理
     * @param stmt
     * @return
     * @param <E>
     * @throws SQLException
     */
    <E> List<E> handleResultSets(Statement stmt) throws SQLException;
}
