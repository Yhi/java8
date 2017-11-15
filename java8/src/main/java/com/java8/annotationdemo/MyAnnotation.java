package com.java8.annotationdemo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

/**
 * Created by cyh on 2016-05-15.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String str();
    int val();
}


class TestMyAnnotation{
    public static void main(String[] args) {
        myMeth();
    }

    @MyAnnotation(str="Annotation Example",val=100)
    public static  void myMeth(){
        TestMyAnnotation t=new TestMyAnnotation();
        try {
            Class<?> c=t.getClass();
            Method m=c.getMethod("myMeth");
            MyAnnotation anno=m.getDeclaredAnnotation(MyAnnotation.class);
            System.out.println(anno.str()+" "+anno.val());

        }catch (NoSuchMethodException e) {
            System.out.println("Method Not Found");
        }
    }
}

