package cn.yong.mybatis.reflection.wrapper;

import cn.yong.mybatis.reflection.MetaObject;

/**
 * @author Line
 * @desc 默认对象包装工厂
 * @date 2022/8/31
 */
public class DefaultObjectWrapperFactory implements ObjectWrapperFactory {
    @Override
    public boolean hasWrapperFor(Object object) {
        return false;
    }

    @Override
    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
        throw new RuntimeException("The DefaultObjectWrapperFactory should never be called to provide an ObjectWrapper.");
    }
}
