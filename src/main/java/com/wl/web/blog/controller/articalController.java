package com.wl.web.blog.controller;

import com.google.gson.Gson;
import com.wl.web.blog.dao.ArticalDao;
import com.wl.web.blog.entity.artical;
import com.wl.web.blog.factory.DaoFactory;
import com.wl.web.blog.util.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 小黑
 * @ClassNamearticalController
 * @Description TODO
 * @Date 2019/11/10
 * @Version 1.0
 */
@WebServlet(urlPatterns = "/article")
public class articalController extends HttpServlet {

    private static Logger logger= LoggerFactory.getLogger(UserController.class);
    private ArticalDao articalDao = DaoFactory.getArticalInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            List<artical> articalList =articalDao.findArticalAll();
            Gson gson =new Gson();
            resp.setContentType("application/json;charset=utf-8");
            PrintWriter out = resp.getWriter();
       ResponseObject ro =     ResponseObject.success(200,"成功",articalList);
            out.print(gson.toJson(ro));
            out.close();
        } catch (SQLException e) {
            logger.error("查询失败");

        }


    }
}
