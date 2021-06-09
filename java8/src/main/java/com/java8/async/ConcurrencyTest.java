package com.java8.async;


/**
 * @author chenyihui
 * @Title: ConcurrencyTest
 * @Description:  串行和并发执行并累加操作
 * @date 2020/07/15
 */
public class ConcurrencyTest {
    private static final long count=100001;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    private static void concurrency() throws InterruptedException {
        long start =System.currentTimeMillis();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                int a=0;
                for (long i=0;i<count;i++){
                    a+=5;
                }

            }

        });

        thread.start();

        int b=0;
        for (long i=0;i<count;i++){
            b--;
        }
        int a=1;

        long time=System.currentTimeMillis()-start;
        thread.join();

        System.out.println("concurrency :"+time+"ms，b="+b);
    }

    private static void serial(){
        long start=System.currentTimeMillis();
        int a=0;
        for (long i=0;i<count;i++){
            a+=5;
        }
        int b=0;
        for (long i=0;i<count;i++){

            b--;
        }
        long time=System.currentTimeMillis()-start;
        System.out.println("serial:"+time+"ms,b="+b+",a="+a);
    }
}