package cn.yong.mybatis.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Line
 * @desc 数据源工厂
 * @date 2022/11/6
 */
public interface DataSourceFactory {

    void setProperties(Properties props);

    DataSource getDataSource();

}
