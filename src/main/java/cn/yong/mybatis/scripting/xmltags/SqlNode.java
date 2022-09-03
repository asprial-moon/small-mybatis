package cn.yong.mybatis.scripting.xmltags;

/**
 * @author Line
 * @desc SQL 节点
 * @date 2022/9/3
 */
public interface SqlNode {

    boolean apply(DynamicContext context);

}
