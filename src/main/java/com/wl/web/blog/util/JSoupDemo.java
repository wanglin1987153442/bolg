package com.wl.web.blog.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author 小黑
 * @ClassNameJSoupDemo
 * @Description JSoup解析器
 * @Date 2019/11/7
 * @Version 1.0
 */
public class JSoupDemo {
    public static void main(String[] args)throws Exception {
//        声明文档变量

        Document document;
        //通过JSoup连接目标页面
          document=Jsoup.connect("https://www.jianshu.com/recommendations/users?utm_source=desktop&utm_medium=index-users").get();
          //选取class为col-xs-8的元素集合
        Elements divs = document.getElementsByClass("col-xs-8");
        //对div集合进行遍历
        divs.forEach(div->{
            //取div孩子节点集合
           Element wrapDiv=div.child(0);

            Element sDiv =wrapDiv.child(0);
            Element avatar = sDiv.child(0);
            Element name= sDiv.child(1);

            System.out.println( "https:"+avatar.attr("src"));
            System.out.println(name.text());

        });



    }
}
