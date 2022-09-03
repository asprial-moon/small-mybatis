package cn.yong.mybatis.scripting.xmltags;

import cn.yong.mybatis.builder.xml.XMLMapperBuilder;
import cn.yong.mybatis.mapping.SqlSource;
import cn.yong.mybatis.scripting.LanguageDriver;
import cn.yong.mybatis.session.Configuration;
import org.dom4j.Element;

/**
 * @author Line
 * @desc XML语言驱动器
 * @date 2022/9/3
 */
public class XMLLanguageDriver implements LanguageDriver {
    @Override
    public SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType) {
        XMLScriptBuilder builder = new XMLScriptBuilder(configuration, script, parameterType);
        return builder.parseScriptNode();
    }
}
