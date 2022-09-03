package cn.yong.mybatis.scripting.defaults;

import cn.yong.mybatis.builder.SqlSourceBuilder;
import cn.yong.mybatis.mapping.BoundSql;
import cn.yong.mybatis.mapping.SqlSource;
import cn.yong.mybatis.scripting.xmltags.DynamicContext;
import cn.yong.mybatis.scripting.xmltags.MixedSqlNode;
import cn.yong.mybatis.session.Configuration;

import java.util.HashMap;

/**
 * @author Line
 * @desc 原始SQL源码，比DynamicSqlSource动态SQL处理快
 * @date 2022/9/3
 */
public class RawSqlSource implements SqlSource {

    private final SqlSource sqlSource;

    public RawSqlSource(Configuration configuration, MixedSqlNode rootSqlNode, Class<?> parameterType) {
        this(configuration, geSql(configuration, rootSqlNode), parameterType);
    }

    public RawSqlSource(Configuration configuration, String sql, Class<?> parameterType) {
        SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
        Class<?> clazz = parameterType == null ? Object.class : parameterType;
        sqlSource = sqlSourceParser.parse(sql, clazz, new HashMap<>());
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return sqlSource.getBoundSql(parameterObject);
    }

    private static String geSql(Configuration configuration, MixedSqlNode rootSqlNode) {
        DynamicContext context = new DynamicContext(configuration, null);
        rootSqlNode.apply(context);
        return context.getSql();
    }
}
