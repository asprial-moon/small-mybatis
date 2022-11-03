package cn.yong.mybatis.binding;

import cn.yong.mybatis.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Allen
 * @desc 映射器代理类
 * @date 2022/11/3
 * <ul>
 *     <li> 通过实现 InvocationHandler#invoke 代理类接口，封装操作逻辑的方式，对外接口提供数据库操作对象
 *     <li> 目前我们这里只是简单的封装了一个sqlSession的 Map 对象，你可以想象所有的数据库语句操作，都是通过 接口名称 + 方法名称作key，
 *          操作作为逻辑的方式进行使用的。那么在反射调用中则获取对应的操作直接执行并返回结果即可。当然这还只是最核心的简化流程
 *     <li> 另外这里要注意如果是 Object 提供的 toString、hashCode等方法是不需要代理执行的。
 *          所以添加Object.class.equals(method.getDeclaringClass()) 判断
 * </ul>
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {
    private static final long serialVersionUID = -6424540398559729838L;

    private SqlSession sqlSession;

    private final Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            return sqlSession.selectOne(method.getName(), args);
        }
    }
}
