package com.wl.web.blog.service.UserServiceImpl;

import com.wl.web.blog.dao.UserDao;
import com.wl.web.blog.dao.articleDao;
import com.wl.web.blog.domain.Dto.UserDto;
import com.wl.web.blog.domain.Vo.ArticleVo;
import com.wl.web.blog.domain.Vo.UserVo;
import com.wl.web.blog.entity.User;
import com.wl.web.blog.factory.DaoFactory;
import com.wl.web.blog.service.UserService;
import com.wl.web.blog.util.Message;
import com.wl.web.blog.util.Result;
import com.wl.web.blog.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 小黑
 * @ClassNameUserServiceImpl
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = DaoFactory.getUserDaoInstance();
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private articleDao articleDao = DaoFactory.getArticalInstance();

    @Override
    public Result signIn(UserDto userDto) {
        User user = null;
        Map<String, Object> map = new HashMap<>();
        try {
            user = userDao.findUserByMobile(userDto.getMobile());
        } catch (SQLException e) {
            logger.error("手机号查询用户异常");
        }
        if (user != null) {
            //数据库查到的用户密码和前端传递的用户密码（经过加密）相等
            if (user.getPassword().equals(userDto.getPassword())) {
                //登录成功
                return Result.success(user);
            } else {
                //密码错误
                return Result.failure(ResultCode.USER_PASSWORD_ERROR);
            }
        } else {
            //账号错误
            return Result.failure(ResultCode.USER_ACCOUNT_ERROR);
        }
    }

    @Override
    public Map<String, Object> zhuce(User user) {
        Map<String, Object> map = new HashMap<>();
        try {
            int  result = userDao.addUser(user);
        } catch (SQLException e) {
            logger.info("注册失败");
        }
        if (!user.getMobile().equals("")&&!user.getPassword().equals("")){
               map.put("msg", Message.ZHUCE_SUCCESS);
       }else {
           map.put("msg", Message.ZHUCE_ERROP);
       }

        return map;

    }



    @Override
    public Result getHotUsers() {
        List<User> userList = null;
        try {
            userList = userDao.selectHotUsers();
        } catch (SQLException e) {
            logger.error("获取热门用户出现异常");
        }
        if (userList != null) {
            //成功并返回数据
            return Result.success(userList);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    @Override
    public Result selectByPage(int currentPage, int count) {
        List<User> userList = null;
        try {
            userList = userDao.selectByPage(currentPage, count);
        } catch (
                SQLException e) {
            logger.error("分页查询用户出现异常");
        }
        if (userList != null) {
            return Result.success(userList);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }


    @Override
    public Result getUser(long id) {
        UserVo userVo = null;
        try {
            userVo = userDao.getUser(id);
        } catch (SQLException e) {
            logger.error("根据id获取用户详情出现异常");
        }
        if (userVo != null) {
            try {
                List<ArticleVo> articleVoList = articleDao.selectByUserId(id);
                userVo.setArticleList(articleVoList);
                return Result.success(userVo);
            } catch (SQLException e) {
                logger.error("根据用户id获取文章列表数据出现异常");
            }
        }

        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);

    }

    @Override
    public Result selectByKeywords(String keywords) {
        List<User> userList = null;
        try {
            userList = userDao.selectByKeywords(keywords);
        } catch (SQLException e) {
            logger.error("根据关键字查询用户出现异常");
        }
        if (userList != null) {
            return Result.success(userList);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }
}

