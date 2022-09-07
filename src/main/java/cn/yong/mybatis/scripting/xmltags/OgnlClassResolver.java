package cn.yong.mybatis.scripting.xmltags;

import cn.yong.mybatis.io.Resources;
import ognl.ClassResolver;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Line
 * @desc
 * @date 2022/9/6
 */
public class OgnlClassResolver implements ClassResolver {

    private Map<String, Class<?>> classes = new HashMap<>(101);

    @Override
    public Class classForName(String className, Map context) throws ClassNotFoundException {
        Class<?> result = null;
        if ((result = classes.get(className)) == null) {
            try {
                result = Resources.classForName(className);
            } catch (ClassNotFoundException ex) {
                if (className.indexOf('.') == -1) {
                    result = Resources.classForName("java.lang." + className);
                    classes.put("java.lang." + className, result);
                }
            }
        }
        return result;
    }
}
