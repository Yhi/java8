package com.java8.stream;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by cyh on 2016-05-01.
 * map()方法
 */
public class StreamMap {
    public static void main(String[] args) {
        //此版本与StreamCombiner类区别在于：
        //本类计算平方根发生在映射过程而不是此前的缩减过程中。
        ArrayList<Double> myList=new ArrayList<>();
        myList.add(4.0);
        myList.add(9.0);
        myList.add(1.0);
        //map the square root of the elements in myList to a new stream.
        Stream<Double> sqrRootStream=myList.stream().map(a->Math.sqrt(a));
        //Find the product of the square roots.
        double productOfSqrRoots=sqrRootStream.reduce(1.0,(a,b)->a*b);
        System.out.println("Product of square roots is "+productOfSqrRoots);

    }
}
