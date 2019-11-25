package com.wl.web.blog.service.UserServiceImpl;

import com.wl.web.blog.dao.TopicDao;
import com.wl.web.blog.dao.articleDao;
import com.wl.web.blog.domain.Vo.ArticleVo;
import com.wl.web.blog.domain.Vo.TopicVo;
import com.wl.web.blog.entity.Topic;
import com.wl.web.blog.factory.DaoFactory;
import com.wl.web.blog.service.TopicService;
import com.wl.web.blog.util.Result;
import com.wl.web.blog.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 小黑
 * @ClassNamewqdd
 * @Description TODO
 * @Date 2019/11/25
 * @Version 1.0
 */
public class TopicServiceImpl implements TopicService {

    private TopicDao topicDao = DaoFactory.getTopicDaoInstance();
    private com.wl.web.blog.dao.articleDao articleDao = DaoFactory.getArticalInstance();
    private static Logger logger = LoggerFactory.getLogger(TopicServiceImpl.class);

    @Override
    public Result getHotTopics() {
        List<Topic> topicList = null;
        try {
            topicList = topicDao.selectHotTopics();
        } catch (SQLException e) {
            logger.error("获取热门专题出现异常");
        }
        if (topicList != null) {
            return Result.success(topicList);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }


    @Override
    public Result getTopic(long id) {
        TopicVo topicVo = null;
        try {
            topicVo = topicDao.getTopic(id);
        } catch (SQLException e) {
            logger.error("根据id获取专题详情出现异常");
        }
        if (topicVo != null) {
            try {
                List<ArticleVo> articleVoList = articleDao.selectByTopicId(topicVo.getTopic().getId());
                topicVo.setArticleList(articleVoList);

            } catch (SQLException e) {
                logger.error("根据专题id获取所有文章出现异常");
            }
            return Result.success(topicVo);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    @Override
    public Result selectByKeywords(String keywords) {
        List<Topic> topicList = null;
        try {
            topicList = topicDao.selectByKeywords(keywords);
        } catch (SQLException e) {
            logger.error("根据关键字查询专题出现异常");
        }
        if (topicList != null) {
            return Result.success(topicList);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    @Override
    public Result selectByPage(int currentPage, int count) {
        List<Topic> topicList = null;
        try {
            topicList = topicDao.selectByPage(currentPage, count);
        } catch (SQLException e) {
            logger.error("分页查询专题出现异常");
        }
        if (topicList != null) {
            return Result.success(topicList);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }
}