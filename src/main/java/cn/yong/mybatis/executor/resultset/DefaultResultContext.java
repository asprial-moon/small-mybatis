package cn.yong.mybatis.executor.resultset;

import cn.yong.mybatis.session.ResultContext;

/**
 * @author Line
 * @desc 默认结果上下文
 * @date 2022/9/4
 */
public class DefaultResultContext implements ResultContext {

    private Object resultObject;

    private int resultCount;

    public DefaultResultContext() {
        this.resultObject = null;
        this.resultCount = 0;
    }

    @Override
    public Object getResultObject() {
        return resultObject;
    }

    @Override
    public int getResultCount() {
        return resultCount;
    }

    public void nextResultObject(Object resultObject) {
        resultCount ++;
        this.resultObject = resultObject;
    }
}
