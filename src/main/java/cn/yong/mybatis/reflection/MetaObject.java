package cn.yong.mybatis.reflection;

import cn.yong.mybatis.reflection.factory.ObjectFactory;
import cn.yong.mybatis.reflection.property.PropertyTokenizer;
import cn.yong.mybatis.reflection.wrapper.MapWrapper;
import cn.yong.mybatis.reflection.wrapper.ObjectWrapper;
import cn.yong.mybatis.reflection.wrapper.ObjectWrapperFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Line
 * @desc 元对象
 * @date 2022/11/8
 */
public class MetaObject {
    /**
     * 原对象
     */
    private Object originalObject;
    /**
     * 对象包装器
     */
    private ObjectWrapper objectWrapper;
    /**
     * 对象工厂
     */
    private ObjectFactory objectFactory;
    /**
     * 对象包装工厂
     */
    private ObjectWrapperFactory objectWrapperFactory;

    public MetaObject(Object object, ObjectFactory objectFactory, ObjectWrapperFactory objectWrapperFactory) {
        this.originalObject = object;
        this.objectFactory = objectFactory;
        this.objectWrapperFactory = objectWrapperFactory;

        if (object instanceof ObjectWrapper) {
            // 如果对象本身已经是ObjectWrapper型，则直接赋给objectWrapper
            this.objectWrapper = (ObjectWrapper) object;
        } else if (objectWrapperFactory.hasWrapperFor(object)) {
            // 如果有包装其，调用objectWrapperFactory.getWrapperFor
            this.objectWrapper = objectWrapperFactory.getWrapperFor(this, object);
        } else if (object instanceof Map) {
            // 如果是Map型，返回MapWrapper

        } else if (object instanceof Collection) {
            // 如果是Collection型, 返回CollectionWrapper
//            this.objectWrapper = ne
        } else {
            // 除此以外，返回BeanWrapper
            this.objectWrapper = null;
        }

    }

    public static MetaObject forObject(Object object, ObjectFactory objectFactory, ObjectWrapperFactory objectWrapperFactory) {
        if (object == null) {
            // 处理下null，将null包装起来
            return SystemMetaObject.NULL_MATE_OBJECT;
        } else {
            return new MetaObject(object, objectFactory, objectWrapperFactory);
        }
    }

    public ObjectFactory getObjectFactory() {
        return objectFactory;
    }

    public ObjectWrapperFactory getObjectWrapperFactory() {
        return objectWrapperFactory;
    }

    public Object getOriginalObject() {
        return originalObject;
    }

    /* ------------ 以下方法都是委派给 ObjectWrapper ------------ */

    public String findProperty(String propName, boolean useCamelCaseMapping) {
        return objectWrapper.findProperty(propName, useCamelCaseMapping);
    }

    /**
     * 取得getter的名字列表
     * @return
     */
    public String[] getGetterNames() {
        return objectWrapper.getGetterNames();
    }

    /**
     * 取得setter的名字列表
     * @return
     */
    public String[] getSetterNames() {
        return objectWrapper.getSetterNames();
    }

    /**
     * 取得setter的类型列表
     * @param name
     * @return
     */
    public Class<?> getSetterType(String name) {
        return objectWrapper.getSetterType(name);
    }

    /**
     * 取得getter的类型列表
     * @param name
     * @return
     */
    public Class<?> getGetterType(String name) {
        return objectWrapper.getGetterType(name);
    }

    /**
     * 是否有指定的setter
     * @param name
     * @return
     */
    public boolean hasSetter(String name) {
        return objectWrapper.hasSetter(name);
    }

    /**
     * 是否有指定的getter
     * @param name
     * @return
     */
    public boolean hasGetter(String name) {
        return objectWrapper.hasGetter(name);
    }

    /**
     * 取得值
     * 如 班级[0].学生.成绩
     * @param name
     * @return
     */
    public Object getValue(String name) {
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()) {
            MetaObject metaValue = metaObjectForProperty(prop.getIndexedName());
            if (metaValue == SystemMetaObject.NULL_MATE_OBJECT) {
                // 如果上层就是null了，那就结束，返回null
                return null;
            } else {
                // 否则继续看下一层，递归调用getValue
                return metaValue.getValue(prop.getChildren());
            }
        } else {
            return objectWrapper.get(prop);
        }
    }

    /**
     * 设置值
     * 如 班级[0].学生.成绩
     * @param name
     * @param value
     */
    public void setValue(String name, Object value) {
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()) {
            MetaObject metaValue = metaObjectForProperty(prop.getIndexedName());
            if (metaValue == SystemMetaObject.NULL_MATE_OBJECT) {
                if (value == null && prop.getChildren() != null) {
                    // don't instantiate child path id value is null
                    // 如果上层就是 null 了，还得看有没有儿子，没有那就结束了
                    return;
                } else {
                    // 否则还得 new 一个，委派给 ObjectWrapper.instantiatePropertyValue
                    metaValue = objectWrapper.instantiatePropertyValue(name, prop, objectFactory);
                }
            }
            // 递归调用setValue
            metaValue.setValue(prop.getChildren(), value);
        } else {
            // 到了最后一层了，所以委派给 ObjectWrapper.set
            objectWrapper.set(prop, value);
        }
    }

    /**
     * 为属性生成元对象
     * @param name
     * @return
     */
    public MetaObject metaObjectForProperty(String name) {
        // 实例是递归调用
        Object value = getValue(name);
        return MetaObject.forObject(value, objectFactory, objectWrapperFactory);
    }

    public ObjectWrapper getObjectWrapper() {
        return objectWrapper;
    }

    public boolean isCollection() {
        return objectWrapper.isCollection();
    }

    public void add(Object element) {
        objectWrapper.add(element);
    }

    public <E> void addAll(List<E> list) {
        objectWrapper.addAll(list);
    }
}
