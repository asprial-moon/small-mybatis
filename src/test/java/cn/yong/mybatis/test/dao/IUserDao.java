package cn.yong.mybatis.test.dao;

import cn.yong.mybatis.test.po.User;

/**
 * @author Allen
 * @date 2022/8/28
 */
public interface IUserDao {

    User queryUserInfoById(Long uId);

    User queryUserInfo(User req);
}
