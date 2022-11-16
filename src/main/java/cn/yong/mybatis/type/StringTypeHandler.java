package cn.yong.mybatis.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Allen
 * @date 2022/11/16
 */
public class StringTypeHandler extends BaseTypeHandler<String> {
    @Override
    protected void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter);
    }
}
