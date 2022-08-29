package cn.yong.mybatis.datasource.pooled;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc 池状态
 * @user Line
 * @date 2022/8/29
 */
public class PoolState {

    protected PooledDataSource dataSource;
    /**
     * 空闲链接
     */
    protected final List<PooledConnection> idleConnections = new ArrayList<>();
    /**
     * 活跃链接
     */
    protected final List<PooledConnection> activeConnections = new ArrayList<>();
    /**
     * 请求次数
     */
    protected long requestCount = 0;
    /**
     * 累积请求时间
     */
    protected long accumulatedRequestTime = 0;
    /**
     * 累积结帐时间
     */
    protected long accumulatedCheckoutTime = 0;
    /**
     * 声称的过期连接计数
     */
    protected long claimedOverdueConnectionCount = 0;
    /**
     * 逾期连接累计结账时间
     */
    protected long accumulatedCheckoutTimeOfOverdueConnections = 0;
    /**
     * 累积等待时间
     */
    protected long accumulatedWaitTime = 0;
    /**
     * 不得不等待计数
     */
    protected long hadToWaitCount = 0;
    /**
     * 错误的连接数
     */
    protected long badConnectionCount = 0;

    public PoolState(PooledDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public synchronized long getRequestCount() {
        return requestCount;
    }

    public synchronized long getAverageRequestTime() {
        return requestCount == 0 ? 0 : accumulatedRequestTime / requestCount;
    }

    public synchronized long getAverageWaitTime() {
        return hadToWaitCount == 0 ? 0 : accumulatedWaitTime / hadToWaitCount;
    }

    public synchronized long getHadToWaitCount() {
        return hadToWaitCount;
    }

    public synchronized long getBadConnectionCount() {
        return badConnectionCount;
    }

    public synchronized long getClaimedOverdueConnectionCount() {
        return claimedOverdueConnectionCount;
    }

    public synchronized long getAverageOverdueCheckoutTime() {
        return claimedOverdueConnectionCount == 0 ? 0 : accumulatedCheckoutTimeOfOverdueConnections / claimedOverdueConnectionCount;
    }

    public synchronized long getAverageCheckoutTime() {
        return requestCount == 0 ? 0 : accumulatedCheckoutTime / requestCount;
    }

    public synchronized int getIdleConnectionCount() {
        return idleConnections.size();
    }

    public synchronized int getActiveConnectionCount() {
        return activeConnections.size();
    }

}
