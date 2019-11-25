package com.wl.web.blog.dao.Impl;

import com.wl.web.blog.dao.RegionDao;
import com.wl.web.blog.entity.Region;
import com.wl.web.blog.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 小黑
 * @ClassNameadsdasda
 * @Description TODO
 * @Date 2019/11/25
 * @Version 1.0
 */
public class RegionDaoImpl implements RegionDao {

    @Override
    public List<Region> selectAll() throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_region ";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        List<Region> regionList = new ArrayList<>();
        while (rs.next()){
            Region region = new Region();
            region.setId(rs.getInt("id"));
            region.setName(rs.getString("name"));
            region.setParentId(rs.getInt("parent_id"));
            region.setLevel(rs.getInt("level"));
            region.setCityCode(rs.getString("city_code"));
            region.setPostCode(rs.getString("post_code"));
            region.setMergeName(rs.getString("merge_name"));
            regionList.add(region);
        }
        DbUtil.close(connection, pst, rs);
        return regionList;
    }
}