package cn.yong.mybatis.datasource.pooled;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * @author Line
 * @desc 池化代理的链接
 * @date 2022/11/7
 */
public class PooledConnection implements InvocationHandler {
    private static final String CLOSE = "close";
    private static final Class<?>[] IFACES = new Class<?>[]{Connection.class};
    private int haseCode = 0;
    private PooledDataSource dataSource;
    /**
     * 真实的链接
     */
    private Connection realConnection;
    /**
     * 代理的链接
     */
    private Connection proxyConnection;
    private long checkoutTimestamp;
    private long createdTimestamp;
    private long lastUsedTimestamp;
    private long connectionTypeCode;
    private boolean valid;

    public PooledConnection(Connection connection, PooledDataSource dataSource) {
        this.haseCode = connection.hashCode();
        this.realConnection = connection;
        this.dataSource = dataSource;
        this.createdTimestamp = System.currentTimeMillis();
        this.lastUsedTimestamp = System.currentTimeMillis();
        this.valid = true;
        this.proxyConnection = (Connection) Proxy.newProxyInstance(Connection.class.getClassLoader(), IFACES, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if (CLOSE.hashCode() == methodName.hashCode() && CLOSE.equals(methodName)) {
//            dataSource
        }
        return null;
    }
}
