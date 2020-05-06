package com.insanexs.mess.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xieshang
 * @Description:
 * @Date: Create at 2019-12-23
 */
public class ModifySubList {

    public static void main(String[] args){

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        List<Integer> subList = list.subList(1,2);
        subList.add(4);

        System.out.println(list.size());
    }
}
