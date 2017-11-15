package com.java8.genericity;

/**
 * Created by cyh on 2016-05-01.
 * 带两个类型的泛型类
 * 在泛型中可以声明多个类型参数，为了指定多个类型参数，只需要逗号分割参数列表
 */

class TwoGen<T,V>{
    T ob1;
    V ob2;

    //构造方法
    TwoGen(T o1,V o2){
        ob1=o1;
        ob2=o2;
    }
    //显示T和V的类型
    void showTypes(){
        System.out.println("Type of T is: "+ob1.getClass().getName());
        System.out.println("Type of V is: "+ob2.getClass().getName());
    }

    T getOb1(){
        return ob1;
    }
    V getOb2(){
        return ob2;
    }
}

public class SimpGen {

    public static void main(String[] args) {
        TwoGen<String,Integer> SIObj=new TwoGen<>("hello Gen",88);
        SIObj.showTypes();

        //Obtain and show values
        String str=SIObj.getOb1();
        System.out.println("values: "+str);
        //自动拆箱
        int v=SIObj.getOb2();
        System.out.println("values: "+v);
       /*
        Type of T is: java.lang.String
        Type of V is:java.lang.Integer
        values: hello Gen
        values: 88*/

    }
}
