package cn.yong.mybatis.builder;

import cn.yong.mybatis.mapping.ParameterMapping;
import cn.yong.mybatis.mapping.SqlSource;
import cn.yong.mybatis.parsing.GenericTokenParser;
import cn.yong.mybatis.parsing.TokenHandler;
import cn.yong.mybatis.reflection.MetaObject;
import cn.yong.mybatis.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author Line
 * @desc SQL源码构建器
 * @date 2022/9/3
 */
public class SqlSourceBuilder extends BaseBuilder {

    private Logger log = LoggerFactory.getLogger(SqlSourceBuilder.class);

    private static final String parameterProperties = "javaType,jdbcType,mode,numericScale,resultMap,typeHandler,jdbcTypeName";

    public SqlSourceBuilder(Configuration configuration) {
        super(configuration);
    }

    public SqlSource parse(String originSql, Class<?> parameterType, HashMap<String, Object> additionalParameters) {
        ParameterMappingTokenHandler handler = new ParameterMappingTokenHandler(configuration, parameterType, additionalParameters);
        log.info("SqlSourceBuilder 解析开始 原SQL: {}", originSql);
        GenericTokenParser parser = new GenericTokenParser("#{", "}", handler);
        String sql = parser.parse(originSql);
        log.info("SqlSourceBuilder 解析结束 原SQL: {}", sql);
        return new StaticSqlSource(configuration, sql, handler.getParameterMappings());
    }

    private static class ParameterMappingTokenHandler extends BaseBuilder implements TokenHandler {

        private List<ParameterMapping> parameterMappings = new ArrayList<>();

        private Class<?> parameterType;

        private MetaObject metaParameters;

        public ParameterMappingTokenHandler(Configuration configuration, Class<?> parameterType, Map<String, Object> additionalParameters) {
            super(configuration);
            this.parameterType = parameterType;
            this.metaParameters = configuration.newMateObject(additionalParameters);
        }

        public List<ParameterMapping> getParameterMappings() {
            return parameterMappings;
        }

        @Override
        public String handleToken(String content) {
            parameterMappings.add(buildParameterMapping(content));
            return "?";
        }

        private ParameterMapping buildParameterMapping(String content) {
            Map<String, String> propertiesMap = new ParameterExpression(content);
            String property = propertiesMap.get("property");
            Class<?> propertyType = parameterType;
            return new ParameterMapping.Builder(configuration, property, propertyType).build();
        }
    }
}
