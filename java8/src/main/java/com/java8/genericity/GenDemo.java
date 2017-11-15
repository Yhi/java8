package com.java8.genericity;

/**
 * Created by cyh on 2016-05-01.
 * 泛型：参数化类型。
 * 使用泛型，所有类类型转换都是自动和隐式进行的。
 * 泛型扩展了重用代码多多能力，并且可以安全、容易地重用代码
 */
class  Gen<T>{
    T ob;
    Gen(T o){
        ob=o;
    }
    T getOb(){
        return ob;
    }
    //show type of T
    void showType(){
        System.out.println("Type of T is： "+ ob.getClass().getName());
    }
        }
 class GenDemo {

     public static void main(String[] args) {
         Gen<Integer> intOb;
         intOb=new Gen<Integer>(88);
         intOb.showType();
         int v=intOb.getOb();
         System.out.println("value: "+v);

         Gen<String> strOb=new Gen<String>("hell Gen");
         strOb.showType();
         String str=strOb.getOb();
         System.out.println("value: "+str);
         /*
           Type of T is： java.lang.Integer
           value: 88
           Type of T is： java.lang.String
           value: hell Gen
          */

     }
}
