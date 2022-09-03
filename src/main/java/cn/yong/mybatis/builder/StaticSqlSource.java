package cn.yong.mybatis.builder;

import cn.yong.mybatis.mapping.BoundSql;
import cn.yong.mybatis.mapping.ParameterMapping;
import cn.yong.mybatis.mapping.SqlSource;
import cn.yong.mybatis.session.Configuration;

import java.util.List;

/**
 * @author Line
 * @desc 静态SQL源码
 * @date 2022/9/3
 */
public class StaticSqlSource implements SqlSource {

    private String sql;
    private List<ParameterMapping> parameterMappings;
    private Configuration configuration;

    public StaticSqlSource(Configuration configuration, String sql) {
        this(configuration, sql, null);
    }

    public StaticSqlSource(Configuration configuration, String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
        this.configuration = configuration;
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return new BoundSql(configuration, sql, parameterMappings, parameterObject);
    }
}
