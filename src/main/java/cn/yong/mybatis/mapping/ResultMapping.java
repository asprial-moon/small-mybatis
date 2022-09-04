package cn.yong.mybatis.mapping;

import cn.yong.mybatis.session.Configuration;
import cn.yong.mybatis.type.JdbcType;
import cn.yong.mybatis.type.TypeHandler;

/**
 * @author Line
 * @desc 结果映射
 * @date 2022/9/4
 */
public class ResultMapping {

    private Configuration configuration;
    private String property;
    private String column;
    private Class<?> javaType;
    private JdbcType jdbcType;
    private TypeHandler<?> typeHandler;

    ResultMapping() {
    }

    public  static class Builder {
        private ResultMapping resultMapping = new ResultMapping();
    }

}
