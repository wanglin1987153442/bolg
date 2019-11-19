package com.wl.web.blog.entity;

import lombok.Data;

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
public class article {
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
