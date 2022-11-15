package cn.yong.mybatis.mapping;

import cn.yong.mybatis.reflection.MetaObject;
import cn.yong.mybatis.session.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Line
 * @desc 绑定SQL，是从SqlSource而来，将动态内容都处理完成得到的SQL语句字符串，其中包括？，还有绑定的参数
 * @date 2022/11/6
 */
public class BoundSql {

    private String sql;
    private List<ParameterMapping> parameterMappings;
    private Object parameterObject;
    private Map<Integer, String> additionalParameters;
    private MetaObject metaParameters;

    public BoundSql(Configuration configuration, String sql, List<ParameterMapping> parameterMappings, Object parameterObject) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
        this.parameterObject = parameterObject;
        this.additionalParameters = new HashMap<>();
        this.metaParameters = configuration.newMetaObject(additionalParameters);
    }


    public String getSql() {
        return sql;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public Object getParameterObject() {
        return parameterObject;
    }

    public boolean hasAdditionalParameter(String name) {
        return metaParameters.hasSetter(name);
    }

    public void setAdditionalParameter(String name, Object value) {
        metaParameters.setValue(name, value);
    }

    public void getAdditionalParameter(String name) {
        metaParameters.getValue(name);
    }
}
