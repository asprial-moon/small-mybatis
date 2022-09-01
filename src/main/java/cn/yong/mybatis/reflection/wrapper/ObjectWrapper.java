package cn.yong.mybatis.reflection.wrapper;

import cn.yong.mybatis.reflection.MetaObject;
import cn.yong.mybatis.reflection.factory.ObjectFactory;
import cn.yong.mybatis.reflection.property.PropertyTokenizer;

import javax.xml.bind.Element;
import java.util.List;

/**
 * @author Line
 * @desc 对象包装器
 * @date 2022/8/31
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
    String findProperty(String name, boolean useCamelCaseMapping);

    /**
     * 取的getter的名字列表
     * @return
     */
    String[] getGetterNames();

    /**
     * 取的setter的名字列表
     * @return
     */
    String[] getSetterNames();

    /**
     * 取的setter的类型
     * @param name
     * @return
     */
    Class<?> getSetterType(String name);

    /**
     * 取的getter的类型
     * @param name
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
     */
    void add(Object element);

    /**
     * 添加属性
     * @param element
     * @param <E>
     */
    <E> void addAll(List<E> element);
}
