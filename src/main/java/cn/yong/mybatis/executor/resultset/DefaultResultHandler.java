package cn.yong.mybatis.executor.resultset;

import cn.yong.mybatis.reflection.factory.ObjectFactory;
import cn.yong.mybatis.session.ResultContext;
import cn.yong.mybatis.session.ResultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Line
 * @desc 默认结果处理器
 * @date 2022/9/4
 */
public class DefaultResultHandler implements ResultHandler {

    private final List<Object> list;

    public DefaultResultHandler() {
        this.list = new ArrayList<>();
    }

    /**
     * 通过 ObjectFactory 反射工具类，产生特定的 List
     */
    @SuppressWarnings("unchecked")
    public DefaultResultHandler(ObjectFactory objectFactory) {
        this.list = objectFactory.create(List.class);
    }

    @Override
    public void handleResult(ResultContext context) {
        list.add(context.getResultObject());
    }

    public List<Object> getResultList() {
        return list;
    }

}
