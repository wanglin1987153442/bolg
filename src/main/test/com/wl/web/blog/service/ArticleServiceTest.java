package com.wl.web.blog.service;

import com.wl.web.blog.factory.ServiceFactory;
import com.wl.web.blog.util.Result;
import org.junit.Test;

/**
 * @author 小黑
 * @ClassNamesada
 * @Description TODO
 * @Date 2019/11/25
 * @Version 1.0
 */
public class ArticleServiceTest {
    private ArticleService articleService = ServiceFactory.getArticleServiceInstance();

    @Test
    public void getHotArticles() {
        Result result =  articleService.getHotArticles();
        System.out.println(result.getData());
    }

    @Test
    public void getPageArticles() {
    }

    @Test
    public void getArticle() {
    }
}