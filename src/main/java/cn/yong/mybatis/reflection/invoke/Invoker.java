package cn.yong.mybatis.reflection.invoke;

/**
 * @author Line
 * @desc 调用者
 * @date 2022/11/8
 */
public interface Invoker {

    Object invoke(Object target, Object[] args) throws Exception;

    Class<?> getType();

}
