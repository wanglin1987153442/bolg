package com.wl.web.blog.service;

import com.wl.web.blog.domain.UserDto;
import com.wl.web.blog.factory.ServiceFactory;
import com.wl.web.blog.service.UserServiceImpl.UserServiceImpl;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class UserServiceTest {
private UserService userService = ServiceFactory.getUserServiceInstance();
    @Test
    public void signIn() {
        UserDto userDto = new UserDto("18082200736","91d1fe5576df194c8392b68a7dcc3cef");
        Map<String,Object> map= userService.signIn(userDto);
        System.out.println(map);
    }
}