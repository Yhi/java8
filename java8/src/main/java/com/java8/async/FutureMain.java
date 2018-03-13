package com.java8.async;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FutureMain {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new Callable<Double>() {
            @Override
            public Double call(){
                return doSomeA();
            }});


    }


    public static double doSomeA(){
        return 4.5;
    }
}
