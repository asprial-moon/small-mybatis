package cn.yong.mybatis.session;

import cn.yong.mybatis.builder.xml.XMLConfigBuilder;
import cn.yong.mybatis.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * SqlSessionFactoryBuilder 是作为整个 Mybatis 的入口类，通过指定解析XML的IO，引导整个流程的启动。
 * 从这个类开始新增加了 XMLConfigBuilder、Configuration 两个处理类，分别用于解析 XML 和串联整个流程的对象保存操作。
 *  接下来我们会分别介绍这些新引入的对象。
 * @author Allen
 * @date 2022/8/28
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    private SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }
}
