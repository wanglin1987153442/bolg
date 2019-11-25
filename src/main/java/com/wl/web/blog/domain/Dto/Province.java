package com.wl.web.blog.domain.Dto;

import lombok.Data;

import java.util.List;

/**
 * @author 小黑
 * @ClassNameqq

 *  @Description 省，level为1，
 * @Date 2019/11/19
 * @Version 1.0
 */
@Data
public class Province {
    private String name;
    private String level;
    private String code;
    private List<City> cities;
}