package com.java8.pattern.state;


/**
 * @author chenyihui
 * @version 1.0
 * @Description: 实现了Context的状态所有行为
 * @date 2018/3/13
 */
public class ConcreteStateA implements State {


    @Override
    public void handle(String sampleParameter) {
        System.out.println("ConcretStateA handle :" + sampleParameter);
    }
}
