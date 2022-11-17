package cn.yong.mybatis.executor.result;

import cn.yong.mybatis.session.ResultContext;

/**
 * @author Allen
 * @desc 默认结果上下文
 * @date 2022/11/17
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
