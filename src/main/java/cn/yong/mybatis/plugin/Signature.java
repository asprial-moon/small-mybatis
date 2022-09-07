package cn.yong.mybatis.plugin;

/**
 * @author Line
 * @desc 方法签名
 * @date 2022/9/7
 */
public @interface Signature {

    /**
     * 被拦截类
     * @return
     */
    Class<?> type();

    /**
     * 被拦截类方法
     * @return
     */
    String method();

    /**
     * 被拦截类的方法的参数
     * @return
     */
    Class<?>[] args();
}
