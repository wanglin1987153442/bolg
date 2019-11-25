package com.wl.web.blog.dao;

import com.wl.web.blog.entity.User;
import com.wl.web.blog.factory.DaoFactory;
import com.wl.web.blog.util.JSoupSpider;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

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

    @Test
    public void addUser() {
        User user =new User();
        user.setMobile("1806997128");
        user.setPassword("123456");

        try {
            int result=userDao.addUser(user);

            logger.info("成功"+user.getMobile()+"    "
                    +user.getPassword()+"  "
            +user.getId());

        } catch (SQLException e) {
            logger.info("注册失败");
            e.printStackTrace();
        }
    }


    @Test
    public void selectHotUsers() throws SQLException {
        List<User> userList = userDao.selectHotUsers();
        userList.forEach(System.out::println);
    }

    @Test
    public void selectByKeywords() throws SQLException{
        List<User> userList = userDao.selectByKeywords("王");
        System.out.println(userList.size());
    }
}
