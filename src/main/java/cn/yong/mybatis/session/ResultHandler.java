package cn.yong.mybatis.session;


import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 结果集处理器
 * @author Allen
 * @date 2022/8/30
 */
public interface ResultHandler {

    /**
     * 结果集
     */
    void handleResult();

}
