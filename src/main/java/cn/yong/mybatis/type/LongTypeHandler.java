package cn.yong.mybatis.type;

import java.sql.PreparedStatement;

import java.sql.SQLException;

/**
 * @author Line
 * @desc Long类型处理器
 * @date 2022/9/4
 */
public class LongTypeHandler extends BaseTypeHandler<Long> {
    @Override
    protected void setNonNullParameter(PreparedStatement ps, int i, Long parameter, JdbcType jdbcType) throws SQLException {
        ps.setLong(i, parameter);
    }
}
