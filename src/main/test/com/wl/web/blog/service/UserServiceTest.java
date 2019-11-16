package com.wl.web.blog.service;

import com.wl.web.blog.domain.UserDto;
import com.wl.web.blog.entity.User;
import com.wl.web.blog.factory.ServiceFactory;
import com.wl.web.blog.service.UserServiceImpl.UserServiceImpl;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class UserServiceTest {
private UserService userService = ServiceFactory.getUserServiceInstance();
    @Test
    public void signIn() {
        UserDto userDto = new UserDto("18029300631","0cc17dbf0536c9ea077d8007f85eaa10");
        Map<String,Object> map= userService.signIn(userDto);
        System.out.println(map);
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