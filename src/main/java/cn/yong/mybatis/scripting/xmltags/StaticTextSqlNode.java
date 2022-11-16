package cn.yong.mybatis.scripting.xmltags;

/**
 * @author Allen
 * @desc 静态文本SQL节点
 * @date 2022/11/16
 */
public class StaticTextSqlNode implements SqlNode {

    private String text;

    public StaticTextSqlNode(String text) {
        this.text = text;
    }

    @Override
    public boolean apply(DynamicContext context) {
        context.appendSql(text);
        return true;
    }
}
