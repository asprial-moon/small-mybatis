package cn.yong.mybatis.reflection;

import cn.yong.mybatis.reflection.factory.ObjectFactory;
import cn.yong.mybatis.reflection.wrapper.MapWrapper;
import cn.yong.mybatis.reflection.wrapper.ObjectWrapper;
import cn.yong.mybatis.reflection.wrapper.ObjectWrapperFactory;

import java.util.Collection;
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


}
