package cn.yong.mybatis.reflection.wrapper;

import cn.yong.mybatis.reflection.MetaObject;
import cn.yong.mybatis.reflection.factory.ObjectFactory;
import cn.yong.mybatis.reflection.property.PropertyTokenizer;

import java.util.List;

/**
 * @author Line
 * @desc 对象包装器
 * @date 2022/11/8
 */
public interface ObjectWrapper {

    /**
     * get
     * @param prop
     * @return
     */
    Object get(PropertyTokenizer prop);

    /**
     * set
     * @param prop
     * @param value
     */
    void set(PropertyTokenizer prop, Object value);

    /**
     * 查找属性
     * @param name
     * @param useCamelCaseMapping
     * @return
     */
    String findProperty(String name, Object useCamelCaseMapping);

    /**
     * 取得getter的名字列表
     * @return
     */
    String[] getGetterNames();

    /**
     * 取得setter的名字列表
     * @return
     */
    String[] getSetterNames();

    /**
     * 取得setter的类型
     * @return
     */
    Class<?> getSetterType(String name);

    /**
     * 取得getter的类型
     * @return
     */
    Class<?> getGetterType(String name);

    /**
     * 是否有指定的setter
     * @param name
     * @return
     */
    boolean hasSetter(String name);

    /**
     * 是否有指定的getter
     * @param name
     * @return
     */
    boolean hasGetter(String name);

    /**
     * 实例化属性
     * @param name
     * @param prop
     * @param objectFactory
     * @return
     */
    MetaObject instantiatePropertyValue(String name, PropertyTokenizer prop, ObjectFactory objectFactory);

    /**
     * 是否是集合
     * @return
     */
    boolean isCollection();

    /**
     * 添加属性
     * @param element
     */
    void add(Object element);

    /**
     * 添加属性
     * @param element
     * @param <E>
     */
    <E> void addAll(List<E> element);
}
