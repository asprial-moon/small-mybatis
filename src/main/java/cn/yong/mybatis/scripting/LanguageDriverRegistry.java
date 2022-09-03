package cn.yong.mybatis.scripting;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Line
 * @desc 脚本语言注册器
 * @date 2022/9/3
 */
public class LanguageDriverRegistry {

    private final Map<Class<?>, LanguageDriver> LANGUAGE_DRIVER_MAP = new HashMap<>();

    private Class<?> defaultDriverClass = null;

    public void register(Class<?> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("null is not a valid Language Driver");
        }
        if (!LanguageDriver.class.isAssignableFrom(clazz)) {
            throw new RuntimeException(clazz.getName() + " does not implements " + LanguageDriver.class.getName());
        }
        // 如果没注册过，再去注册
        LanguageDriver driver = LANGUAGE_DRIVER_MAP.get(clazz);
        if (driver == null) {
            try {
                // 单例模式，即一个Class只有一个对应的LanguageDriver
                driver = (LanguageDriver) clazz.newInstance();
                LANGUAGE_DRIVER_MAP.put(clazz, driver);
            } catch (Exception ex) {
                throw new RuntimeException("Failed to load language driver for " + clazz.getName(), ex);
            }
        }
    }

    public LanguageDriver getDriver(Class<?> clazz) {
        return LANGUAGE_DRIVER_MAP.get(clazz);
    }

    public LanguageDriver getDefaultDriver() {
        return getDriver(getDefaultDriverClass());
    }

    public Class<?> getDefaultDriverClass() {
        return defaultDriverClass;
    }

    /**
     * Configuration()有调用，默认的为XMLLanguageDriver
     * @param defaultDriverClass
     */
    public void setDefaultDriverClass(Class<?> defaultDriverClass) {
        register(defaultDriverClass);
        this.defaultDriverClass = defaultDriverClass;
    }
}
