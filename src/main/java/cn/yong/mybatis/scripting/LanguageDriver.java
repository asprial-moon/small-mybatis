package cn.yong.mybatis.scripting;

import cn.yong.mybatis.mapping.SqlSource;
import cn.yong.mybatis.session.Configuration;
import org.dom4j.Element;

/**
 * @author Allen
 * @desc 脚本语言驱动
 * @date 2022/11/15
 */
public interface LanguageDriver {

    SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType);

}
