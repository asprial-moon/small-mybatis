package cn.yong.mybatis.scripting;

import cn.yong.mybatis.executor.parameter.ParameterHandler;
import cn.yong.mybatis.mapping.BoundSql;
import cn.yong.mybatis.mapping.MappedStatement;
import cn.yong.mybatis.mapping.SqlSource;
import cn.yong.mybatis.session.Configuration;
import org.dom4j.Element;

/**
 * @author Allen
 * @desc 脚本语言驱动
 * @date 2022/11/15
 */
public interface LanguageDriver {

    /**
     * 创建SQL源码(mapper xml方式)
     */
    SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType);

    /**
     * 创建参数处理器
     */
    ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql);
}
