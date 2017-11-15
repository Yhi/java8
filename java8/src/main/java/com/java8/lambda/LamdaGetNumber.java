package com.java8.lambda;

/**
 * Created by cyh on 2016-05-01.
 * 获取一个数字
 */
//@FunctionalInterface
interface  MyNumber{
    double getValue();
}
public class LamdaGetNumber {
    public static void main(String[] args) {
        MyNumber myNumber;

        myNumber=()->123.45;
        System.out.println("A fixed value: "+myNumber.getValue());

        myNumber=()->Math.random()*10;
        System.out.println(" A random value: "+myNumber.getValue());
        System.out.println("Another random value: "+myNumber.getValue());

        //Lambda 必须与想要实现的抽象方法兼容。类型必须一直
        // myNumber=()->"123"; String类型与double不一直
    }
}
