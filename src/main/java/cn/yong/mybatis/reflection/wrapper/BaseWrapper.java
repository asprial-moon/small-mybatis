package cn.yong.mybatis.reflection.wrapper;

import cn.yong.mybatis.reflection.MetaObject;
import cn.yong.mybatis.reflection.factory.ObjectFactory;
import cn.yong.mybatis.reflection.property.PropertyTokenizer;

import java.util.List;

/**
 * @author Line
 * @desc 对象包装器抽象基类，提供一些工具方法
 * @date 2022/11/8
 */
public abstract class BaseWrapper implements ObjectWrapper {

    protected static final Object[] NO_ARGUMENTS = new Object[0];

    protected MetaObject metaObject;

    protected BaseWrapper(MetaObject metaObject) {
        this.metaObject = metaObject;
    }

    /**
     * 解析集合
     * @param prop
     * @param object
     * @return
     */
    protected Object resolveCollection(PropertyTokenizer prop, Object object) {
        if ("".equals(prop.getName())) {
            return object;
        } else {
            return metaObject.getValue(prop.getName());
        }
    }


}
