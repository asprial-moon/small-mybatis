package cn.yong.mybatis.cache.decorators;

import cn.yong.mybatis.cache.Cache;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Allen
 * @Desc FIFO (first in, first out) cache decorator
 *  先进先出，按对象进入缓存顺序移除过期对象
 * @date 2022/9/8
 */
public class FifoCache implements Cache {

    private final Cache delegate;
    private Deque<Object> keyList;
    private int size;

    public FifoCache(Cache delegate) {
        this.delegate = delegate;
        this.keyList = new ArrayDeque<>();
        this.size = 1024;
    }

    @Override
    public String getId() {
        return delegate.getId();
    }

    @Override
    public void putObject(Object key, Object value) {
        cycleKeyList(key);
        delegate.putObject(key, value);
    }

    @Override
    public Object getObject(Object key) {
        return delegate.getObject(key);
    }

    @Override
    public Object removeObject(Object key) {
        return delegate.removeObject(key);
    }

    @Override
    public void clear() {
        delegate.clear();
        keyList.clear();
    }

    @Override
    public int getSize() {
        return delegate.getSize();
    }

    public void setSize(int size) {
        this.size = size;
    }

    private void cycleKeyList(Object key) {
        keyList.addLast(key);
        if (keyList.size() > size) {
            Object oldestKey = keyList.removeFirst();
            delegate.removeObject(oldestKey);
        }
    }
}
