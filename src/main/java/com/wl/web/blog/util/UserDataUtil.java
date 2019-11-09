package com.wl.web.blog.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDate;
import java.util.Random;

/**
 * @author 小黑
 * @ClassName DataUtil
 * @Description 数据生成工具
 * @Date 2019/11/9
 * @Version 1.0
 */
public class UserDataUtil {
    public static String getMobile(){

        StringBuilder stringBuilder = new StringBuilder("180");
        Random random =new Random();
        for(int i= 0;i<8;i++){
            int number = random.nextInt(10);
            /**
             * 追加到后边
             */
            stringBuilder.append(number);
        }
        return stringBuilder.toString();
    }
    public static  String getPassword(){
        StringBuilder stringBuilder =new StringBuilder();
        Random random = new Random();
        for (int i= 0;i<6;i++){
            int password = random.nextInt(10);
            stringBuilder.append(password);
        }
     return  DigestUtils.md5Hex(stringBuilder.toString());
    }


    public  static String getGender(){
        String[] genders = new String[]{"男","女"};
        Random random = new Random();
        int index = random.nextInt(2);
        return genders[index];
    }
 public static LocalDate getBirthday(){
        LocalDate now =LocalDate.now();
        Random random =new Random();
        int bound =random.nextInt(8888);
        return now.minusDays(bound);
 }

}
