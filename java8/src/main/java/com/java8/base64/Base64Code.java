package com.java8.base64;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

/**
 * Created by cyh on 2016-05-21.
 * base64编码可将一组字节顺序列编码为一个更长可打印的ASCII字符序列
 * java8提供了一个标准的编码器和解码器。
 */
public class Base64Code {
    public static void main(String[] args) {
        try {
            System.out.println(base64Encoder("hello world java8 base64"));
            System.out.println(base64Decoder(base64Encoder("hello world java8 base64")));
            System.out.println();
            System.out.println(base64URLEncoder("www.test.com/oa"));
            System.out.println(base64URLDecoder(base64URLEncoder("www.test.com/oa")));
            System.out.println("--------------");
            StringBuilder stringBuilder=new StringBuilder();
            for (int i = 0;i<5;i++){
                stringBuilder.append(UUID.randomUUID().toString());
            }
            System.out.println(base64MIMEEncoder(stringBuilder.toString()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //编码
    public static String base64Encoder(String str) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(str.getBytes("utf-8"));
    }
    //解码
    public static String base64Decoder(String str) throws UnsupportedEncodingException {
        byte[] asByte=Base64.getDecoder().decode(str);
        return new String(asByte,"utf-8");
    }
    //url编码  使用下划线"_"替换了url的反斜杠"/"
    public static String base64URLEncoder(String str) throws UnsupportedEncodingException {
        return Base64.getUrlEncoder().encodeToString(str.getBytes("utf-8"));
    }
    //url解码
    public static  String base64URLDecoder(String str)throws UnsupportedEncodingException{
        byte[] asByte=Base64.getUrlDecoder().decode(str);
        return  new String(asByte,"utf-8");
    }
    //MIME编码：使用基本的字符数字产生的Base64输出，而且对MIME格式友好：每行输出不超过76个字符，而且每行以"\r\n"符结束
    public static String base64MIMEEncoder(String str) throws UnsupportedEncodingException {
        byte[] toEncode=str.getBytes("utf-8");
        return Base64.getMimeEncoder().encodeToString(toEncode);
    }
}
