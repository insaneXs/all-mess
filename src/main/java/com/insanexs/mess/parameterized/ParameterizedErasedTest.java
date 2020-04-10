package com.insanexs.mess.parameterized;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ParameterizedEraesdTest
 * @Description TODO
 * @Author xieshang
 * @Date 2020/4/8 2:43 下午
 */
public class ParameterizedErasedTest {

    public static void main(String[] args){
        //TEST 1 确认擦出后 两个类型相等
        //
        List<String> stringList = new ArrayList<>();

        List<Integer> integerList = new ArrayList<>();

        System.out.println("After Parameterized Erased, They are same class:" + stringList.getClass().equals(integerList.getClass()));

        //TEST 2 测试擦出后在赋值给没有泛型的引用 编译器将无法在保证类型安全
        List<String> withParameterizedClass = new ArrayList<>();

        //擦出后 编译器已经无法保证类型安全
        List erasedParamterized = withParameterizedClass;

        erasedParamterized.add(new Integer(1));

        //编译器不通过
//        List<Integer> anotherParameterizedTypeClass = withParameterizedClass;

    }
}
