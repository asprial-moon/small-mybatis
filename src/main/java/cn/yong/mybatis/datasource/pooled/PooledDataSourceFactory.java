package cn.yong.mybatis.datasource.pooled;

import cn.yong.mybatis.datasource.unpooled.UnpooledDataSourceFactory;

import javax.sql.DataSource;

/**
 * @author Line
 * @desc 有池连接的数据数据源工厂
 * @date 2022/11/7
 */
public class PooledDataSourceFactory extends UnpooledDataSourceFactory {

    public PooledDataSourceFactory() {
        this.dataSource = new PooledDataSource();
    }

}
