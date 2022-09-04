package cn.yong.mybatis.session;

/**
 * @author Line
 * @desc 结果上下文
 * @date 2022/9/4
 */
public interface ResultContext {

    /**
     * 获取结果
     * @return
     */
    Object getResultObject();

    /**
     * 获取记录数
     * @return
     */
    int getResultCount();
}
