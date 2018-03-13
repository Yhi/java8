package com.java8.pattern.state;


/**
 * @author chenyihui
 * @version 1.0
 * @Description: 环境角色 上下文 定义客户端所感兴趣的接口，并且保留一个具体状态类的实例。这个状态类的实例对应的是环境对象的现有状态
 * @date 2018/3/13
 */
public class Context {

    /**
     * 持有state对象的实例
     */
    private State state;

    public void setState(State state) {
        this.state = state;
    }

    public void request(String sampleParemeter) {
        state.handle(sampleParemeter);
    }

}
