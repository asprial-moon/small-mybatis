package cn.yong.mybatis.executor.keygen;

import cn.yong.mybatis.executor.Executor;
import cn.yong.mybatis.mapping.MappedStatement;

import java.sql.Statement;

/**
 * @author Line
 * @desc 不用键值生成器
 * @date 2022/9/6
 */
public class NoKeyGenerator implements KeyGenerator {
    @Override
    public void processBefore(Executor executor, MappedStatement ms, Statement stmt, Object parameter) {
        // Do Nothing
    }

    @Override
    public void processAfter(Executor executor, MappedStatement ms, Statement stmt, Object parameter) {
        // Do Nothing
    }
}
