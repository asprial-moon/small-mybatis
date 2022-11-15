package cn.yong.mybatis.mapping;

/**
 * @author Allen
 * @desc SQL源码
 * @date 2022/11/15
 */
public interface SqlSource {

    BoundSql getBoundSql(Object parameterObject);

}
