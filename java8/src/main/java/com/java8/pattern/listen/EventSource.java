package com.java8.pattern.listen;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenyihui
 * @version 1.0
 * @Description 定义事件源
 * @date 2018/3/13
 */
public class EventSource {

    private List<DemoEventListener> listeners = new ArrayList<DemoEventListener>();

    public EventSource() {

    }

    public void addDemoLister(DemoEventListener demoEventListener) {
        listeners.add(demoEventListener);
    }


    public void notifyDemoEvent() {
        for (DemoEventListener eventListener : listeners) {
            DemoEvent demoEvent = new DemoEvent(this);
            eventListener.processEvent(demoEvent);
            eventListener.processEvent(demoEvent);
        }
    }

}
