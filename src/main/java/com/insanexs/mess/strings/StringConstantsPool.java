package com.insanexs.mess.strings;

/**
 * @ClassName StringConstantsPool
 * @Description TODO
 * @Author insaneXs
 * @Date 2020/8/21
 */
public class StringConstantsPool {

    public static void main(String[] args){
        String s1 = "hello";
        String s2 = "hello";
        System.out.println(s1 == s2); //true 以字面量(在class文件中就直接以特定字节保存)的形势创建的字符串 会先从字符串常量池中中查找
        String s3 = new String("hello");
        System.out.println(s3 == s2);//false s3是通过构造函数创建的

        String s4 = s3.intern();
        System.out.println(s4 == s2);//true

    }

}
