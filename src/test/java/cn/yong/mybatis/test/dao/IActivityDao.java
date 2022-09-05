package cn.yong.mybatis.test.dao;

import cn.yong.mybatis.test.po.Activity;

public interface IActivityDao {

    Activity queryActivityById(Long activityId);

}
