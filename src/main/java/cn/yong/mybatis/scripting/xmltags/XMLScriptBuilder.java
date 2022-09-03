package cn.yong.mybatis.scripting.xmltags;

import cn.yong.mybatis.builder.BaseBuilder;
import cn.yong.mybatis.mapping.SqlSource;
import cn.yong.mybatis.scripting.defaults.RawSqlSource;
import cn.yong.mybatis.session.Configuration;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Line
 * @desc XML脚本构建器
 * @date 2022/9/3
 */
public class XMLScriptBuilder extends BaseBuilder {

    private Element element;

    private boolean idDynamic;

    private Class<?> parameterType;

    public XMLScriptBuilder(Configuration configuration, Element element, Class<?> parameterType) {
        super(configuration);
        this.element = element;
        this.parameterType = parameterType;
    }

    public SqlSource parseScriptNode() {
        List<SqlNode> contents = parseDynamicTags(element);
        MixedSqlNode rootSqlNode = new MixedSqlNode(contents);
        return new RawSqlSource(configuration, rootSqlNode, parameterType);
    }

    List<SqlNode> parseDynamicTags(Element element) {
        List<SqlNode> contents = new ArrayList<>();
        // element.getText 拿到SQL
        String data = element.getText();
        contents.add(new StaticTextSqlNode(data));
        return contents;
    }
}
