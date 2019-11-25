package com.wl.web.blog.dao;


import com.wl.web.blog.domain.Vo.ArticleVo;
import com.wl.web.blog.entity.Article;
import com.wl.web.blog.factory.DaoFactory;
import com.wl.web.blog.util.JSoupSpiderAboutJianShu;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class ArticalDaoTest {
private articleDao articleDao= DaoFactory.getArticalInstance();
private Logger logger= LoggerFactory.getLogger(ArticalDaoTest.class);
    @Test
    public void batchInsert() {
        int [] result = null;
        try {
            result = articleDao.batchInsert(JSoupSpiderAboutJianShu.getsomeUser());

        if(result.length!=0){
            logger.info("插入成功"+"一共"+result.length+"条记录");
        }
    } catch (
    SQLException e) {
        logger.error("批量插入失败");
        e.printStackTrace();
    }

}

    @Test
    public void findArticalAll() {
        try {
            List<Article> articalList = articleDao.findArticalAll();
            articalList.forEach(System.out::println);
        } catch (SQLException e) {
            logger.error("查询失败");
            e.printStackTrace();
        }

    }
    @Test
    public void selectHotArticles() throws SQLException {
        List<ArticleVo> articleVoList = articleDao.selectHotArticles();
        System.out.println(articleVoList.size());
    }

    @Test
    public void getArticle() throws SQLException {
        ArticleVo article = articleDao.getArticle(1);
        System.out.println(article);
    }

    @Test
    public void selectByKeywords() throws SQLException {
        List<ArticleVo> articleVoList = articleDao.selectByKeywords("一");
        System.out.println(articleVoList.size());
    }

    @Test
    public void selectByPage() throws SQLException {
        List<ArticleVo> articleVoList = articleDao.selectByPage(1, 10);
        articleVoList.forEach(System.out::println);
    }
}