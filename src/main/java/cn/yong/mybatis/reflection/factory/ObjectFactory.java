package cn.yong.mybatis.reflection.factory;

import java.util.List;
import java.util.Properties;

/**
 * @author Line
 * @desc 对象工厂接口
 * @date 2022/8/31
 */
public interface ObjectFactory {

    /**
     * Sets configuration properties.
     * 设置属性
     * @param properties
     */
    void setProperties(Properties properties);

    /**
     * Creates a new object with default constructor.
     * 生产对象
     * @param type
     * @return
     * @param <T>
     */
    <T> T create(Class<T> type);

    /**
     * Creates a new object with the specified constructor and params.
     * 生产对象，使用指定的构造函数和构造函数参数
     * @param type
     * @param constructorArgTypes
     * @param constructorArgs
     * @return
     * @param <T>
     */
    <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs);

    /**
     * Returns true if this object can have a set of other objects. It's main purpose is to support non-java.util.Collection objects like Scala collections.
     * 返回这个对象是否是集合，为了支持 Scala collections
     * @param type
     * @return
     * @param <T>
     */
    <T> boolean isCollection(Class<T> type);
}
