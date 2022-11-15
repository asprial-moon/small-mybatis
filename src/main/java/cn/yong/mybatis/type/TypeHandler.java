package cn.yong.mybatis.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Allen
 * @desc 类型处理器
 * @date 2022/11/15
 */
public interface TypeHandler<T> {

    /**
     * 设置参数
     */
    void setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException;

}
