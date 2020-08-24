package com.insanexs.mess.collection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName TestConHashMapResize
 * @Description TODO
 * @Author insaneXs
 * @Date 2020/8/23
 */
public class TestConHashMapSeq {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {


        Map<SameHashClass, String> map = new ConcurrentHashMap<>();

        for(int i=0; i<11; i++){
            map.put(new SameHashClass((char)('A' + i)), i+"");
            checkMapTab(map);
        }

        //debug and check tab[0]
        map.put(new SameHashClass((char)('A' + 12)), "12");
        checkMapTab(map);
    }




    static class SameHashClass{
        private char c;

        public SameHashClass(char c){
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SameHashClass that = (SameHashClass) o;
            return c == that.c;
        }

        @Override
        public int hashCode() {
            return c % 2 + c / 2 * 16;
        }

        @Override
        public String toString() {
            return "[" + c +
                    ']';
        }
    }

    protected static void checkMapTab(Map map) throws NoSuchFieldException, IllegalAccessException {
        System.out.println("");
        Field field = map.getClass().getDeclaredField("table");
        field.setAccessible(true);

        Field sizeCtl = map.getClass().getDeclaredField("sizeCtl");
        sizeCtl.setAccessible(true);
        Object[] table = (Object[])field.get(map);
        System.out.println("table size:" + table.length);
        Object val  = sizeCtl.get(map);
        System.out.println("sizeCtl:" + val);
        for(int i=0; i<table.length; i++){
            if(table[i] == null)
                continue;
            System.out.print("tab[" + i + "]:");
            Object o = table[i];
            while(o != null){
                Field field1 = table[i].getClass().getDeclaredField("key");
                field1.setAccessible(true);
                System.out.print(field1.get(o));

                Field filed2 =  table[i].getClass().getDeclaredField("next");
                filed2.setAccessible(true);
                o = filed2.get(o);
            }

        }
        System.out.println("");
    }
}
