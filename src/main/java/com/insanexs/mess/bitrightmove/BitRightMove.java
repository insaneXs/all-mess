package com.insanexs.mess.bitrightmove;

/**
 * @Author: xieshang
 * @Description:
 * @Date: Create at 2019-12-14
 */
public class BitRightMove {

    public static void main(String[] args){
//        positiveNumBitRightMove();
        System.out.println(tableSizeFor(16));
        System.out.println(tableSizeFor(31));

        System.out.println(8% 7);
        System.out.println( -9 % 10);
    }

    protected static void positiveNumBitRightMove(){
        int i = 16;

        System.out.println(i |= i >>> 1);
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n;
    }
}
