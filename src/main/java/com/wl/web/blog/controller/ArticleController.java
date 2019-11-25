package com.wl.web.blog.controller;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import com.wl.web.blog.dao.articleDao;

import com.wl.web.blog.entity.Article;
import com.wl.web.blog.factory.DaoFactory;
import com.wl.web.blog.factory.ServiceFactory;

import com.wl.web.blog.service.ArticleService;
import com.wl.web.blog.util.ResponseObject;
import com.wl.web.blog.util.Result;
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
import java.util.List;

/**
 * @author 小黑
 * @ClassNamearticalController
 * @Description TODO
 * @Date 2019/11/10
 * @Version 1.0
 */




    @WebServlet(urlPatterns = {"/api/article", "/api/article/*"})
    public class ArticleController extends HttpServlet {
        private ArticleService articleService = ServiceFactory.getArticleServiceInstance();
        private static Logger logger = LoggerFactory.getLogger(ArticleController.class);

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //取得请求地址
            String uri = req.getRequestURI().trim();
            if ("/api/article".equals(uri)) {
                String page = req.getParameter("page");
                String keywords = req.getParameter("keywords");
                String count = req.getParameter("count");
                if (page != null) {
                    getArticlesByPage(resp, Integer.parseInt(page), Integer.parseInt(count));
                } else if (keywords != null) {
                    getArticlesByKeywords(resp, keywords);
                } else {
                    getHotArticles(req, resp);
                }
            } else {
                getArticle(req, resp);
            }
        }

        private void getHotArticles(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Gson gson = new GsonBuilder().create();
            Result result = articleService.getHotArticles();
            PrintWriter out = resp.getWriter();
            out.print(gson.toJson(result));
            out.close();
        }

        private void getArticlesByPage(HttpServletResponse resp, int page, int count) throws ServletException, IOException {
            Gson gson = new GsonBuilder().create();
            Result result = articleService.getArticlesByPage(page, count);
            PrintWriter out = resp.getWriter();
            out.print(gson.toJson(result));
            out.close();
        }

        private void getArticlesByKeywords(HttpServletResponse resp, String keywords) throws ServletException, IOException {
            Gson gson = new GsonBuilder().create();
            Result result = articleService.selectByKeywords(keywords);
            PrintWriter out = resp.getWriter();
            out.print(gson.toJson(result));
            out.close();
        }

        private void getArticle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String info = req.getPathInfo().trim();
            //取得路径参数
            String id = info.substring(info.indexOf("/") + 1);
            Result result = articleService.getArticle(Long.parseLong(id));
            Gson gson = new GsonBuilder().create();
            PrintWriter out = resp.getWriter();
            out.print(gson.toJson(result));
            out.close();
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          List<Article>articleList =articleService.findAllArticles();
            Gson gson = new Gson();
            resp.setContentType("application/json;charset=utf-8");
            PrintWriter out = resp.getWriter();
            ResponseObject ro = ResponseObject.success(200, "成功",articleList);
            out.print(gson.toJson(ro));
            out.close();


        }
    }
