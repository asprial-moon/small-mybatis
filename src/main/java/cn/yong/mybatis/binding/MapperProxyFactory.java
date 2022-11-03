package cn.yong.mybatis.binding;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author Allen
 * @desc 映射器代理工厂
 * @date 2022/11/3
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(Map<String, String> sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        return (T) Proxy.newProxyInstance(mapperProxy.getClass().getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}
