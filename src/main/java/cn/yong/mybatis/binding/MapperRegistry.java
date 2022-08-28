package cn.yong.mybatis.binding;

import cn.hutool.core.lang.ClassScanner;
import cn.yong.mybatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 映射器注册机
 * MapperRegistry提供包路径的扫描和映射器代理类注册机服务，完成接口对象的代理类注册处理
 *
 * 1.MapperRegistry 映射器注册类的核心主要用于提供了ClassScanner.scanPackage扫描包路径，调用addMapper方法，
 * 给接口类创建MapperProxyFactory映射器代理类，并写入到knownMappers的HashMap缓存中
 * 2.另外就是这个类也提供了对应的 getMapper 获取映射器代理类的方法，其实这步就包装了我们上一章节手动操作实例化的过程，
 * 更加方便在 DefaultSqlSession 中获取 Mapper 时进行使用。
 * @author Allen
 * @date 2022/8/28
 */
public class MapperRegistry {

    /**
     * 将已添加的映射器代理加入到HashMap
     */
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("type " + type + " is not known to the MapperRegistry");
        }

        try {
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException("Error getting mapper instance. Cause: " + e, e);
        }
    }

    public <T> void addMapper(Class<T> type) {
        /** 必须是接口才会注册 */
        if (type.isInterface()) {
            if (hasMapper(type)) {
                // 如果重复添加了，报错
                throw new RuntimeException("Type " + type + " is alaready known to the MapperRegistry.");
            }
            // 注册映射器代理工厂
            knownMappers.put(type, new MapperProxyFactory<>(type));
        }
    }

    private <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }

    public void addMappers(String packageName) {
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for (Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }
    }
}
