package com.java8.pattern.state;


/**
 * @author chenyihui
 * @version 1.0
 * @Description:
 * @date 2018/3/13
 */
public class ConcreteStateB implements State {


    @Override
    public void handle(String sampleParameter) {
        System.out.println("ConcreteStateB handle :" + sampleParameter);
    }
}
