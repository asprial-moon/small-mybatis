package cn.yong.mybatis.scripting.defaults;

import cn.yong.mybatis.builder.SqlSourceBuilder;
import cn.yong.mybatis.mapping.BoundSql;
import cn.yong.mybatis.mapping.SqlSource;
import cn.yong.mybatis.scripting.xmltags.DynamicContext;
import cn.yong.mybatis.scripting.xmltags.SqlNode;
import cn.yong.mybatis.session.Configuration;

import java.util.HashMap;

/**
 * @author Allen
 * @desc 原始SQL源码，比 DynamicSqlSource 动态处理快
 * @date 2022/11/15
 */
public class RawSqlSource implements SqlSource {

    private final SqlSource sqlSource;

    public RawSqlSource(Configuration configuration, SqlNode rootSqlNode, Class<?> parameterType) {
        this(configuration, getSql(configuration, rootSqlNode), parameterType);
    }

    public RawSqlSource(Configuration configuration, String sql, Class<?> parameterType) {
        SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
        Class<?> clazz = parameterType == null ? Object.class : parameterType;
        sqlSource = sqlSourceParser.parse(sql, clazz, new HashMap<>());
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return null;
    }

    private static SqlNode getSql(Configuration configuration, SqlNode rootSqlNode) {
        DynamicContext context = new DynamicContext(configuration, null);
        rootSqlNode.apply(context);
        return context.getSql();
    }
}
