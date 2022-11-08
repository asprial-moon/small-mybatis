package cn.yong.mybatis.reflection.invoke;

import java.lang.reflect.Field;

/**
 * @author Line
 * @desc getter 调用
 * @date 2022/11/8
 */
public class GetFieldInvoker implements Invoker {

    private Field field;

    public GetFieldInvoker(Field field) {
        this.field = field;
    }

    @Override
    public Object invoke(Object target, Object[] args) throws Exception {
        return field.get(target);
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }
}
