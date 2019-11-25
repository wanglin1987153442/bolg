package com.wl.web.blog.domain.Vo;

import com.wl.web.blog.entity.Article;
import com.wl.web.blog.entity.Topic;
import com.wl.web.blog.entity.User;
import lombok.Data;

/**
 * @author 小黑
 * @ClassNamedasds
 * @Description TODO
 * @Date 2019/11/25
 * @Version 1.0
 */
@Data
public class ArticleVo {
    private Article article;
    private User author;
    private Topic topic;
}