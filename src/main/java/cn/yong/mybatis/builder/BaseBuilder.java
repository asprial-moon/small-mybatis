package cn.yong.mybatis.builder;

import cn.yong.mybatis.session.Configuration;

/**
 * @author Allen
 * @desc 构建器的基类，建造者模式
 * @date 2022/11/4
 */
public abstract class BaseBuilder {

    protected final Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
