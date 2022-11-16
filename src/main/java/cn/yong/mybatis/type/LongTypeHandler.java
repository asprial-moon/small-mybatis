package cn.yong.mybatis.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Allen
 * @desc Long类型处理器
 * @date 2022/11/16
 */
public class LongTypeHandler extends BaseTypeHandler<Long> {
    @Override
    protected void setNonNullParameter(PreparedStatement ps, int i, Long parameter, JdbcType jdbcType) throws SQLException {
        ps.setLong(i, parameter);
    }
}
