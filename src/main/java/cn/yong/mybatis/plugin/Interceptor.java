package cn.yong.mybatis.plugin;

import java.util.Properties;

/**
 * @author Line
 * @desc 拦截器接口
 * @date 2022/9/7
 */
public interface Interceptor {

    /**
     * 代理，使用方实现
     * @param invocation
     * @return
     * @throws Throwable
     */
    Object intercept(Invocation invocation) throws Throwable;

    /**
     * 代理
     * @param target
     * @return
     */
    default Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 设置属性
     * @param properties
     */
    default void setProperties(Properties properties) {
        // NOP
    }
}
