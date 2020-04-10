package com.insanexs.mess.parameterized;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName WildParameterizedTest
 * @Description TODO
 * @Author xieshang
 * @Date 2020/4/8 3:02 下午
 */
public class WildParameterizedTest {

    public static void main(String[] args){
        List<Integer> integerList = new ArrayList<>();
        List<Double> doubleList = new ArrayList<>();
        List<Number> numberList = new ArrayList<>();

        List notSpecifiedParameterizedList = new ArrayList();

        //编译时出错，编译器认为List<Integer> 和 List<Number>是两个不同的类
        //acceptParameterizedClass(integerList);

        //编译不报错,不指定泛型绕过了编译时检查，但是存在运行时出错的风险
        notSpecifiedParameterizedList.add(new Double(0));
        notSpecifiedParameterizedList.add(new Integer(1));
        acceptParameterizedClass(notSpecifiedParameterizedList);

        //正常使用
        numberList.add(new Integer(1));
        acceptParameterizedClass(numberList);

        acceptWildParameterizedClass(integerList);

        acceptWildParameterizedClass(doubleList);

        acceptWildParameterizedClass(notSpecifiedParameterizedList);
    }

    protected static void acceptParameterizedClass(List<Number> list){
        System.out.println("");
    }

    protected static void acceptWildParameterizedClass(List<?> list){
        System.out.println("");
    }

    protected static void putAndSetWildParameterizedClass(){
        List<?> list = new ArrayList<>();
        //编译器错误
//        list.add("String");

        list.get(0);

        ParameterizedTest.ParameterizedClass<?> obj = new ParameterizedTest.ParameterizedClass<>();

        //obj.setValue("Complied Error");

        obj.function();
    }

}
