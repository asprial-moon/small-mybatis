package cn.yong.mybatis.mapping;

/**
 * @author Allen
 * @desc 执行类型
 * @date 2022/11/4
 */
public enum SqlCommandType {
    /**
     * 未知
     */
    UNKNOWN,
    /**
     * 插入
     */
    INSERT,
    /**
     * 更新
     */
    UPDATE,
    /**
     * 删除
     */
    DELETE,
    /**
     * 查找
     */
    SELECT,
}
