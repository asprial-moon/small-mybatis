package cn.yong.mybatis.session;

import cn.yong.mybatis.builder.xml.XMLConfigBuilder;
import cn.yong.mybatis.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * @author Allen
 * @desc 构建SqlSessionFactory的工厂
 * @date 2022/11/4
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration configuration) {
        return new DefaultSqlSessionFactory(configuration);
    }
}
