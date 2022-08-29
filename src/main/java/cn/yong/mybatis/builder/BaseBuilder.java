package cn.yong.mybatis.builder;

import cn.yong.mybatis.TypeAliasRegistry;
import cn.yong.mybatis.session.Configuration;

/**
 * 构建器的基类，建造者模式
 * @author Allen
 * @date 2022/8/28
 */
public class BaseBuilder {

    protected final Configuration configuration;

    protected final TypeAliasRegistry typeAliasRegistry;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = this.configuration.getTypeAliasRegistry();
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
