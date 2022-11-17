package cn.yong.mybatis.session;

/**
 * @author Allen
 * @desc 结果上下文
 * @date 2022/11/17
 */
public interface ResultContext {

    /**
     * 获取结果
     */
    Object getResultObject();

    /**
     * 获取记录数
     */
    int getResultCount();

}
