package cn.yong.mybatis.scripting.xmltags;

/**
 * @author Allen
 * @desc SQL 节点
 * @date 2022/11/15
 */
public interface SqlNode {

    boolean apply(DynamicContext context);

}
