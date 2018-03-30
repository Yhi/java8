import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){
        test();
    }

    public static  void  test(){
       /* test();
        System.out.println("a");

        CyclicBarrierTest cyclicBarrierTest=new CyclicBarrierTest();*/



            List<String> typeList=new ArrayList<String>();
            typeList.add("网贷黑名单"); typeList.add("其他");
            typeList.add("M3-M6");typeList.add("M6+");
            String[] storeStr = typeList.toArray(new String[typeList.size()]);

            System.out.println(storeStr.toString());
            for (String s : storeStr) {
                 System.out.println(s);

            }

    }
}
