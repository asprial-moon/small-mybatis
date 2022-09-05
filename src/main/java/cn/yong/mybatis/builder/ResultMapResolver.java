package cn.yong.mybatis.builder;

import cn.yong.mybatis.mapping.ResultMap;
import cn.yong.mybatis.mapping.ResultMapping;

import java.util.List;

/**
 * @author Line
 * @desc 结果映射解析器
 * @date 2022/9/5
 */
public class ResultMapResolver {

    private final MapperBuilderAssistant assistant;
    private String id;
    private Class<?> type;

    private List<ResultMapping> resultMappings;

    public ResultMapResolver(MapperBuilderAssistant assistant, String id, Class<?> type, List<ResultMapping> resultMappings) {
        this.assistant = assistant;
        this.id = id;
        this.type = type;
        this.resultMappings = resultMappings;
    }

    public ResultMap resolve() {
        return assistant.addResultMap(this.id, this.type, this.resultMappings);
    }
}
