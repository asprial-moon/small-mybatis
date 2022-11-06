package cn.yong.mybatis.datasource.unpooled;

import cn.yong.mybatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Line
 * @desc 无池化数据源工厂
 * @date 2022/11/7
 */
public class UnpooledDataSourceFactory implements DataSourceFactory {

    private Properties props;

    @Override
    public void setProperties(Properties props) {
        this.props = props;
    }

    @Override
    public DataSource getDataSource() {
        UnpooledDataSource unpooledDataSource = new UnpooledDataSource();

        return null;
    }
}
