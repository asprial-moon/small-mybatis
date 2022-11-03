package cn.yong.mybatis.test.dao;

/**
 * @author Allen
 * @date 2022/11/3
 */
public interface IUserDao {

    String queryUserName(String uId);

    Integer queryUserAge(String uId);
}
