import com.java8.thread.CyclicBarrierTest;

public class Main {

    public static void main(String[] args){
        test();
    }

    public static  void  test(){
        test();
        System.out.println("a");

        CyclicBarrierTest cyclicBarrierTest=new CyclicBarrierTest();
    }
}
