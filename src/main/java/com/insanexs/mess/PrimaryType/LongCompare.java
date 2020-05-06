package com.insanexs.mess.PrimaryType;

import javax.xml.bind.DatatypeConverter;

/**
 * @Author: xieshang
 * @Description:
 * @Date: Create at 2019-12-24
 */
public class LongCompare {

    private static int[] nullArrays;

    public static void main(String[] args){

        Long lObj = 1L;

        System.out.println(lObj == 1);
//        Long nullLongVal = null;
//        Long.compare(nullLongVal, 1);
//        spread(1);
//
//        spread(1 << 15 + 1);
//
//        spread(1 << 16 + 1);
//
//        int n = 16;
//        System.out.println((n - (n >>> 2)));
//
//        test();
//
//        System.out.println(resizeStamp(16));
//
//        System.out.println(resizeStamp(12));
//
//        Long l1 = new Long(1);
//
//        Long l2 = new Long(1);
//
//        System.out.println(l1 == l2);
//        int i = -1;
//        i = -1<< (Integer.SIZE - 3);
//        byte[] result = new byte[4];
//        // 由高位到低位
//        result[0] = (byte) ((i >> 24) & 0xFF);
//        result[1] = (byte) ((i >> 16) & 0xFF);
//        result[2] = (byte) ((i >> 8) & 0xFF);
//        result[3] = (byte) (i & 0xFF);
//        System.out.println(DatatypeConverter.printHexBinary(result));


//        System.out.print(DatatypeConverter.printHexBinary(result));


    }

    private static void spread(int h){
        System.out.println("Before Spread:" + h);
        h = h ^ (h >>> 16) & 0x7fffffff;
        System.out.println("After Spread:" + h);
    }

    public static void test(){
        for(int i : nullArrays){
            System.out.println(i);
        }
    }

    private static int resizeStamp(int n){
        return Integer.numberOfLeadingZeros(n) | (1 << (16 - 1));
    }
}
