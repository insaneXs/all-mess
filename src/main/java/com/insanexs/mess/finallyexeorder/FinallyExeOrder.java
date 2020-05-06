package com.insanexs.mess.finallyexeorder;

/**
 * @ClassName FinallyExeOrder
 * @Description TODO
 * @Author xieshang
 * @Date 2020/2/10 10:19 AM
 */
public class FinallyExeOrder {

    public static void main(String[] args){
        try{
//            return System.out.println("Return clause");
        }finally {
            System.out.println("finally clause executes");
        }
        System.out.println("after finally clause executes");
    }
}
