package cn.yong.mybatis.scripting.defaults;

import cn.yong.mybatis.executor.parameter.ParameterHandler;
import cn.yong.mybatis.mapping.BoundSql;
import cn.yong.mybatis.mapping.MappedStatement;
import cn.yong.mybatis.mapping.ParameterMapping;
import cn.yong.mybatis.reflection.MetaObject;
import cn.yong.mybatis.session.Configuration;
import cn.yong.mybatis.type.JdbcType;
import cn.yong.mybatis.type.TypeHandler;
import cn.yong.mybatis.type.TypeHandlerRegistry;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Line
 * @desc 默认参数处理器
 * @date 2022/9/4
 */
public class DefaultParameterHandler implements ParameterHandler {

    private Logger log = LoggerFactory.getLogger(DefaultParameterHandler.class);

    private final TypeHandlerRegistry typeHandlerRegistry;

    private final MappedStatement mappedStatement;

    private final Object parameterObject;

    private BoundSql boundSql;

    private Configuration configuration;

    public DefaultParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        this.mappedStatement = mappedStatement;
        this.configuration = mappedStatement.getConfiguration();
        this.typeHandlerRegistry = mappedStatement.getConfiguration().getTypeHandlerRegistry();
        this.parameterObject = parameterObject;
        this.boundSql = boundSql;
    }

    @Override
    public Object getParameterObject() {
        return parameterObject;
    }

    @Override
    public void setParameters(PreparedStatement ps) throws SQLException {
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (null != parameterMappings) {
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                String propertyName = parameterMapping.getProperty();
                Object value;
                if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                    value = parameterObject;
                } else {
                    // 通过MetaObject.getValue 反射取得值
                    MetaObject metaObject = configuration.newMateObject(parameterObject);
                    value = metaObject.getValue(propertyName);
                }
                JdbcType jdbcType = parameterMapping.getJdbcType();

                // 设置参数
                log.info("根据每个ParameterMapping中的TypeHandler设置对应的参数信息 value：{}", JSON.toJSONString(value));
                TypeHandler typeHandler = parameterMapping.getTypeHandler();
                typeHandler.setParameter(ps, i + 1, value, jdbcType);
            }
        }
    }
}
