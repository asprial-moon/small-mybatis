package cn.yong.mybatis.binding;

import cn.yong.mybatis.mapping.MappedStatement;
import cn.yong.mybatis.mapping.SqlCommandType;
import cn.yong.mybatis.session.Configuration;
import cn.yong.mybatis.session.SqlSession;

import java.lang.reflect.Method;

/**
 * 映射器方法
 * @author Allen
 * @date 2022/8/28
 */
public class MapperMethod {

    private final SqlCommand command;

    public MapperMethod(Class<?> mapperInterface, Method method, Configuration configuration) {
        this.command = new SqlCommand(configuration, mapperInterface, method);
    }

    public Object execute(SqlSession sqlSession, Object[] args) {
        Object result = null;
        switch (command.getType()) {
            case INSERT:
                break;
            case DELETE:
                break;
            case UPDATE:
                break;
            case SELECT:
                result = sqlSession.selectOne(command.getName(), args);
            default:
                throw new RuntimeException("Unknown execution method for: " + command.getName());
        }
        return result;
    }

    /**
     * SQL质量
     */
    public static class SqlCommand {
        private final String name;

        private final SqlCommandType type;

        public SqlCommand(Configuration configuration, Class<?> mapperInterface, Method method) {
            String statementName = mapperInterface.getName() + "." + method.getName();
            MappedStatement mappedStatement = configuration.getMappedStatement(statementName);
            this.name = mappedStatement.getId();
            this.type = mappedStatement.getSqlCommandType();
        }

        public String getName() {
            return name;
        }

        public SqlCommandType getType() {
            return type;
        }
    }
}
