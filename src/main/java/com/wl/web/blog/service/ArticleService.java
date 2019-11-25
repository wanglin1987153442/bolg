package com.wl.web.blog.service;

import com.wl.web.blog.entity.Article;
import com.wl.web.blog.util.Result;

import java.util.List;

/**
 * @author 小黑
 * @ClassNameweqew
 * @Description TODO
 * @Date 2019/11/25
 * @Version 1.0
 */
public interface ArticleService {
    /**
     * 获取热门文章
     *
     * @return
     */
    Result getHotArticles();

    /**
     * 获取分页文章
     *
     * @param currentPage
     * @param count
     * @return
     */
    Result getArticlesByPage(int currentPage, int count);

    /**
     * 获取文章详情
     *
     * @param id
     * @return
     */
    Result getArticle(long id);


    /**
     * 根据标题或摘要模糊查询文章
     *
     * @param keywords
     * @return
     */
    Result selectByKeywords(String keywords);

    List<Article> findAllArticles();
}