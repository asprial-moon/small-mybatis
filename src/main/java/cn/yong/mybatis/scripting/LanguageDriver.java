package cn.yong.mybatis.scripting;

import cn.yong.mybatis.executor.parameter.ParameterHandler;
import cn.yong.mybatis.mapping.BoundSql;
import cn.yong.mybatis.mapping.MappedStatement;
import cn.yong.mybatis.mapping.SqlSource;
import cn.yong.mybatis.session.Configuration;
import org.dom4j.Element;

/**
 * @author Line
 * @desc 脚本语言启动器
 * @date 2022/9/3
 */
public interface LanguageDriver {

    /**
     * 创建SqlSource(mapper xml方式)
     * @param configuration
     * @param script
     * @param parameterType
     * @return
     */
    SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType);

    /**
     * 创建SQL源码(annotation 注解方式)
     * @param configuration
     * @param script
     * @param parameterType
     * @return
     */
    SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType);

    /**
     * 创建参数处理器
     * @param mappedStatement
     * @param parameterObject
     * @param boundSql
     * @return
     */
    ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql);

}
