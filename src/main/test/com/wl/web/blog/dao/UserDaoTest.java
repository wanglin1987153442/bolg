package com.wl.web.blog.dao;

import com.wl.web.blog.factory.DaoFactory;
import com.wl.web.blog.util.JSoupSpider;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserDaoTest {
    private  static Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
    private UserDao userDao = DaoFactory.getUserDaoInstance();
    @Test
    public void batchInsert() {

        try {
            int [] result = userDao.batchInsert(JSoupSpider.getUsers());
            if(result.length!=0){
                logger.info("插入成功"+"一共"+result.length+"条记录");
            }
        } catch (SQLException e) {
            logger.error("批量插入失败");
        }


    }
    }
