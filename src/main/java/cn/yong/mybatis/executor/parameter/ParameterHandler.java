package cn.yong.mybatis.executor.parameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Allen
 * @desc 参数处理器
 * @date 2022/11/16
 */
public interface ParameterHandler {

    /**
     * 获取参数
     * @return
     */
    Object getParameterObject();

    /**
     * 设置参数
     * @param ps
     * @throws SQLException
     */
    void setParameters(PreparedStatement ps) throws SQLException;
}
