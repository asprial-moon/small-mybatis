package cn.yong.mybatis.builder;

import cn.yong.mybatis.mapping.MappedStatement;
import cn.yong.mybatis.mapping.ResultMap;
import cn.yong.mybatis.mapping.SqlCommandType;
import cn.yong.mybatis.mapping.SqlSource;
import cn.yong.mybatis.scripting.LanguageDriver;
import cn.yong.mybatis.session.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Line
 * @desc 映射构建器助手，建造者
 * @date 2022/11/16
 */
public class MapperBuilderAssistant extends BaseBuilder {
    private String currentNamespace;
    private String resource;

    public MapperBuilderAssistant(Configuration configuration, String resource) {
        super(configuration);
        this.resource = resource;
    }

    public String getCurrentNamespace() {
        return currentNamespace;
    }

    public void setCurrentNamespace(String currentNamespace) {
        this.currentNamespace = currentNamespace;
    }

    public String applyCurrentNamespace(String base, boolean isReference) {
        if (base == null) {
            return null;
        }
        if (isReference) {
            if (base.contains(".")) return base;
        }
        return currentNamespace + "." + base;
    }

    /**
     * 添加映射器语句
     */
    public MappedStatement addMappedStatement(
            String id,
            SqlSource sqlSource,
            SqlCommandType sqlCommandType,
            Class<?> parameterType,
            String resultMap,
            Class<?> resultType,
            LanguageDriver lang
    ) {
        // 给id加上namespace前缀：cn.yong.mybatis.test.dao.IUserDao.queryUserInfoById
        id = applyCurrentNamespace(id, false);
        MappedStatement.Builder statementBuilder = new MappedStatement.Builder(configuration, id, sqlCommandType, sqlSource, resultType);

        // 结果映射，给MappedStatement#resultMaps
        setStatementResultMap(resultMap, resultType, statementBuilder);

        MappedStatement statement = statementBuilder.build();
        // 映射语句信息，建造完存放到配置项中
        configuration.addMappedStatement(statement);

        return statement;
    }

    private void setStatementResultMap(
            String resultMap,
            Class<?> resultType,
            MappedStatement.Builder statementBuilder) {
        // 因为暂时还没有在Mapper XML中配置Map返回结果，所以这里返回的是 null
        resultMap = applyCurrentNamespace(resultMap, true);

        List<ResultMap> resultMaps = new ArrayList<>();
        if (resultMap == null) {
            // TODO: 暂无Map结果映射配置，本章节不添加如此逻辑
        }
        /**
         * 通常使用resultType 即可满足大部分场景
         * <select id = "queryUserInfoById" resultType="cn.yong.mybatis.test.po.User>
         * 使用resultType 的情况下，Mybatis 会自动创建一个ResultMap，基于属性名称映射列到 JavaBean 的属性上
         */
        else if (resultType != null){
            ResultMap.Builder inlineResultMapBuilder = new ResultMap.Builder(
                    configuration,
                    statementBuilder.id() + "-Inline",
                    resultType,
                    new ArrayList<>());
            resultMaps.add(inlineResultMapBuilder.build());
        }
        statementBuilder.resultMaps(resultMaps);
    }
}
