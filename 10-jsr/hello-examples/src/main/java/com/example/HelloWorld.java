package com.example;

@PrintElementInfo
public class HelloWorld {
    public static final String HELLO = "Hello World!";

    public static void main(String[] args) {
        System.out.println(HELLO);
    }

    public <T> T test(T bean) {
        //具体方法
        return bean;
    }
}
