package com.wl.web.blog.dao;


import com.wl.web.blog.entity.artical;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 小黑
 * @ClassNamearticalDao
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 */
public interface ArticalDao {
    /**
     * 批量插入文章
     * @param articalList
     * @return
     * @throws SQLException
     */
    int[] batchInsert(List<artical> articalList) throws SQLException;
    List<artical> findArticalAll ()throws SQLException;
}
