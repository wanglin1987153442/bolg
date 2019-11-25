package com.wl.web.blog.dao;

import com.wl.web.blog.entity.Region;
import com.wl.web.blog.factory.DaoFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 小黑
 * @ClassNamesdas
 * @Description TODO
 * @Date 2019/11/25
 * @Version 1.0
 */
public class RegionDaoTest {
    private RegionDao regionDao = DaoFactory.getRegionDaoInstance();

    @Test
    public void selectAll() throws SQLException {
        List<Region> regionList = regionDao.selectAll();
        System.out.println(regionList.size());
    }
}