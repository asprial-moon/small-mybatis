package cn.yong.mybatis.datasource.pooled;

import cn.yong.mybatis.datasource.unpooled.UnpooledDataSource;
import cn.yong.mybatis.datasource.unpooled.UnpooledDataSourceFactory;

import javax.sql.DataSource;

/**
 * @desc 有连接池的数据源工厂
 * @author  Line
 * @date 2022/8/29
 */
public class PooledDataSourceFactory extends UnpooledDataSourceFactory {


    public PooledDataSourceFactory() {
        dataSource = new UnpooledDataSource();
    }


}
