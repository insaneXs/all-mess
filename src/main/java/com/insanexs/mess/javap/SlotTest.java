package com.insanexs.mess.javap;

/**
 * @Author: xieshang
 * @Description:
 * @Date: Create at 2020-07-14
 */
public class SlotTest {

    private Object reference;

    /**
     * 测试个类型数据在64位虚拟机下占几个slot
     */
    public void testSlot(){
        int i =0;
        long l = 1L;
        Object reference = new Object();

        int j = 1;
        System.out.println(i + ","+ l + "," + reference + "," + j);
    }
}
