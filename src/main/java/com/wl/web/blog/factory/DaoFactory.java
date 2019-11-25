package com.wl.web.blog.factory;


import com.wl.web.blog.dao.Impl.RegionDaoImpl;
import com.wl.web.blog.dao.Impl.TopicDaoImpl;
import com.wl.web.blog.dao.Impl.articleDaoImpl;
import com.wl.web.blog.dao.RegionDao;
import com.wl.web.blog.dao.TopicDao;
import com.wl.web.blog.dao.UserDao;
import com.wl.web.blog.dao.Impl.UserDaoImpl;
import com.wl.web.blog.dao.articleDao;


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
        public static articleDao getArticalInstance(){return  new articleDaoImpl();
        }

        public static TopicDao getTopicDaoInstance() {
                return new TopicDaoImpl();
        }

        public static RegionDao getRegionDaoInstance() {
                return new RegionDaoImpl();
        }
        }
