package com.wl.web.blog.dao.Impl;


import com.wl.web.blog.dao.articleDao;
import com.wl.web.blog.domain.Vo.ArticleVo;
import com.wl.web.blog.entity.Article;
import com.wl.web.blog.util.BeanHandler;
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
    public int[] batchInsert(List<Article> articalList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        connection.setAutoCommit(false);
        String sql="INSERT INTO t_article(title,cover,content,comment,person_like,create_Time,user_id,topic_id,summary) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt =connection.prepareStatement(sql);
        articalList.forEach(Article -> {
            try {

                pstmt.setString(1,Article.getTitle());
                pstmt.setString(2,Article.getCover());
                pstmt.setString(3,Article.getContent());
                pstmt.setInt(4,Article.getComment());
                pstmt.setInt(5,Article.getPerson_like());
                pstmt.setObject(6,Article.getCreateTime());
                pstmt.setInt(7,Article.getUser_id());
                pstmt.setLong(8,Article.getTopicId());
                pstmt.setString(9,Article.getSummary());


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
    public List<Article> findArticalAll() throws SQLException {
        List<Article> articalList = new ArrayList<>();
        Connection connection =DbUtil.getConnection();
        String sql ="SELECT * FROM t_article ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
      ResultSet rs= pstmt.executeQuery();
        while (rs.next()){
            Article article = new Article();
            article.setId(rs.getInt("id"));
            article.setTitle(rs.getString("title"));
            article.setCover(rs.getString("cover"));
            article.setContent(rs.getString("content"));
            article.setComment(rs.getInt("comment"));
            article.setPerson_like(rs.getInt("person_like"));
            article.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            article.setUser_id(rs.getInt("user_id"));
            article.setTopicId(rs.getLong("topic_id"));
            article.setSummary(rs.getString("summary"));
//            Timestamp.valueOf();

            articalList.add(article);
        }
        return articalList;
    }


    @Override
    public List<ArticleVo> selectHotArticles() throws SQLException {
        Connection connection = DbUtil.getConnection();

                //从文章、专题、用户表联查出前端需要展示的数据
        String sql = "SELECT a.id,a.user_id,a.topic_id,a.title,a.person_like,a.comment,a.create_time," +
                "b.topic_name,b.logo,c.nickname,c.avatar " +
                "FROM t_article a " +
                "LEFT JOIN t_topic b " +
                "ON a.topic_id = b.id " +
                "LEFT JOIN t_user c " +
                "ON a.user_id = c.id " +
                "ORDER BY a.comment DESC " +
                "LIMIT 10 ";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        //调用封装的方法，将结果集解析成List
        List<ArticleVo> articleVoList = BeanHandler.convertArticle(rs);
        DbUtil.close(connection, pst, rs);
        return articleVoList;
    }

    @Override
    public List<ArticleVo> selectByPage(int currentPage, int count) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT a.*,b.topic_name,b.logo,c.nickname,c.avatar " +
                "FROM t_article a " +
                "LEFT JOIN t_topic b " +
                "ON a.topic_id = b.id " +
                "LEFT JOIN t_user c " +
                "ON a.user_id = c.id  LIMIT ?,? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, (currentPage - 1) * count);
        pst.setInt(2, count);
        ResultSet rs = pst.executeQuery();
        List<ArticleVo> articleVos = BeanHandler.convertArticle(rs);
//        DbUtil.close(connection, pst, rs);
        return articleVos;
    }


    @Override
    public List<ArticleVo> selectByKeywords(String keywords) throws SQLException {
        Connection connection = DbUtil.getConnection();
        //从文章、专题、用户表联查出前端需要展示的数据
        String sql = "SELECT a.*,b.topic_name,b.logo,c.nickname,c.avatar " +
                "FROM t_article a " +
                "LEFT JOIN t_topic b " +
                "ON a.topic_id = b.id " +
                "LEFT JOIN t_user c " +
                "ON a.user_id = c.id " +
                "WHERE a.title LIKE ?  OR a.summary LIKE ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, "%" + keywords + "%");
        pst.setString(2, "%" + keywords + "%");
        ResultSet rs = pst.executeQuery();
        List<ArticleVo> articleVos = BeanHandler.convertArticle(rs);
//        DbUtil.close(connection, pst, rs);
        return articleVos;
    }

    @Override
    public List<ArticleVo> selectByTopicId(long topicId) throws SQLException {
        Connection connection = DbUtil.getConnection();
        //从文章、专题、用户表联查出前端需要展示的数据
        String sql = "SELECT a.*,b.topic_name,b.logo,c.nickname,c.avatar " +
                "FROM t_article a " +
                "LEFT JOIN t_topic b " +
                "ON a.topic_id = b.id " +
                "LEFT JOIN t_user c " +
                "ON a.user_id = c.id " +
                "WHERE a.topic_id = ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, topicId);
        ResultSet rs = pst.executeQuery();
        List<ArticleVo> articleVos = BeanHandler.convertArticle(rs);
//        DbUtil.close(connection, pst, rs);
        return articleVos;
    }

    @Override
    public List<ArticleVo> selectByUserId(long userId) throws SQLException {
        Connection connection = DbUtil.getConnection();
        //从文章、专题、用户表联查出前端需要展示的数据
        String sql = "SELECT a.*,b.topic_name,b.logo,c.nickname,c.avatar " +
                "FROM t_article a " +
                "LEFT JOIN t_topic b " +
                "ON a.topic_id = b.id " +
                "LEFT JOIN t_user c " +
                "ON a.user_id = c.id " +
                "WHERE a.topic_id = ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, userId);
        ResultSet rs = pst.executeQuery();
        List<ArticleVo> articleVos = BeanHandler.convertArticle(rs);
//        DbUtil.close(connection, pst, rs);
        return articleVos;
    }

    @Override
    public ArticleVo getArticle(long id) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT a.*,b.topic_name,b.logo,c.nickname,c.avatar " +
                "FROM t_article a " +
                "LEFT JOIN t_topic b " +
                "ON a.topic_id = b.id " +
                "LEFT JOIN t_user c " +
                "ON a.user_id = c.id " +
                "WHERE a.id = ?  ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
        ArticleVo articleVo = BeanHandler.convertArticle(rs).get(0);
        //注意这里，上一步执行完毕后，结果集的指针已经在当前这行记录的下方，所以回退一下
        rs.previous();
        //列表页的文章数据一般不需要详细内容，但是文章详情页需要，所以补上content属性
        articleVo.getArticle().setContent(rs.getString("content"));
        DbUtil.close(connection, pst, rs);
        return articleVo;
    }
}


