package com.wl.web.blog.util;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author 小黑
 * @ClassNameFileUtil
 * @Description TODO
 * @Date 2019/11/21
 * @Version 1.0
 */
public class FileUtil {

    public  static boolean createFile(String newFileName){

        File file =new File(newFileName);
        System.out.println(file.getAbsolutePath());
if (!file.exists()){
    System.out.println("成功");
    return  file.mkdirs();

}else {
    return false;
}
    }
}
