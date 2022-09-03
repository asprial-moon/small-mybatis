package cn.yong.mybatis.mapping;

/**
 * @author Line
 * @desc SQL源码
 * @date 2022/9/3
 */
public interface SqlSource {

    BoundSql getBoundSql(Object parameterObject);

}
