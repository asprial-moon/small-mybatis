package cn.yong.mybatis.builder;

import cn.yong.mybatis.mapping.ParameterMapping;
import cn.yong.mybatis.mapping.SqlSource;
import cn.yong.mybatis.parsing.TokenHandler;
import cn.yong.mybatis.session.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Allen
 * @desc SQL 源码构建器
 * @date 2022/11/15
 */
public class SqlSourceBuilder extends BaseBuilder {

    private static final String parameterProperties = "javaType,jdbcType,mode,numericScale,resultMap,typeHandler,jdbcTypeName";

    public SqlSourceBuilder(Configuration configuration) {
        super(configuration);
    }

    public SqlSource parse(String originalSql, Class<?> paramterType, Map<String, Object> additionalParameters) {

    }

    private static class ParameterMappingTokenHandler extends BaseBuilder implements TokenHandler {
        private List<ParameterMapping> parameterMappings = new ArrayList<>();


        @Override
        public String handleToken(String content) {
            return null;
        }
    }
}
