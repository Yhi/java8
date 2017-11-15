package com.java8.stream;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by cyh on 2016-05-01.
 * reduce方法
 */
public class StreamReduce {
    public static void main(String[] args) {
        ArrayList<Integer> myList=new ArrayList();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);

        //因为流中包含Intger值，所以在乘法计算中会自动拆箱Integer对象，然后在返回
        //时会自动重新装箱。两个值分别代表累积结果中的当前值和流中的下一个元素
        //最终在一个Optional类型的对象中并被返回。
        Optional<Integer> productObj=myList.stream().reduce((a,b)->a*b);
        if (productObj.isPresent()){
            System.out.println("Product as Optional:"+productObj.get());

        }

        //显示指定了单位，对鱼乘法而言就是1. 注意：结果作为元素类型的对象返回，在本列中就是一个Integer对象
        int product=myList.stream().reduce(1,(a,b)->a*b);
        System.out.println("Product as int "+product);

        //获取偶数值的乘积
        int evenProduct=myList.stream().reduce(1,(a,b)->{
            if(b%a==0)return  a*b;
            else return a;
        });
        System.out.println("Product as EvenProduct "+evenProduct);

        //使用并行流
        //请求并行流处理十分简单：只需使用一个并行流即可。获取并行流的一种方法是使用Collection
        //定义的parallelStream()方法。另外一种方法是对顺序流调用parallel()方法。

        //结果虽然是一样，但乘法可以发生在不同的线程上
        //一般应用并行流的任何操作得到的结果，与在顺序流上执行是不干预的，并且具有关联性，
        //这确保在并行流上执行得到的结果，在顺序流上执行相同操作得到的结果是相同的。
        Optional<Integer> productObjSync=myList.parallelStream().reduce((a,b)->a*b);
        System.out.println("Product as parallelStream"+productObjSync);

        int parallelProduct=myList.parallelStream().reduce(1,(a,b)->a*b,(a,b)->a*b);
        System.out.println(parallelProduct);

    }

}
