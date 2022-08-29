package cn.yong.mybatis.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 数据源工厂
 * @author Allen
 * @date 2022/8/29
 */
public interface DataSourceFactory {

    /**
     * 设置属性
     * @param properties
     */
    void setProperties(Properties properties);

    /**
     * 获取数据源
     * @return
     */
    DataSource getDataSource();

}
