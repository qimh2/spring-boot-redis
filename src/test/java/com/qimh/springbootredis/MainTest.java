package com.qimh.springbootredis;

import com.alibaba.fastjson.JSONArray;
import com.qimh.springbootredis.domain.Emp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainTest {

    public static void main(String[] args) {

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(new Emp(1,"张三","男"));
        jsonArray.add(new Emp(2,"李四","男"));
        jsonArray.add(new Emp(3,"王五","男"));

        JSONArray tmp = new JSONArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            if (i == 0 || i == 1){
                tmp.add(jsonArray.get(i));
            }

        }
        jsonArray.removeAll(tmp);

        System.out.println("jsonArray" + jsonArray);

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
