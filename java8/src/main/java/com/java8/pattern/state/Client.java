package com.java8.pattern.state;


/**
 * @author chenyihui
 * @version 1.0
 * @Description 客户端
 * @date 2018/3/13
 */
public class Client {


    public static void main(String[] args) {

        //创建状态
        State state=new ConcreteStateA();

        State state1=new ConcreteStateB();

        //创建上下文
        Context context=new Context();

        context.setState(state1);
        context.setState(state);

        context.request("test");

        context.request("demo");

    }
}
