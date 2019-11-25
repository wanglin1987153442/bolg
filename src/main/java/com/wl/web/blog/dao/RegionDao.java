package com.wl.web.blog.dao;

import com.wl.web.blog.entity.Region;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 小黑
 * @ClassNameasasa
 * @Description TODO
 * @Date 2019/11/25
 * @Version 1.0
 */

public interface RegionDao {

    /**
     *  查询所有地址
     *
     * @return
     */
    List<Region> selectAll()throws SQLException;
}