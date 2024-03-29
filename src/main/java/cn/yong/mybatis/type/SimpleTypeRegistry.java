package cn.yong.mybatis.type;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Line
 * @desc 基本类型注册器
 * @date 2022/9/7
 */
public class SimpleTypeRegistry {

    private static final Set<Class<?>> SIMPLE_TYPE_SET = new HashSet<>();

    static {
        SIMPLE_TYPE_SET.add(String.class);
        SIMPLE_TYPE_SET.add(Byte.class);
        SIMPLE_TYPE_SET.add(Short.class);
        SIMPLE_TYPE_SET.add(Character.class);
        SIMPLE_TYPE_SET.add(Integer.class);
        SIMPLE_TYPE_SET.add(Long.class);
        SIMPLE_TYPE_SET.add(Float.class);
        SIMPLE_TYPE_SET.add(Double.class);
        SIMPLE_TYPE_SET.add(Boolean.class);
        SIMPLE_TYPE_SET.add(Date.class);
        SIMPLE_TYPE_SET.add(Class.class);
        SIMPLE_TYPE_SET.add(BigInteger.class);
        SIMPLE_TYPE_SET.add(BigDecimal.class);
    }

    private SimpleTypeRegistry() {
    }

    /**
     * Tells us if the class passed in is a known common type
     * @param clazz the class to check
     * @return True if the class is known
     */
    public static boolean isSampleType(Class<?> clazz) {
        return SIMPLE_TYPE_SET.contains(clazz);
    }
}
