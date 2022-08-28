package cn.yong.mybatis.builder.xml;

import cn.yong.mybatis.builder.BaseBuilder;
import cn.yong.mybatis.session.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.Reader;
import java.util.List;

/**
 * XMLConfigBuilder 核心操作在于初始化 Configuration，因为 Configuration 的使用离解析 XML 和存放是最近的操作，所以放在这里比较适合。
 * 之后就是具体的 parse() 解析操作，并把解析后的信息，通过 Configuration 配置类进行存放，包括：添加解析 SQL、注册Mapper映射器。
 * 解析配置整体包括：类型别名、插件、对象工厂、对象包装工厂、设置、环境、类型转换、映射器，但目前我们还不需要那么多，所以只做一些必要的 SQL 解析处理。
 * @author Allen
 * @date 2022/8/28
 */
public class XMLConfigBuilder extends BaseBuilder {

    private Element root;

    public XMLConfigBuilder(Reader reader) {
        // 1.调用父类初始化Configuration
        super(new Configuration());

        // 2. dom4j处理xml
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new InputSource(reader));
            root = document.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public Configuration parse() {
        try {
            mapperElement(root.element("mappers"));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
        }

        return null;
    }

    private void mapperElement(Element mappers) throws Exception {
        List<Element> elementList = mappers.elements("mapper");
        for (Element e : elementList) {
            // 解析处理，具体参照源码

            // 添加解析 SQL


        }
    }
}
