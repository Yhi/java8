package com.java8.stream;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by cyh on 2016-05-01.
 */
class NamePhoneEmail{
    String name;
    String phoneNum;
    String email;

    public NamePhoneEmail(String name, String phoneNum, String email) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public NamePhoneEmail() {
    }

    public NamePhoneEmail(String name, String phoneNum) {
        this.name = name;
        this.phoneNum = phoneNum;
    }
}

public class StreamMapDemo {
    public static void main(String[] args) {
        ArrayList<NamePhoneEmail> myList=new ArrayList<>();
        myList.add(new NamePhoneEmail("李华","555-5555","lihua@qq.com"));
        myList.add(new NamePhoneEmail("张三","111-5555","zhangsan@qq.com"));
        myList.add(new NamePhoneEmail("二麻子","222-5555","ermazi@qq.com"));

        myList.stream().forEach((a)-> {
            System.out.println(a.name + " " + a.phoneNum + " " + a.email);
        });

        Stream<NamePhoneEmail> nameAndPhone=myList.stream().map(
                a->(new NamePhoneEmail(a.name,a.phoneNum))
        );

        nameAndPhone.forEach(i-> System.out.println(i.name+" "+i.phoneNum));

        //查找张三的电话号码
        Stream<NamePhoneEmail> nameAndPhoneNumber=myList.stream().filter(a->a.name.equals("张三")).map((a)->new NamePhoneEmail(a.name,a.phoneNum));
        nameAndPhoneNumber.forEach(i-> System.out.println(i.name+" "+i.phoneNum));

    }

}
