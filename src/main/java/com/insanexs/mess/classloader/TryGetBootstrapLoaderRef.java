package com.insanexs.mess.classloader;

/**
 * @Author: xieshang
 * @Description:
 * @Date: Create at 2020-07-20
 */
public class TryGetBootstrapLoaderRef {

    public static void main(String[] args){
        //不能获取Bootstrap ClassLoader的引用 打印null
        System.out.println(Object.class.getClassLoader());
        //打印AppClassLoader
        System.out.println(TryGetBootstrapLoaderRef.class.getClassLoader());
    }
}
