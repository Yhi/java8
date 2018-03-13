package com.java8.pattern.listen;

public class SecondEventListener implements DemoEventListener {

    @Override
    public void processEvent(DemoEvent demoEvent) {

        System.out.println("second event listener process");
    }
}
