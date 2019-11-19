package com.wl.web.blog.dao.Impl;


import com.wl.web.blog.dao.articleDao;
import com.wl.web.blog.entity.article;
import com.wl.web.blog.util.DbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 小黑
 * @ClassNamearticalDaoImpl
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 */
public class articleDaoImpl implements articleDao {
    private static Logger logger = LoggerFactory.getLogger(articleDaoImpl.class);
    @Override
    public int[] batchInsert(List<article> articalList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        connection.setAutoCommit(false);
        String sql="INSERT INTO article(title,cover,content,comment,person_like,create_Time,user_id，topicId,summary) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt =connection.prepareStatement(sql);
        articalList.forEach(article -> {
            try {

                pstmt.setString(1,article.getTitle());
                pstmt.setString(2,article.getCover());
                pstmt.setString(3,article.getContent());
                pstmt.setInt(4,article.getComment());
                pstmt.setInt(5,article.getPerson_like());
                pstmt.setObject(6,article.getCreateTime());
                pstmt.setInt(7,article.getUser_id());
                pstmt.setLong(8,article.getTopicId());
                pstmt.setString(9,article.getSummary());


                pstmt.addBatch();
            } catch (SQLException e) {
               e.printStackTrace();
            }

        });
        int [] result = pstmt.executeBatch();
        connection.commit();
//        DbUtil.close(null ,pstmt,connection);
        return result;
    }

    @Override
    public List<article> findArticalAll() throws SQLException {
        List<article> articalList = new ArrayList<>();
        Connection connection =DbUtil.getConnection();
        String sql ="SELECT * FROM article ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
      ResultSet rs= pstmt.executeQuery();
        while (rs.next()){
            article article = new article();
            article.setId(rs.getInt("id"));
            article.setTitle(rs.getString("title"));
            article.setCover(rs.getString("cover"));
            article.setContent(rs.getString("content"));
            article.setComment(rs.getInt("comment"));
            article.setPerson_like(rs.getInt("person_like"));
            article.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            article.setUser_id(rs.getInt("user_id"));
            article.setTopicId(rs.getLong("topId"));
            article.setSummary(rs.getString("summary"));
//            Timestamp.valueOf();

            articalList.add(article);
        }
        return articalList;
    }
}
