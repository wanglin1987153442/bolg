package com.wl.web.blog.service;

import com.wl.web.blog.util.Result;

/**
 * @author 小黑
 * @ClassNamedsada
 * @Description TODO
 * @Date 2019/11/25
 * @Version 1.0
 */
public interface TopicService {
    /**
     * 获取热门专题
     * @return
     */
    Result getHotTopics();


    /**
     * 根据id获取专题详情
     * @param id
     * @return
     */
    Result getTopic(long id);

    /**
     * 根据名称或描述模糊搜索专题
     *
     * @param keywords
     * @return
     */
    Result selectByKeywords(String keywords);


    /**
     * 分页查询专题信息
     * @param currentPage
     * @param count
     * @return
     */
    Result selectByPage(int currentPage,int count);
}