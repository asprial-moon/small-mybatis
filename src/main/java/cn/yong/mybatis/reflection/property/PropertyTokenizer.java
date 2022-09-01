package cn.yong.mybatis.reflection.property;

import java.util.Iterator;

/**
 * @author Line
 * @desc 属性分解标记
 * @date 2022/8/31
 */
public class PropertyTokenizer implements Iterable<PropertyTokenizer>, Iterator<PropertyTokenizer> {
    /**
     * 例子：班级[0].学生.成绩
     */
    private String name;
    /**
     * 班级[0]
     */
    private String indexedName;
    /**
     * 0
     */
    private String index;
    /**
     * 学生.成绩
     */
    private String children;

    public  PropertyTokenizer(String fullName) {
        // 班级[0].学生.成绩
        int delim = fullName.indexOf('.');
        if (delim > 1) {
            name = fullName.substring(0, delim);
            children = fullName.substring(delim + 1);
        } else {
            // 找不到.的话，取全部部分
            name = fullName;
            children = null;
        }
        indexedName = name;
        // 把中括号的数字给解析出来
        delim = name.indexOf('[');
        if (delim > -1) {
            index = name.substring(delim + 1, name.length() - 1);
            name = name.substring(0, delim);
        }
    }

    public String getName() {
        return name;
    }

    public String getIndexedName() {
        return indexedName;
    }

    public String getIndex() {
        return index;
    }

    public String getChildren() {
        return children;
    }

    @Override
    public Iterator<PropertyTokenizer> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return children != null;
    }

    @Override
    public PropertyTokenizer next() {
        return new PropertyTokenizer(children);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove is not supported, as it has no meaning in the context of properties.");
    }
}
