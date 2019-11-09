package com.wl.web.blog.factory;

import com.wl.web.blog.dao.UserDao;
import com.wl.web.blog.dao.UserDaoImpl.UserDaoImpl;

/**
 * @author 小黑
 * @ClassNameDaoFactory
 * @Description TODO
 * @Date 2019/11/7
 * @Version 1.0
 */
public class DaoFactory {
        public static UserDao getUserDaoInstance(){
        return  new UserDaoImpl();
        }
        }
