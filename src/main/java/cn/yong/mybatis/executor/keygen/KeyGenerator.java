package cn.yong.mybatis.executor.keygen;

import cn.yong.mybatis.executor.Executor;
import cn.yong.mybatis.mapping.MappedStatement;

import java.sql.Statement;

/**
 * @author Line
 * @desc 键值生成器接口
 * @date 2022/9/6
 */
public interface KeyGenerator {

    /**
     * 针对sequence主键而言，而执行insert sql前必须指定一个主键值要给插入的记录。
     * @param executor
     * @param ms
     * @param stmt
     * @param parameter
     */
    void processBefore(Executor executor, MappedStatement ms, Statement stmt, Object parameter);

    /**
     * 针对自增主键的表，在插入时不需要主键，而是在插入过程自动获取一个自增的主键
     * @param executor
     * @param ms
     * @param stmt
     * @param parameter
     */
    void processAfter(Executor executor, MappedStatement ms, Statement stmt, Object parameter);

}
