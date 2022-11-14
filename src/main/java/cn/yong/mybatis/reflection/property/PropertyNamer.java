package cn.yong.mybatis.reflection.property;

import java.util.Locale;

/**
 * @author Line
 * @desc 属性命名器
 * @date 2022/11/8
 */
public class PropertyNamer {
    public static String methodToProperty(String name) {
        if (name.startsWith("is")) {
            name = name.substring(2);
        } else if (name.startsWith("get") || name.startsWith("set")) {
            name = name.substring(3);
        } else {
            throw new RuntimeException("Error parsing property name '" + name + "'. Didn't start with 'is',, 'get' or 'set'.");
        }

        /**
         * 如果只有1个字母，转换为小写
         * 如果大于1个字母，第二个字母非大写，转换为小写
         */
        if (name.length() == 1 || (name.length() > 1 && !Character.isUpperCase(name.charAt(1)))) {
            name = name.substring(0, 1).toLowerCase(Locale.ENGLISH) + name.substring(1);
        }

        return name;
    }

    /**
     * 判断开头get/set/is
     * @param name
     * @return
     */
    public static boolean isProperty(String name) {
        return name.startsWith("get") || name.startsWith("set") || name.startsWith("is");
    }

    /**
     * 是否为 getter
     * @param name
     * @return
     */
    public static boolean isGetter(String name) {
        return name.startsWith("get") || name.startsWith("is");
    }

    /**
     * 是否为 setter
     * @param name
     * @return
     */
    public static boolean isSetter(String name) {
        return name.startsWith("set");
    }
}
