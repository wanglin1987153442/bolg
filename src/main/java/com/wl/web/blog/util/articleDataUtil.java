package com.wl.web.blog.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * @author 小黑
 * @ClassNamearticleDataUtil
 * @Description TODO
 * @Date 2019/11/10
 * @Version 1.0
 */
public class articleDataUtil {
    /**
     * 自己造 点赞 评论 日期数据
     *
     */
    /**
     * 创造点赞数
     *
     * @return
     */
    public static int getComment() {
        Integer result = 0;
        Random random = new Random();
        for (int i = 0; i <= 50; i++) {
            int Comment = random.nextInt(100000);
            result += Comment;
        }

        return result;
    }

    /**
     * 、获得点赞数
     *
     * @return
     */
    public static int getLike() {
        Integer result = 0;
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            int Like = random.nextInt(100);
            result += Like;
        }
        return result;
    }

    /**
     * 获得 日期
     * @return
     */
    public static LocalDateTime getTime() {
        LocalDate now = LocalDate.now();
        Random random = new Random();
        int bound = random.nextInt(8888);
        int hour = random.nextInt(25);
        int min = random.nextInt(61);
        int sec =random.nextInt(61);

       LocalDateTime localDateTime= now.minusDays(bound).atTime(hour,min,sec);
        return  localDateTime;

    }



}
