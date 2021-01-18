package com.qimh.springbootredis;

import com.qimh.springbootredis.common.ChanelEnum;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainTest {

    public static void main(String[] args) {

//        System.out.println(ChanelEnum.valueOf("baidu"));
//
//        A.methodA();
//        System.out.println("main..");

//        t1();
        String str = "{10}-{20}";
        str = str.replaceAll("\\{","").replaceAll("\\}","");
        String[] strings = str.split("-");
        String newStr = Arrays.toString(strings);

        System.out.println(newStr);



    }






        static class A {

        public static void methodA(){
            System.out.println("i am methodA");
            B.methodB();
        }
    }


    static class B {

        public static void methodB(){
            System.out.println("i am methodB");

        }
    }


    public static void t1() {
        List<String> list = Collections.EMPTY_LIST;
        list = Arrays.asList("awg", "weg", "wweg", "wegwe");
        list.forEach(System.out::println);
        list.forEach(str -> {
            if ("awg".equals(str)) {
                System.out.println("i am return");
                return;
            }
            System.out.println(str);
        });
    }

}
