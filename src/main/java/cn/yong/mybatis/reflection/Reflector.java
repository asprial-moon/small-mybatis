package cn.yong.mybatis.reflection;

import cn.yong.mybatis.reflection.invoke.Invoker;
import cn.yong.mybatis.reflection.property.PropertyNamer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ReflectPermission;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Line
 * @desc 反射器，属性 get/set 的映射器
 * @date 2022/8/31
 */
public class Reflector {

    private static boolean classCacheEnabled = true;

    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    // 线程安全的缓存
    private static final Map<Class<?>, Reflector> REFLECTOR_MAP = new ConcurrentHashMap<>();

    private Class<?> type;
    // get 属性列表
    private String[] readablePropertyNames = EMPTY_STRING_ARRAY;
    // set 属性列表
    private String[] writeablePropertyNames = EMPTY_STRING_ARRAY;
    // set 方法列表
    private Map<String, Invoker> setMethods = new HashMap<>();
    // get 方法列表
    private Map<String, Invoker> getMethods = new HashMap<>();
    // set 类型列表
    private Map<String, Class<?>> setTypes = new HashMap<>();
    // get 类型列表
    private Map<String, Class<?>> getTypes = new HashMap<>();
    // 构造函数
    private Constructor<?> defaultConstructor;

    private Map<String, String> caseInsensitivePropMap = new HashMap<>();

    public Reflector(Class<?> clazz) {
        this.type = clazz;
        // 加入构造函数
        addDefaultConstructor(clazz);
        // 加入getter
        addGetMethods(clazz);
        // 加入setter
        addSetMethods(clazz);
        // 加入字段
        addFields(clazz);

        readablePropertyNames = getMethods.keySet().toArray(new String[getMethods.keySet().size()]);

    }


    private void addDefaultConstructor(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterTypes().length == 0) {
                if (canAccessPrivateMethods()) {
                    try {
                        constructor.setAccessible(true);
                    } catch (Exception ignore) {
                        // Ignored. This is only a final precaution, nothing we can do
                    }
                }

                if (constructor.isAccessible()) {
                    this.defaultConstructor = constructor;
                }
            }
        }
    }

    private void addGetMethods(Class<?> clazz) {
        Map<String, List<Method>> conflictingGetters = new HashMap<>();
        Method[] methods = getClassMethods(clazz);
        for (Method method : methods) {
            String name = method.getName();
            if (name.startsWith("get") && name.length() > 3) {
                if (method.getParameterTypes().length == 0) {
                    name = PropertyNamer.methodToProperty(name);
                    addMethodConflict(conflictingGetters, name, method);
                }
            } else if (name.startsWith("is") && name.length() > 2) {
                if (method.getParameterTypes().length == 0) {
                    name = PropertyNamer.methodToProperty(name);
                    addMethodConflict(conflictingGetters, name, method);
                }
            }
        }

        resolveGetterConflicts(conflictingGetters);
    }



    private boolean canAccessPrivateMethods() {
        try {
            SecurityManager securityManager = System.getSecurityManager();
            if (null != securityManager) {
                securityManager.checkPermission(new ReflectPermission("suppressAccessChecks"));
            }
        } catch (SecurityException e) {
            return false;
        }
        return true;
    }

    private Method[] getClassMethods(Class<?> clazz) {
        return null;
    }


    private void addMethodConflict(Map<String, List<Method>> conflictingGetters, String name, Method method) {
    }

    private void resolveGetterConflicts(Map<String, List<Method>> conflictingGetters) {
    }

}
