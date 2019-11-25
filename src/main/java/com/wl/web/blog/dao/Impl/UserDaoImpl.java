package com.wl.web.blog.dao.Impl;

import com.wl.web.blog.dao.UserDao;
import com.wl.web.blog.domain.Vo.UserVo;
import com.wl.web.blog.entity.User;
import com.wl.web.blog.util.BeanHandler;
import com.wl.web.blog.util.DbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;


/**
 * @author 小黑
 * @ClassNameUserDaoImpl
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 */
public class UserDaoImpl implements UserDao {
    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    @Override
    public int[] batchInsert(List<User> userList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO t_user (mobile,password,nickname,avatar,gender,birthday,introduction,create_time,address) VALUES (?,?,?,?,?,?,?,?,?) ";
        PreparedStatement pstmt =connection.prepareStatement(sql);
        connection.setAutoCommit(false);//设置自动提交为false
        userList.forEach(user -> {
            try {
                pstmt.setString(1, user.getMobile());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getNickname());
                pstmt.setString(4, user.getAvatar());
                pstmt.setString(5, user.getGender());
                //日期的设置，可以使用setObject
                pstmt.setObject(6, user.getBirthday());
                pstmt.setString(7, user.getIntroduction());

                pstmt.setObject(8, user.getCreateTime());
                pstmt.setString(9,user.getAddress());
                pstmt.addBatch();
            } catch (SQLException e) {
                logger.error("批量加入用户数据产生异常");
            }
        });
 int [] result = pstmt.executeBatch();
 connection.commit();
        DbUtil.close(connection, pstmt);
 return result;
    }

    @Override
    public User findUserByMobile(String mobile) throws SQLException {
        Connection connection =DbUtil.getConnection();
        String sql ="SELECT * FROM t_user WHERE mobile = ? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,mobile);
        ResultSet rs = pstmt.executeQuery();
        User user =null;
        if(rs.next()){
            user =new User();
            user.setId(rs.getLong("id"));
            user.setMobile(rs.getString("mobile"));
            user.setPassword(rs.getString("password"));
            user.setNickname(rs.getString("nickname"));
            user.setAvatar(rs.getString("avatar"));
            user.setGender(rs.getString("gender"));
            if (rs.getDate("birthday")!=null){
                user.setBirthday(rs.getDate("birthday").toLocalDate());
            }else {
                user.setBirthday(null);
            }
            user.setIntroduction(rs.getString("introduction"));
            user.setBanner(rs.getString("banner"));
            user.setEmail(rs.getString("email"));
            user.setAddress(rs.getString("address"));
            user.setFollows(rs.getShort("follows"));
            user.setFans(rs.getShort("fans"));
            user.setArticles(rs.getShort("articles"));
            user.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            user.setStatus(rs.getShort("status"));
              }
                   return  user;
    }

    @Override
    public int addUser(User user) throws SQLException {
        Connection connection =DbUtil.getConnection();
        String sql= "INSERT INTO t_user (mobile,password) VALUES(?,?)";
        PreparedStatement pstmt =connection.prepareStatement(sql);
        pstmt.setString(1,user.getMobile());
        pstmt.setString(2,user.getPassword());



        int  result = pstmt.executeUpdate();
//        DbUtil.close(null ,pstmt,connection);
        DbUtil.close(connection, pstmt);
        return result;
    }






    @Override
    public List<User> selectHotUsers() throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_user ORDER BY fans DESC LIMIT 10 ";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        List<User> userList = BeanHandler.convertUser(rs);
        DbUtil.close(connection, pst, rs);
        return userList;
    }

    @Override
    public List<User> selectByPage(int currentPage, int count) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_user LIMIT ?,? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, (currentPage - 1) * count);
        pst.setInt(2, count);
        ResultSet rs = pst.executeQuery();
        List<User> userList = BeanHandler.convertUser(rs);
        DbUtil.close(connection, pst, rs);
        return userList;
    }

    @Override
    public UserVo getUser(long id) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_user WHERE id = ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
        UserVo userVo = new UserVo();
        User user = BeanHandler.convertUser(rs).get(0);
        userVo.setUser(user);
        DbUtil.close(connection, pst, rs);
        return userVo;
    }

    @Override
    public List<User> selectByKeywords(String keywords) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_user " +
                "WHERE nickname LIKE ?  OR introduction LIKE ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, "%" + keywords + "%");
        pst.setString(2, "%" + keywords + "%");
        ResultSet rs = pst.executeQuery();
        List<User> userList = BeanHandler.convertUser(rs);
        DbUtil.close(connection, pst, rs);
        return userList;
    }
}
