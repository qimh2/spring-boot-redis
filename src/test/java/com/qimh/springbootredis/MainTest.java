package com.qimh.springbootredis;

public class MainTest {

    public static void main(String[] args) {
        A.methodA();
        System.out.println("main..");

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
}
