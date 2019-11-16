package com.wl.web.blog.dao.articalDaoImpl;

import com.wl.web.blog.dao.ArticalDao;
import com.wl.web.blog.entity.artical;
import com.wl.web.blog.util.DbUtil;
import com.wl.web.blog.util.JSoupSpider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 小黑
 * @ClassNamearticalDaoImpl
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 */
public class articalDaoImpl implements ArticalDao {
    private static Logger logger = LoggerFactory.getLogger(articalDaoImpl.class);
    @Override
    public int[] batchInsert(List<artical> articalList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        connection.setAutoCommit(false);
        String sql="INSERT INTO article(title,avatar,text,comment,person_like,time,user_id) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pstmt =connection.prepareStatement(sql);
        articalList.forEach(artical -> {
            try {

                pstmt.setString(1,artical.getTitle());
                pstmt.setString(2,artical.getAvatar());
                pstmt.setString(3,artical.getText());
                pstmt.setInt(4,artical.getComment());
                pstmt.setInt(5,artical.getPerson_like());
                pstmt.setObject(6,artical.getTime());
                pstmt.setInt(7,artical.getUser_id());
//                pstmt.setInt(6,artical.getUser_id());
                pstmt.addBatch();
            } catch (SQLException e) {
               e.printStackTrace();
            }

        });
        int [] result = pstmt.executeBatch();
        connection.commit();
        DbUtil.close(null ,pstmt,connection);
        return result;
    }

    @Override
    public List<artical> findArticalAll() throws SQLException {
        List<artical> articalList = new ArrayList<>();
        Connection connection =DbUtil.getConnection();
        String sql ="SELECT * FROM article ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
      ResultSet rs= pstmt.executeQuery();
        while (rs.next()){
            artical artical = new artical();
            artical.setId(rs.getInt("id"));
            artical.setTitle(rs.getString("title"));
            artical.setAvatar(rs.getString("avatar"));
            artical.setText(rs.getString("text"));
            artical.setComment(rs.getInt("comment"));
            artical.setPerson_like(rs.getInt("person_like"));
           artical.setTime(rs.getTimestamp("time").toLocalDateTime());
//            Timestamp.valueOf();

            articalList.add(artical);
        }
        return articalList;
    }
}
