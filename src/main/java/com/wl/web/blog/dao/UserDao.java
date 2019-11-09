package com.wl.web.blog.dao;

import com.wl.web.blog.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 小黑
 * @ClassNameUserDao
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 */
public interface UserDao {
    int[] batchInsert(List<User> userList) throws SQLException;

    User findUserByMobile(String mobile) throws SQLException;
}