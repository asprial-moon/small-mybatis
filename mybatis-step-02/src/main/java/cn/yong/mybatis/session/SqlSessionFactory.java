package cn.yong.mybatis.session;

/**
 * 是一个简单工厂模式，用于提供SqlSession服务，屏蔽创建细节，延迟创建过程
 * @author Allen
 * @date 2022/8/28
 */
public interface SqlSessionFactory {

    /**
     * 发开一个session
     * @return
     */
    SqlSession openSession();
}
