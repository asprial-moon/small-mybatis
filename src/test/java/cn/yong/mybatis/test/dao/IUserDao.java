package cn.yong.mybatis.test.dao;

/**
 * @author Allen
 * @date 2022/8/28
 */
public interface IUserDao {

    String queryUserName(String uId);

    String queryUserAge(String uId);

}
