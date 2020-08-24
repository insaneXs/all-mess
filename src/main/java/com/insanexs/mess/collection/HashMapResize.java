package com.insanexs.mess.collection;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @ClassName HashMapResize
 * @Description TODO
 * @Author insaneXs
 * @Date 2020/8/23
 */
public class HashMapResize {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        testNonInitCapHashMap();
        System.out.println("----------------------");
        testInitCapHashMap();
    }


    //未指定intiCap时 没初始话数组前的threshold = 0
    protected static void testNonInitCapHashMap() throws NoSuchFieldException, IllegalAccessException {
        HashMap<String,String> map = new HashMap<>();

        printMapThreshold(map);
        printMapTableSize(map);

        map.put("1", "1");
        printMapThreshold(map);
        printMapTableSize(map);
    }

    //指定intiCap时 没初始话数组前的threshold = cap；目的是用来保存初始时的cap?
    protected static void testInitCapHashMap() throws NoSuchFieldException, IllegalAccessException {
        HashMap<String,String> map = new HashMap<>(10);

        printMapThreshold(map);
        printMapTableSize(map);

        map.put("1", "1");
        printMapThreshold(map);
        printMapTableSize(map);


    }


    protected static void printMapThreshold(HashMap map) throws NoSuchFieldException, IllegalAccessException {
        Field field = HashMap.class.getDeclaredField("threshold");
        field.setAccessible(true);
        System.out.println(field.getLong(map));
    }

    protected static void printMapTableSize(HashMap map) throws NoSuchFieldException, IllegalAccessException {
        Field field = HashMap.class.getDeclaredField("table");
        field.setAccessible(true);

        Object[] table = (Object[])field.get(map);
        System.out.println(table == null ? null : table.length);
    }


}
