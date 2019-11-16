package com.wl.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wl.web.blog.dao.UserDao;
import com.wl.web.blog.domain.UserDto;
import com.wl.web.blog.entity.User;
import com.wl.web.blog.factory.DaoFactory;
import com.wl.web.blog.factory.ServiceFactory;
import com.wl.web.blog.service.UserService;
import com.wl.web.blog.util.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author 小黑
 * @ClassNameUserController
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 */
@WebServlet(urlPatterns = "/sign-in")
public class UserController extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService = ServiceFactory.getUserServiceInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        logger.info("登录用户信息：" + stringBuilder.toString());
        Gson gson = new GsonBuilder().create();
        UserDto userDto = gson.fromJson(stringBuilder.toString(), UserDto.class);
        Map<String, Object> map = userService.signIn(userDto);
        String msg = (String) map.get("msg");
        System.out.println(msg);
        ResponseObject ro;
        switch (msg) {
            case "登录成功":
                ro = ResponseObject.success(200, msg, map.get("data"));
                break;
            case "密码错误":
            case"密码为空":
            case "手机号不存在":
            default:
                ro = ResponseObject.success(200, msg);
        }
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(ro));
        out.close();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader=req.getReader();
        StringBuilder stringBuilder=new StringBuilder();
        String line =null;
        while((line=reader.readLine())!=null){
            stringBuilder.append(line);
        }
        System.out.println(stringBuilder.toString());
        Gson gson = new GsonBuilder().create();
        User user=gson.fromJson(stringBuilder.toString(),User.class);
        Map<String, Object> map = userService.zhuce(user);
        String msg = (String)map.get("msg");
        ResponseObject ro;
        switch(msg){
            case "注册成功":
                ro=ResponseObject.success(200,msg, user);
                break;
            case "注册失败":
                ro = ResponseObject.error(250,msg);
                break;
            default:
                ro=ResponseObject.success(200,msg);
        }
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out=resp.getWriter();
        out.print(gson.toJson(ro));
        out.close();
   }
    }

