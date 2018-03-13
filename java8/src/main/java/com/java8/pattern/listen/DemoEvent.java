package com.java8.pattern.listen;


import java.util.EventObject;

/**
 * @author chenyihui
 * @version 1.0
 * @Description: 事件对象的定义
 * @date 2018/3/13
 */
public class DemoEvent extends EventObject{


    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public DemoEvent(Object source) {
        super(source);
    }
}
