package com.wl.web.blog.service;

import com.wl.web.blog.domain.UserDto;

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
    Map<String,Object> signIn(UserDto userDto);
}
