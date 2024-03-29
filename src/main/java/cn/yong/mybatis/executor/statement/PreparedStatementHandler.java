package cn.yong.mybatis.executor.statement;

import cn.yong.mybatis.executor.Executor;
import cn.yong.mybatis.executor.keygen.KeyGenerator;
import cn.yong.mybatis.mapping.BoundSql;
import cn.yong.mybatis.mapping.MappedStatement;
import cn.yong.mybatis.session.ResultHandler;
import cn.yong.mybatis.session.RowBounds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 预处理语句处理器
 * @author Allen
 * @date 2022/8/30
 */
public class PreparedStatementHandler extends BaseStatementHandler {

    public PreparedStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameterObject, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        super(executor, mappedStatement, parameterObject, rowBounds, resultHandler, boundSql);
    }

    @Override
    protected Statement instantiateStatement(Connection connection) throws SQLException {
        String sql = boundSql.getSql();
        return connection.prepareStatement(sql);
    }

    /**
     * 设置参数
     * @param statement
     * @throws SQLException
     */
    @Override
    public void parameterize(Statement statement) throws SQLException {
//        PreparedStatement ps = (PreparedStatement) statement;
//        ps.setLong(1, Long.parseLong(((Object[]) parameterObject)[0].toString()));
        parameterHandler.setParameters((PreparedStatement) statement);
    }

    @Override
    public int update(Statement statement) throws SQLException {
        // 1. 执行 insert/delete/update
        PreparedStatement ps = (PreparedStatement) statement;
        ps.execute();
        int rows = ps.getUpdateCount();

        // 2. 执行 selectKey 语句
        Object parameterObject = boundSql.getParameterObject();
        KeyGenerator keyGenerator = mappedStatement.getKeyGenerator();
        keyGenerator.processAfter(executor, mappedStatement, ps, parameterObject);
        return rows;
    }

    @Override
    public <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException {
        PreparedStatement ps = (PreparedStatement) statement;
        ps.execute();
        return resultSetHandler.handleResultSets(statement);
    }
}
