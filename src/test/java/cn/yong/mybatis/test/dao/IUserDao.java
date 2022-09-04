package cn.yong.mybatis.test.dao;

import cn.yong.mybatis.test.po.User;

import java.util.List;

/**
 * @author Allen
 * @date 2022/8/28
 */
public interface IUserDao {

    User queryUserInfoById(Long id);

    User queryUserInfo(User req);

    List<User> queryUserInfoList();

    int updateUserInfo(User req);

    void insertUserInfo(User req);

    int deleteUserInfoByUserId(String userId);
}
