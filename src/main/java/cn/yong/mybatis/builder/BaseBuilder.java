package cn.yong.mybatis.builder;

import cn.yong.mybatis.session.Configuration;

/**
 * 构建器的基类，建造者模式
 * @author Allen
 * @date 2022/8/28
 */
public class BaseBuilder {

    protected final Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
