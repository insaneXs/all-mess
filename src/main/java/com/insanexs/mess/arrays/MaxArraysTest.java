package com.insanexs.mess.arrays;

/**
 * @Author: xieshang
 * @Description:
 * @Date: Create at 2019-12-23
 */
public class MaxArraysTest {

    public static void main(String[] args){
        //Scene 1: beyonds VM limits
        try {
            byte[] arrBeyondsVMLimits = new byte[Integer.MAX_VALUE];
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Scene 2: does not beyonds VM limits,but out of memory which means if vm can allocate enough memory space, it works
        try {
            byte[] arrUnderVMLimits = new byte[Integer.MAX_VALUE - 8];
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
