package cn.yong.mybatis.mapping;

import cn.yong.mybatis.session.Configuration;
import cn.yong.mybatis.type.JdbcType;
import cn.yong.mybatis.type.TypeHandler;
import cn.yong.mybatis.type.TypeHandlerRegistry;

/**
 * @author Line
 * @desc 参数映射 #{property,  javaType=int, jdbcType=NUMERIC}
 * @date 2022/11/6
 */
public class ParameterMapping {

    private Configuration configuration;
    /**
     * property
     */
    private String property;
    /**
     * javaType = int
     */
    private Class<?> javaType = Object.class;
    /**
     * jdbcType=NUMBER
     */
    private JdbcType jdbcType;

    private TypeHandler<?> typeHandler;

    public ParameterMapping() {
    }

    public static class Builder {
        private ParameterMapping parameterMapping = new ParameterMapping();

        public Builder(Configuration configuration, String property, Class<?> javaType) {
            parameterMapping.configuration = configuration;
            parameterMapping.property = property;
            parameterMapping.javaType = javaType;
        }

        public Builder javaType(Class<?> javaType) {
            parameterMapping.javaType = javaType;
            return this;
        }

        public Builder jdbcType(JdbcType jdbcType) {
            parameterMapping.jdbcType = jdbcType;
            return this;
        }

        public ParameterMapping build() {
            if (parameterMapping.typeHandler == null && parameterMapping.javaType != null) {
                Configuration configuration = parameterMapping.configuration;
                TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
                parameterMapping.typeHandler = typeHandlerRegistry.getTypeHandler(parameterMapping.javaType, parameterMapping.jdbcType);
            }

            return parameterMapping;
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public String getProperty() {
        return property;
    }

    public Class<?> getJavaType() {
        return javaType;
    }

    public JdbcType getJdbcType() {
        return jdbcType;
    }

    public TypeHandler<?> getTypeHandler() {
        return typeHandler;
    }
}
