package cn.yong.mybatis.session;

/**
 * @author Line
 * @desc 分页记录限制
 * @date 2022/9/4
 */
public class RowBounds {

    public static final int NO_ROW_OFFSET = 0;
    public static final int NO_ROW_LIMIT = Integer.MAX_VALUE;
    public static final RowBounds DEFAULT = new RowBounds();
    /**
     * offset,limit就等于一般分页的start,limit
     */
    private int offset;
    private int limit;

    public RowBounds() {
        this.offset = NO_ROW_OFFSET;
        this.limit = NO_ROW_LIMIT;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }
}
