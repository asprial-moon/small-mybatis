package cn.yong.mybatis.scripting;

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
     * 创建SqlSource
     * @param configuration
     * @param script
     * @param parameterType
     * @return
     */
    SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType);

}
