package com.wl.web.blog.service.UserServiceImpl;

import com.wl.web.blog.dao.UserDao;
import com.wl.web.blog.domain.UserDto;
import com.wl.web.blog.entity.User;
import com.wl.web.blog.factory.DaoFactory;
import com.wl.web.blog.service.UserService;
import com.wl.web.blog.util.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.Result;
import java.sql.SQLException;
import java.util.HashMap;
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

    @Override
    public Map<String, Object> signIn(UserDto userDto) {
        User user = null;
        Map<String, Object> map = new HashMap<>();
        try {
            user = userDao.findUserByMobile(userDto.getMobile());
        } catch (SQLException e) {
            logger.error("手机号查询用户异常");
        }
        if (user != null) {
            if (user.getPassword().equals(userDto.getPassword())) {
                map.put("msg", Message.SIGN_IN_SUCCESS);
                map.put("data", user);
            } else {
                if(!userDto.getPassword().equals("")) {
                    map.put("msg", Message.PASSWORD_ERROR);
                }else{
                    map.put("msg",Message.PASSWORD_null);
                }
            }
        } else {
            map.put("msg", Message.MOBILE_NOT_FOUND);
        }
        return map;
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
}
