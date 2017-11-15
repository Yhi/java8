package com.java8.annotationdemo;

import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

/**
 * Created by cyh on 2016-05-15.
 * Jdk8 新增的另一种注解特性：允许在相同元素上重复应用注解。
 */
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(MyAnnotationJava8Two.class)
@interface MyAnnotationJava8 {
    String str() default "Testing";
    int val() default 9000;
}
@Retention(RetentionPolicy.RUNTIME)
@interface  MyAnnotationJava8Two{
    MyAnnotationJava8[] value();
}

class RepeatAnno{
    @MyAnnotationJava8(str="First annotation ",val = -1)
    @MyAnnotationJava8(str="Second annotation ",val = 100)
    public static  void myMeth(String str,int i){
        RepeatAnno ob=new RepeatAnno();
        try{
            Class<?> c=ob.getClass();
            Method m=c.getMethod("myMeth",String.class,int.class);
            Annotation anno=m.getAnnotation(MyAnnotationJava8Two.class);
            System.out.println(anno);
        }catch (NoSuchMethodException exc){
            System.out.println("Method Not Found");
        }
    }

    public static void main(String[] args) {
        myMeth("test",10);
    }
}
