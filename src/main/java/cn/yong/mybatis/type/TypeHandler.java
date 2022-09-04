package cn.yong.mybatis.type;


import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Line
 * @desc 类型处理器
 * @date 2022/9/3
 */
public interface TypeHandler<T> {

    /**
     * 设置参数
     * @param ps
     * @param i
     * @param parameter
     * @param jdbcType
     * @throws SQLException
     */
    void setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException;
}
