package com.wl.web.blog.util;

import com.wl.web.blog.entity.Topic;
import com.wl.web.blog.entity.User;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mq_xu
 * @ClassName JSoupSpider
 * @Description JSoup实现的一个爬虫工具
 * @Date 9:13 2019/11/7
 * @Version 1.0
 **/
public class JSoupSpider {
    private static Logger logger = LoggerFactory.getLogger(JSoupSpider.class);
    private static final int PAGE_COUNT = 1;
    public static List<User> getUsers() {
        Document document = null;
        List<User> userList = new ArrayList<>(100);
        for (int i = 2; i <= 5; i++) {
            try {
                document = Jsoup.connect("https://www.jianshu.com/recommendations/users?utm_source=desktop&utm_medium=index-users&page=" + i).get();
            } catch (IOException e) {
                logger.error("连接失败");
            }
            Elements divs = document.getElementsByClass("col-xs-8");
            divs.forEach(div -> {
                Element wrapDiv = div.child(0);
                Element link = wrapDiv.child(0);
                Elements linkChildren = link.children();
                User user = new User();
                user.setMobile(UserDataUtil.getMobile());
                user.setPassword(UserDataUtil.getPassword());
                user.setGender(UserDataUtil.getGender());
                user.setAvatar("https:" + linkChildren.get(0).attr("src"));
                user.setNickname(linkChildren.get(1).text());
                user.setIntroduction(linkChildren.get(2).text());
                user.setBirthday(UserDataUtil.getBirthday());
                user.setCreateTime(LocalDateTime.now());
                user.setAddress(UserDataUtil.getAddress());
                userList.add(user);
            });
        }
        return userList;
    }




    /**
     * 爬取简书网专题
     *
     * @return
     */
    public static List<Topic> getTopics() {
        List<Topic> topicList = new ArrayList<>(100);
        Connection connection;
        Document document = null;
        for (int i = 1; i <= PAGE_COUNT; i++) {
            try {
                //分析页面得到url和惨
                connection = Jsoup.connect("https://www.jianshu.com/recommendations/collections?order_by=hot&page=" + i);
                //通过chrome开发者工具查看该请求必须添加请求头
                connection.header("X-PJAX", "true");
                document = connection.get();
            } catch (IOException e) {
                logger.error("连接失败");
            }
            assert document != null;
            Elements list = document.select(".collection-wrap");
            list.forEach(item -> {
                Elements elements = item.children();
                Topic topic = new Topic();
                Element link = elements.select("a").get(0);
                Element logo = link.child(0);
                Element name = link.child(1);
                Element description = link.child(2);
                Element articles = elements.select(".count > a").get(0);
                Element follows = elements.select(".count > a").get(0);
                topic.setAdminId(1L);
                topic.setTopicName(name.text());
                topic.setLogo(logo.attr("src"));
                topic.setDescription(description.text());
                topic.setHomepage("https://www.jianshu.com" + link.attr("href"));
                String[] str = StringUtil.getDigital(articles.text());
                topic.setArticles(Integer.parseInt(str[0]));
                str = StringUtil.getDigital(follows.text());
                topic.setFollows(Integer.parseInt(str[0]));
                topic.setCreateTime(articleDataUtil.getTime());
                topicList.add(topic);
            });
        }
        return topicList;
    }
}
