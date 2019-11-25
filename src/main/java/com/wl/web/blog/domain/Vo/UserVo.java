package com.wl.web.blog.domain.Vo;

import com.wl.web.blog.entity.Topic;
import com.wl.web.blog.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @author 小黑
 * @ClassNameasddas
 * @Description TODO
 * @Date 2019/11/25
 * @Version 1.0
 */
@Data
public class UserVo {
    private User user;
    private List<ArticleVo> articleList;
    private List<Topic> topicList;
    private List<User> fansList;
}