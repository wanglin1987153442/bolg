package com.wl.web.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author 小黑
 * @ClassNameatical
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private Integer id;
    private  Integer user_id;
    private Long topicId;
    private String  title;
    private  String content;
    private  String cover;
    private String summary;
    //评论数
    private Integer comment;
    private  Integer person_like;


    private LocalDateTime createTime;
}
