package com.wl.web.blog.verify;

import com.wl.web.blog.util.UserDataUtil;
import org.jsoup.internal.StringUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

/**
 * @author 小黑
 * @ClassNameimageUtil
 * @Description 图片类
 * @Date 2019/11/20
 * @Version 1.0
 */
public class imageUtil {
    /**
     * 将字符串绘制成矩形
     * @param content
     * @param width
     * @param height
     * @return
     */
    public static BufferedImage getImages(String content,int width,int height){
        BufferedImage img = new BufferedImage(width,height,1);
        Graphics2D graphics = (Graphics2D) img.getGraphics();
       graphics.setColor(new Color(218, 142, 255));
        graphics.fillRect(0,0,200,100);
       graphics.setPaint(new Color(255, 255, 255));
        graphics.drawString(content,100,50);
        return  img;


    }

    public static void main(String[] args) throws IOException {
        String code = UserDataUtil.getCharAndNumr(4);
        BufferedImage img =imageUtil.getImages(code ,200,100);
        //将img通过字节输出到指定目录
        File file =new File("E:/code.jpg");
        ImageIO.write(img,"jpg",file);

    }
}
