package cn.yong.mybatis.builder;

import cn.yong.mybatis.type.TypeAliasRegistry;
import cn.yong.mybatis.session.Configuration;
import cn.yong.mybatis.type.TypeHandlerRegistry;

/**
 * 构建器的基类，建造者模式
 * @author Allen
 * @date 2022/8/28
 */
public class BaseBuilder {

    protected final Configuration configuration;
    protected final TypeAliasRegistry typeAliasRegistry;
    protected final TypeHandlerRegistry typeHandlerRegistry;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = this.configuration.getTypeAliasRegistry();
        this.typeHandlerRegistry = this.configuration.getTypeHandlerRegistry();
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    protected Class<?> resolveAlias(String alias) {
        return typeAliasRegistry.resolveAlias(alias);
    }
}
