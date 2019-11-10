package com.wl.web.blog.dao;

import com.wl.web.blog.entity.artical;
import com.wl.web.blog.factory.DaoFactory;
import com.wl.web.blog.util.JSoupSpider;
import com.wl.web.blog.util.JSoupSpiderAboutJianShu;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class ArticalDaoTest {
private ArticalDao articalDao= DaoFactory.getArticalInstance();
private Logger logger= LoggerFactory.getLogger(ArticalDaoTest.class);
    @Test
    public void batchInsert() {
        int [] result = null;
        try {
            result = articalDao.batchInsert(JSoupSpiderAboutJianShu.getsomeUser());

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
            List<artical> articalList = articalDao.findArticalAll();
            articalList.forEach(System.out::println);
        } catch (SQLException e) {
            logger.error("查询失败");
            e.printStackTrace();
        }

    }
}