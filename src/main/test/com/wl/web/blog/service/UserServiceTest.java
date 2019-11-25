package com.wl.web.blog.service;

import com.wl.web.blog.domain.Dto.UserDto;
import com.wl.web.blog.entity.User;
import com.wl.web.blog.factory.ServiceFactory;
import com.wl.web.blog.util.Result;
import org.junit.Test;

import java.util.Map;

public class UserServiceTest {
    private UserService userService = ServiceFactory.getUserServiceInstance();

    @Test
    public void signIn() {
        UserDto userDao = new UserDto();
        userDao.setMobile("13951905171");
        userDao.setPassword("111");
        Result result = userService.signIn(userDao);
        System.out.println("code:" + result.getCode() + "," + "msg:" + result.getMsg());
    }

    @Test
    public void getHotUsers() {
        Result result = userService.getHotUsers();
        System.out.println(result);
    }

    @Test
    public void zhuce() {
        User user = new User();
        user.setMobile("21321312321331");
        user.setPassword("111111111111");
        Map<String,Object> map= userService.zhuce(user);
        System.out.println(map);
    }
}


