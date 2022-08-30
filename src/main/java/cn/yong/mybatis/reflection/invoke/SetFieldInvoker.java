package cn.yong.mybatis.reflection.invoke;

import java.lang.reflect.Field;

/**
 * @author Line
 * @desc setter 调用者
 * @date 2022/8/31
 */
public class SetFieldInvoker implements Invoker {

    private Field field;

    public SetFieldInvoker(Field field) {
        this.field = field;
    }

    @Override
    public Object invoke(Object target, Object[] args) throws Exception {
        field.set(target, args[0]);
        return null;
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }
}
