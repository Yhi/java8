* 目的
    为了尽可能的节约内存空间，减少无谓的GC消耗，并且使应用可以正常运作。<br>
   
* 适用场景 
    这些类，在应用中如果有两个或者两个以上的实例会引起错误，又或者我换句话说，就是这些类，在整个应用中同一时刻，有且只有一种状态。<br>
    
* 案例
    * 原始单例：<br>
        * 1.静态实例，带有static关键词的属性在每一个类中都是唯一的。
        * 2.限制客户端随意创建实例，即私有化构造方法，为此保证单例的重要一步
        * 3.给一个公共的获取实例静态方法， 注意，是静态方法，因为这个方法是在我们未获取到实例的时候就要提供给客户端调用的
        所以如果是非静态的话，那就变成一个矛盾体，因为非静态的方法必须要拥有实例才可以调用。
        * 4.判断只有持有的静态实例null时才调用构造方法构造一个实例，否则就直接返回。
    * 说明在代码的注解上：双重加锁，同步锁，静态加载
   
     