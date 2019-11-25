package com.wl.web.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 小黑
 * @ClassNames
 * @Description TODO
 * @Date 2019/11/25
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Region {
    private  Integer id;
    private String name;
    private Integer parentId;
    private Integer level;
    private String cityCode;
    private String postCode;
    private String mergeName;
}
