package cn.yong.mybatis.scripting.xmltags;

import cn.yong.mybatis.parsing.GenericTokenParser;
import cn.yong.mybatis.parsing.TokenHandler;
import cn.yong.mybatis.type.SimpleTypeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

/**
 * @author Line
 * @desc 文本节点SQL节点(CDATA|TEXT)
 * @date 2022/9/6
 */
public class TextSqlNode implements SqlNode {

    private static Logger log = LoggerFactory.getLogger(TextSqlNode.class);

    private String text;

    private Pattern injectionFilter;

    public TextSqlNode(String text) {
        this.text = text;
    }

    public TextSqlNode(String text, Pattern injectionFilter) {
        this.text = text;
        this.injectionFilter = injectionFilter;
    }

    /**
     * 判断是否是动态SQL
     * @return
     */
    public boolean isDynamic() {
        DynamicCheckerTokenParser checker = new DynamicCheckerTokenParser();
        GenericTokenParser parser = createParser(checker);
        parser.parse(text);
        return checker.isDynamic;
    }

    /**
     * 动态SQL检查器
     * @param context
     * @return
     */
    @Override
    public boolean apply(DynamicContext context) {
        GenericTokenParser parser = createParser(new BindingTokenParser(context, injectionFilter));
        context.appendSql(parser.parse(text));
        log.info("动态SQL检查器 context: {},  text: {}", context, text);
        return true;
    }

    private GenericTokenParser createParser(TokenHandler handler) {
        return new GenericTokenParser("${", "}", handler);
    }

    /**
     * 绑定记号解析器
     */
    private static class BindingTokenParser implements TokenHandler {
        private DynamicContext context;
        private Pattern injectionFilter;

        public BindingTokenParser(DynamicContext context, Pattern injectionFilter) {
            this.context = context;
            this.injectionFilter = injectionFilter;
        }

        @Override
        public String handleToken(String content) {
            Object parameter = context.getBindings().get("_parameter");
            if (parameter == null) {
                context.getBindings().put("value", null);
            } else if (SimpleTypeRegistry.isSampleType(parameter.getClass())) {
                context.getBindings().put("value", parameter);
            }
            // 从缓存里取得值
            Object value = OgnlCache.getValue(content, context.getBindings());
            String srtValue = (value == null ? "" : String.valueOf(value));
            checkInjection(srtValue);
            return null;
        }

        /**
         * 检查是否匹配正则表达式
         * @param value
         */
        private void checkInjection(String value) {
            if (injectionFilter != null && !injectionFilter.matcher(value).matches()) {
                throw new RuntimeException("Invalid input. Please conform to regex" + injectionFilter.pattern());
            }
        }
    }

    private static class DynamicCheckerTokenParser implements TokenHandler {

        private boolean isDynamic;

        public DynamicCheckerTokenParser() {
            // Prevent Synthetic Access
        }

        public boolean isDynamic() {
            return isDynamic;
        }

        @Override
        public String handleToken(String content) {
            // 设置 isDynamic为true，即调用了这个类就必定是动态SQL
            return null;
        }
    }
}
