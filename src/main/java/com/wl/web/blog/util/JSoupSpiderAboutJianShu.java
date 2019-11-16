package com.wl.web.blog.util;


import com.wl.web.blog.entity.artical;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 小黑
 * @ClassNameJSoupSpiderAboutJianShu
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 */
public class JSoupSpiderAboutJianShu {
    private static Logger logger = LoggerFactory.getLogger(JSoupSpider.class);

    public static List<artical> getsomeUser() {
        Document document = null;
        List<artical> articalList = new ArrayList<>(100);
        for (int i = 1; i <= 20; i++) {
            if (i%2==0) {
                try {
                    document = Jsoup.connect("https://book.douban.com/review/best/?start=" + i * 10).get();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("连接失败");
                }

                Elements divs = document.getElementsByClass("main review-item");
                divs.forEach(div -> {
                    Element subject_img = div.child(0);
                    Elements linkChildren = subject_img.children();
                    Element main_bd = div.child(2);
                    Element title = main_bd.child(0);
                    Element description = main_bd.child(1);
                    artical artical = new artical();
                    artical.setAvatar( linkChildren.get(0).attr("src"));
                    artical.setTitle(title.text());
                    artical.setText(description.text());
                    artical.setComment(articleDataUtil.getComment());
                    artical.setPerson_like(articleDataUtil.getLike());
                    artical.setTime(articleDataUtil.getTime());
                    artical.setUser_id(articleDataUtil.getUser_id());
                    articalList.add(artical);

                });
            }
        }
        return articalList;
    }

    public static void main(String[] args) {
        Document document = null;

        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                try {
                    document = Jsoup.connect("https://book.douban.com/review/best/?start=" + i * 10).get();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("连接失败");
                }
                Elements divs = document.getElementsByClass("main review-item");
                divs.forEach(div -> {
                            Element subject_img = div.child(0);
                            Elements linkChildren = subject_img.children();
                    System.out.println(linkChildren.get(0).attr("src"));
            });

            }


        }
    }
    }
