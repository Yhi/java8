package com.java8.pattern.listen;


/**
 * @author chenyihui
 * @version 1.0
 * @Description 测试监听处理过程
 * @date 2018/3/13
 */
public class DemoEventClient {


    public static void main(String[] args) {
        /**
         * 定义时间源
         */
        EventSource eventSource = new EventSource();

        /**
         * 定义 并向事件源注册事件监听器
         */
        SecondEventListener secondEventListener = new SecondEventListener();
        eventSource.addDemoLister(secondEventListener);

        FirstEventListener firstEventListener = new FirstEventListener();
        eventSource.addDemoLister(firstEventListener);


        /**
         * 时间通知
         */
        eventSource.notifyDemoEvent();
    }
}
