package com.wl.web.blog.verify;

import com.wl.web.blog.util.UserDataUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * @author 小黑
 * @ClassNameCodeController
 * @Description TODO
 * @Date 2019/11/20
 * @Version 1.0
 */
//@WebServlet(urlPatterns = "/code")
public class CodeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = UserDataUtil.getCharAndNumr(4);
        HttpSession session =req.getSession();
        System.out.println(session.getId());
        session.setAttribute("code" ,code);
        BufferedImage img =imageUtil.getImages(code ,200,100);
        //设resp的content type
        resp.setContentType("image/jpg");
        //将图片通过输出流返回给客户端

     OutputStream outputStream =  resp.getOutputStream();
        ImageIO.write(img,"jpg",outputStream);
        outputStream.close();

    }
}
