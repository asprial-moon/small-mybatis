package cn.yong.mybatis.session;


/**
 * 结果集处理器
 * @author Allen
 * @date 2022/8/30
 */
public interface ResultHandler {

    /**
     * 结果集
     */
    void handleResult(ResultContext context);

}
