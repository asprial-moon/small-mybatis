package cn.yong.mybatis.type;

import java.sql.PreparedStatement;

import java.sql.SQLException;

/**
 * @author Line
 * @desc String类型处理器
 * @date 2022/9/4
 */
public class StringTypeHandler extends BaseTypeHandler<String> {
    @Override
    protected void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter);
    }
}
