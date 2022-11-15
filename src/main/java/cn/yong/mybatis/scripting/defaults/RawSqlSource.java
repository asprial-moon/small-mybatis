package cn.yong.mybatis.scripting.defaults;

import cn.yong.mybatis.mapping.BoundSql;
import cn.yong.mybatis.mapping.SqlSource;
import cn.yong.mybatis.scripting.xmltags.SqlNode;
import cn.yong.mybatis.session.Configuration;

/**
 * @author Allen
 * @desc 原始SQL源码，比 DynamicSqlSource 动态处理快
 * @date 2022/11/15
 */
public class RawSqlSource implements SqlSource {

    private final SqlSource sqlSource;

    public RawSqlSource(Configuration configuration, SqlNode rootSqlNode, Class<?> parameterType) {
        new SqlSource
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return null;
    }
}
