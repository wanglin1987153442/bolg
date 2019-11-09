package com.wl.web.blog.controller;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author 小黑
 * @ClassNameStudentController
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 */
//@WebServlet(urlPatterns = "/student")
//public class StudentController extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        StudentService studentService = ServiceFactory.getStudentServiceInstance();
//        List<Student> studentList = studentService.listStudents();
//        Gson gson =new Gson();
//        resp.setContentType("application/json;charset=utf-8");
////        resp.setContentType("text/plain;charset=utf-8");
//        PrintWriter out = resp.getWriter();
//        out.print(gson.toJson(studentList));
//        out.close();

//    }
//}
