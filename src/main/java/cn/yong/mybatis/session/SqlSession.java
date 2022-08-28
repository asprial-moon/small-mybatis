package cn.yong.mybatis.session;

/**
 * 用于定义执行SQL标准，获取映射器以及将来管理事务等方面的操作。基本我们平常使用Mybatis的API接口也都是从这个几口定义的方法进行的
 * 在 SqlSession 中定义用来执行 SQL、获取映射器对象以及后续管理事务操作的标准接口。
 * 目前这个接口中对于数据库的操作仅仅只提供了 selectOne，后续还会有相应其他方法的定义。
 * @author Allen
 * @date 2022/8/28
 */
public interface SqlSession {

    /**
     * Retrieve a single row mapped from the statement key
     * 根据指定的SqlID获取一条记录的封装对象
     * @param statement
     * @return statement sqlID
     * @param <T>  Mapped object 封装之后的对象
     */
    <T> T selectOne(String statement);

    /**
     * Retrieve a single row mapped from the statement key and parameter.
     * 根据指定的SqlID获取一条记录的封装对象，只不过这个方法容许我们可以给sql传递一些参数
     * 一般在实际使用中，这个参数传递的是pojo，或者Map或者ImmutableMap
     * @param statement
     * @param parameter
     * @return Mapped object
     * @param <T> the returned object type
     */
    <T> T selectOne(String statement, Object parameter);

    /**
     * Retrieves current configuration 得到配置
     * @return
     */
    Configuration getConfiguration();

    /**
     * Retrieves a mapper.
     * 得到映射器，这个巧妙的使用了泛型，使得类型安全
     * @param type
     * @return
     * @param <T>
     */
    <T> T getMapper(Class<T> type);
}
