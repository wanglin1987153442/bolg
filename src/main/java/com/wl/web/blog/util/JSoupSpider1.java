package com.wl.web.blog.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 小黑
 * @ClassNameSpider
 * @Description TODO
 * @Date 2019/11/7
 * @Version 1.0
 */
//public class JSoupSpider1 {
//
//   static JSoupSpider1 jSoupSpider =new JSoupSpider1();
//
//
//    //静态公有无参方法 方法名自定 返回List<student>
//    public static List<Student> getStudents(){
//        Document document= null;
//        //通过JSoup连接目标页面
//        try {
//            document= Jsoup.connect("https://www.jianshu.com/recommendations/users?utm_source=desktop&utm_medium=index-users").get();
//        } catch (IOException e) {
//            System.err.println("连接失败");
//        }
//        //选取class为col-xs-8的元素集合
//        Elements divs = document.getElementsByClass("col-xs-8");
//        //对div集合进行遍历
//        List<Student> studentList = new ArrayList<>(divs.size());
//        divs.forEach(div->{
//
//            //取div孩子节点集合
//            Element wrapDiv=div.child(0);
//            Element link =wrapDiv.child(0);
//            Elements linkChildern = link.children();
//       Student student =new Student();
//            student.setAccount(jSoupSpider.addacount());
//       student.setUsername(linkChildern.get(1).text());
//       student.setAvatar("https:"+linkChildern.get(0).attr("src"));
//       student.setCreateTime(LocalDateTime.now());
//       student.setDescription(linkChildern.get(2).text());
//       student.setPassword(student.getPassword());
//
//
//
//       studentList.add(student);
//
//        });
//
//        return studentList;
//    }
//
//    private String addacount()  {
//        Random random = new Random();
//        String result="1802343";
//
//        for (int i=0;i<3;i++)
//        {
//            result+=random.nextInt(10);
//        }
//
//return result;
//
//    }
//
//
//
//
//}
