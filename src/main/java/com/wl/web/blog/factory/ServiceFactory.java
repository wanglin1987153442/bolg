package com.wl.web.blog.factory;

import com.wl.web.blog.service.ArticleService;
import com.wl.web.blog.service.TopicService;
import com.wl.web.blog.service.UserService;
import com.wl.web.blog.service.UserServiceImpl.ArticleServiceImpl;
import com.wl.web.blog.service.UserServiceImpl.TopicServiceImpl;
import com.wl.web.blog.service.UserServiceImpl.UserServiceImpl;

/**
 * @author 小黑
 * @ClassNameServiceFactory
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 */
public class ServiceFactory {
    public static UserService getUserServiceInstance(){
        return new UserServiceImpl();
    }
    public static ArticleService getArticleServiceInstance() {
        return new ArticleServiceImpl();
    }

    public static TopicService getTopicServiceInstance() {
        return new TopicServiceImpl();
    }

}
