package cn.yong.mybatis.session;

/**
 * @author Allen
 * @desc 工厂模式接口，构建SqlSession的工厂
 * @date 2022/11/3
 */
public interface SqlSessionFactory {
    /**
     * 打开一个 session
     * @return
     */
    SqlSession openSession();
}
