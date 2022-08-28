package cn.yong.mybatis.mapping;

/**
 * @author Allen
 * @date 2022/8/28
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
