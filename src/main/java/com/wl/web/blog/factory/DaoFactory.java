package com.wl.web.blog.factory;

import com.wl.web.blog.dao.ArticalDao;
import com.wl.web.blog.dao.UserDao;
import com.wl.web.blog.dao.UserDaoImpl.UserDaoImpl;
import com.wl.web.blog.dao.articalDaoImpl.articalDaoImpl;

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
        public static ArticalDao getArticalInstance(){return  new articalDaoImpl();
        }
        }
