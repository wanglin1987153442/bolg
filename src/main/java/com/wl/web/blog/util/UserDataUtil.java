package com.wl.web.blog.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.wl.web.blog.domain.Dto.City;
import com.wl.web.blog.domain.Dto.Province;
import com.wl.web.blog.domain.Dto.ProvinceList;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

/**
 * @author 小黑
 * @ClassName DataUtil
 * @Description 数据生成工具
 * @Date 2019/11/9
 * @Version 1.0
 */
public class UserDataUtil {
    private static Logger logger = LoggerFactory.getLogger(UserDataUtil.class);
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



    public static String getAddress() {
        StringBuilder address = new StringBuilder();
        ClassLoader classLoader = UserDataUtil.class.getClassLoader();
        URL resource = classLoader.getResource("address.json");
        assert resource != null;
        String path = resource.getPath();
        File file = new File(path);
        Reader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            logger.error("文件找不到");
        }
        assert reader != null;
        BufferedReader br = new BufferedReader(reader);
        String line;
        try {
            while ((line = br.readLine()) != null) {
                address.append(line);
            }
        } catch (IOException e) {
            logger.error("文件io异常");
        }
        Gson gson = new GsonBuilder().create();

        ProvinceList provinces =  gson.fromJson(address.toString(), ProvinceList.class);

        List<Province> provinceList = provinces.getProvinceList();
        int size = provinceList.size();
        Random random = new Random();
        int index = random.nextInt(size);
        Province province = provinceList.get(index);
        List<City> cityList = province.getCities();
        size = cityList.size();
        index = random.nextInt(size);
        City city = cityList.get(index);
        return province.getName() + city.getName();
    }


    public static void main(String[] args) {

        System.out.println(UserDataUtil.getyzm(4));



    }

    public  static StringBuilder getyzm(int length){
        StringBuilder stringBuilder=new StringBuilder();
        String val ="";
        Random random =new Random();
        int sz =10;
        int zm=26;
        char a , b,c;
        int index ;


        for (int i=0;i<length;i++){



int str =random.nextInt(3);

if (str==0){
    index = random.nextInt(sz);
    a=(char)('0'+index ) ;
    System.out.println(a);
    stringBuilder.append(a);
}else if (str==1){
    index= random.nextInt(zm);
    b=(char)('a'+index ) ;
    System.out.println(b);
    stringBuilder.append(b);
}else if (str==2){
    index= random.nextInt(zm);
    c=(char)('A'+index);
    System.out.println(c);
    stringBuilder.append(c);
}




        }
        return  stringBuilder;
    }

    public static String getCharAndNumr(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 取得大写字母还是小写字母
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }



}

