package cn.yong.mybatis.reflection.invoke;

/**
 * @author Line
 * @desc 反射调用者
 * @date 2022/8/31
 */
public interface Invoker {

    /**
     * 调用
     * @param target
     * @param args
     * @return
     * @throws Exception
     */
    Object invoke(Object target, Object[] args) throws Exception;

    /**
     * 类型
     * @return
     */
    Class<?> getType();
}
