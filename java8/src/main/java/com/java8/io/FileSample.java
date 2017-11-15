package com.java8.io;

import java.io.File;
import java.util.stream.Stream;

/**
 * Created by cyh on 2016-04-17.
 */
public class FileSample {
    public  static  void FileJava7(){
        File dir=new File("D:/venkats/tmp/dir");
        //names in uppercase,comma separated
        File[] children=dir.listFiles();
        if(children!=null){
            for (int i=0;i<children.length;i++){
                System.out.print(children[i].getName().toUpperCase());
                if(i!=children.length-1){
                    System.out.print(children[i].getName().toUpperCase()+",");
                }
            }
            System.out.println("");
        }
    }

    public static void FileJava8() {
        File dir = new File("D:/venkats/tmp/dir");
        //names in uppercase,comma separated
        File[] children = dir.listFiles();
        if (children != null) {
        /*String a=    Stream.of(children)
                    .map(File::getName)
                    .map(String::toUpperCase)
                    .collect(joining(","));*/

        }

        }
}
