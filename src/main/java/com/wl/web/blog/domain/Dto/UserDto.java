package com.wl.web.blog.domain.Dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 小黑
 * @ClassNameUserDto
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 */
@Data
public class UserDto implements Serializable {
    private String mobile;
    private String password;
    private String code;
}
