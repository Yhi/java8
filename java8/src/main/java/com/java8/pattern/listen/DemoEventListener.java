package com.java8.pattern.listen;

import java.util.EventListener;

/**
 * @author chenyihui
 * @version 1.0
 * @Description 事件监听接口
 * @date 2018/3/13
 */
public interface DemoEventListener  extends EventListener{

    public void processEvent(DemoEvent demoEvent);
}
