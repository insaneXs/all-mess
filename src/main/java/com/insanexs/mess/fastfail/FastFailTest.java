package com.insanexs.mess.fastfail;

import java.util.*;

/**
 * @Author: xieshang
 * @Description:
 * @Date: Create at 2020-07-27
 */
public class FastFailTest {


    public static void main(String[] args){
//        testListFastFail();
        testMapFastFail();
    }


    //Throw Concurrent Modification Exception
    protected static void testListFastFail(){

        List<String> list = new ArrayList<>();
        list.add("a");
//        list.add("b");
//        list.add("c");

        for(String s : list){
            list.remove(s);
        }
    }

    protected static void testMapFastFail(){
        Map<String, String> map = new HashMap<String, String>();

        map.put("k1", "k1");
        map.put("k2", "k2");
        map.put("k3", "k3");

        for(String str: map.values()){
            map.remove(str);
        }
    }

    protected static void correctRemoveWay(){
        Map<String, String> map = new HashMap<String, String>();

        map.put("k1", "k1");
        map.put("k2", "k2");
        map.put("k3", "k3");

        for(Iterator<String> iterator = map.values().iterator(); iterator.hasNext(); ){
            String str = iterator.next();

            iterator.remove();
            System.out.println(map.containsKey(str));
        }

        System.out.println(map.size());
    }
}
