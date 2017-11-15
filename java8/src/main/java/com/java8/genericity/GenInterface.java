package com.java8.genericity;

/**
 * Created by cyh on 2016-05-01.
 * 泛型接口
 */

interface  MinMax<T extends Comparable<T>>{
    T min();
    T max();
}
//实现接口
class MyMinMax<T extends Comparable<T>> implements  MinMax<T>{
   T[] values;
    MyMinMax(T[] o){
        values=o;
    }

    @Override
    public T min() {
        T v=values[0];
        for (int i=1;i<values.length;i++)
            if (values[i].compareTo(v)<0) {
                v = values[i];
            }
        return v;
    }

    @Override
    public T max() {
        T v=values[0];
        for (int i=1;i<values.length;i++)
            if (values[i].compareTo(v)>0){
                v=values[i];
            }

        return v;
    }
}
public class GenInterface {
    public static void main(String[] args) {
        Integer inums[]={3,4,5,7,9};
        Character chs[]={'a','d','b'};
        MyMinMax<Integer> iob=new MyMinMax<Integer>(inums);
        MyMinMax<Character> cob=new MyMinMax<Character>(chs);
        System.out.println("inums max:"+iob.max()+"  "+"min:"+iob.min());
        System.out.println("chs max:"+cob.max()+"  "+"min:"+cob.min());

       /*
        inums max:9  min:3
        chs max:d  min:a*/


    }
}
