package com.wl.web.blog.dao;

import com.wl.web.blog.domain.Vo.TopicVo;
import com.wl.web.blog.entity.Topic;
import com.wl.web.blog.factory.DaoFactory;
import com.wl.web.blog.util.JSoupSpider;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 小黑
 * @ClassNamesasa
 * @Description TODO
 * @Date 2019/11/25
 * @Version 1.0
 */
public class TopicDaoTest {
    private TopicDao topicDao = DaoFactory.getTopicDaoInstance();

    @Test
    public void batchInsert() throws SQLException {
        topicDao.batchInsert(JSoupSpider.getTopics());
    }

    @Test
    public void selectHotTopics() throws SQLException {
        List<Topic> topicList = topicDao.selectHotTopics();
        System.out.println(topicList.size());
    }

    @Test
    public void selectByKeywords() throws SQLException {
        List<Topic> topicList = topicDao.selectByKeywords("小");
        System.out.println(topicList.size());
    }

    @Test
    public void getTopic() throws SQLException {
        TopicVo topicVo = topicDao.getTopic(1);
        System.out.println(topicVo);
    }
}