package com.java8.pattern.listen;


/**
 * @author chenyihui
 * @version 1.0
 * @Description 第一个具体的事件监听器
 * @date 2018/3/13
 */
public class FirstEventListener  implements DemoEventListener{


    @Override
    public void processEvent(DemoEvent demoEvent) {
        System.out.println("Frist event listener process event");
    }
}
