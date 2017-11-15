package com.java8.List;

import javax.xml.bind.SchemaOutputResolver;
import java.util.*;

/**
 * Created by cyh on 2016-05-15.
 *
 * List<Map<String,Object>> 排序
 */
public class ListInMapOrderBy {
    public static List<Map<String,Object>> listAdd(){
        List list = new ArrayList<>();
        Map map1 = new HashMap();
        Map map2 = new HashMap();
        Map map3 = new HashMap();
        Map map4 = new HashMap();
        Map map5 = new HashMap();

        map1.put("NAME", 1);
        map2.put("NAME", 2);
        map3.put("NAME", 5);
        map4.put("NAME", 8);
        map5.put("NAME", 3);
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        return  list;
    }

    public static void listInMapOrderByJava8(List<Map<String,Object>> list){
        list.forEach(i-> System.out.println("排序前"+i));
      //  System.out.println();
      //  Collections.sort(i->i);

       //list.stream().(Collections.sort(list.forEach(i->i.get("NAME"))));
    }

    public void listInMapOrderByJava7(){}

    public static void main(String[] args) {
        listInMapOrderByJava8(listAdd());
    }

}
