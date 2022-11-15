package cn.yong.mybatis.builder;

import cn.yong.mybatis.session.Configuration;
import cn.yong.mybatis.type.TypeAliasRegistry;
import cn.yong.mybatis.type.TypeHandlerRegistry;

/**
 * @author Allen
 * @desc 构建器的基类，建造者模式
 * @date 2022/11/4
 */
public abstract class BaseBuilder {

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
