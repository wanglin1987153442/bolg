package com.wl.web.blog.service;

import com.wl.web.blog.domain.Dto.UserDto;
import com.wl.web.blog.entity.User;
import com.wl.web.blog.util.Result;

import java.util.Map;

/**
 * @author 小黑
 * @ClassNameUserService
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 */
public interface UserService {
    /**
     * 用户登录
     * @param userDto
     * @return
     */
   Result signIn(UserDto userDto);
    Map<String,Object> zhuce(User user);


    Result getHotUsers();

    /**
     * 获取分页用户信息
     * @param currentPage
     * @param count
     * @return
     */
    Result selectByPage(int currentPage,int count);

    /**
     * 根据id查询用户详情数据
     * @param id
     * @return
     */
    Result getUser(long id);

    /**
     * 根据昵称或简介模糊搜索用户
     *
     * @param keywords
     * @return
     */
    Result selectByKeywords(String keywords);
}
