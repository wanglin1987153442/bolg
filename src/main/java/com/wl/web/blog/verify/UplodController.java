package com.wl.web.blog.verify;


import com.wl.web.blog.util.FileUtil;

import javax.servlet.ServletException;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author 小黑
 * @ClassNameUplodController
 * @Description TODO
 * @Date 2019/11/21
 * @Version 1.0
 */
@MultipartConfig
//@WebServlet(urlPatterns = "/api/upload")
public class UplodController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Collection<Part> parts = req.getParts();
        LocalDate localDate = LocalDate.now();



        for (Part part: parts) {
            long maxFileSize = 1024 * 1024 * 1;
            long size = part.getSize();

            if (size > maxFileSize) {
                resp.setContentType("image/jpg");
                req.setAttribute("msg", "此文件超出最大上传限定请重新上传");
                req.getRequestDispatcher("/upload.jsp").forward(req, resp);
            } else {
                String contentType = part.getContentType();
                String name = part.getSubmittedFileName();
                String path = req.getSession().getServletContext().getRealPath("");
                String newpath = path + localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);

                FileUtil.createFile(newpath);
                String houzhui = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf("."));
                String uuid = UUID.randomUUID().toString();
                String newname = uuid + houzhui;
                part.write(newpath + "/" + newname);

                req.setAttribute("url", "http://localhost:8080/" + localDate.format(DateTimeFormatter.ISO_LOCAL_DATE) + "/" + name);

            }
        }
            resp.setContentType("image/jpg");
            req.setAttribute("msg", "上传成功");
            req.getRequestDispatcher("/upload.jsp").forward(req, resp);

    }
}