package cn.yong.mybatis.test.dao;

import cn.yong.mybatis.test.po.User;

/**
 * @author Allen
 * @date 2022/11/3
 */
public interface IUserDao {

    User queryUserInfoById(Long uId);

    User selectUserInfoById(Long uId);

}
