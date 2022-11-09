package cn.yong.mybatis.reflection.wrapper;

import cn.yong.mybatis.reflection.MetaObject;

/**
 * @author Line
 * @desc 对象包装工厂
 * @date 2022/11/8
 */
public interface ObjectWrapperFactory {

    /**
     * 判断有没有包装器
     * @param object
     * @return
     */
    boolean hasWrapperFor(Object object);

    /**
     * 得到包装器
     * @param metaObject
     * @param object
     * @return
     */
    ObjectWrapper getWrapperFor(MetaObject metaObject, Object object);
}
