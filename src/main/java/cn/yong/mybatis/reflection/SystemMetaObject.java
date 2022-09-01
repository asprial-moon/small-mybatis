package cn.yong.mybatis.reflection;

import cn.yong.mybatis.reflection.factory.DefaultObjectFactory;
import cn.yong.mybatis.reflection.factory.ObjectFactory;
import cn.yong.mybatis.reflection.wrapper.DefaultObjectWrapperFactory;
import cn.yong.mybatis.reflection.wrapper.ObjectWrapperFactory;

/**
 * @author Line
 * @desc 一些系统级别的元对象
 * @date 2022/8/31
 */
public class SystemMetaObject {

    public static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();

    public static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();

    public static final MetaObject NULL_META_OBJECT = MetaObject.forObject(NullObject.class, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);

    private SystemMetaObject() {
    }

    private static class NullObject {
    }

    public static MetaObject forObject(Object object) {
        return MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
    }
}
