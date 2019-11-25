package com.wl.web.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author mq_xu
 * @ClassName User
 * @Description 用户实体类
 * @Date 9:47 2019/11/9
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String mobile;
    private String password;
    private String nickname;
    private String gender;
    private LocalDate birthday;
    //地址
    private String address;

    private String avatar;


    //个人中心背景图
    private String banner;
    private String introduction;
    private String homepage;
    private String  email;
    private Short follows;
    private Short fans;
    private Short articles;
    private LocalDateTime createTime;
    private Short status;
}
