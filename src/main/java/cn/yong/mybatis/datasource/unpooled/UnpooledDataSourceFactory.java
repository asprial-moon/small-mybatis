package cn.yong.mybatis.datasource.unpooled;

import cn.yong.mybatis.datasource.DataSourceFactory;
import cn.yong.mybatis.reflection.MetaObject;
import cn.yong.mybatis.reflection.SystemMetaObject;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @desc 无池化数据源工厂
 * @author Line
 * @date 2022/8/29
 */
public class UnpooledDataSourceFactory implements DataSourceFactory {
    protected DataSource dataSource;

    public UnpooledDataSourceFactory() {
        this.dataSource = new UnpooledDataSource();
    }

    @Override
    public void setProperties(Properties props) {
        MetaObject metaObject = SystemMetaObject.forObject(dataSource);
        for (Object key : props.keySet()) {
            String propertyName = (String) key;
            if (metaObject.hasSetter(propertyName)) {
                String value = (String) props.get(propertyName);
                Object convertValue = convertValue(metaObject, propertyName, value);
                metaObject.setValue(propertyName, convertValue);
            }
        }
    }

    /**
     *
     * @param metaObject
     * @param propertyName
     * @param value
     * @return
     */
    private Object convertValue(MetaObject metaObject, String propertyName, String value) {
        Object convetedValue = value;
        Class<?> targetType = metaObject.getSetterType(propertyName);
        if (targetType == Integer.class || targetType == int.class) {
            convetedValue = Integer.valueOf(value);
        } else if (targetType == Long.class || targetType == long.class) {
            convetedValue = Long.valueOf(value);
        } else if (targetType == Boolean.class || targetType == boolean.class) {
            convetedValue = Boolean.valueOf(value);
        }
        return convetedValue;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }
}
