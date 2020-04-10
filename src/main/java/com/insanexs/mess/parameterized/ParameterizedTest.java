package com.insanexs.mess.parameterized;

/**
 * @ClassName ParameterizedTest
 * @Description TODO
 * @Author xieshang
 * @Date 2020/4/8 1:58 下午
 */
public class ParameterizedTest {

    public static void main(String[] args){
        testParameterizedClass();
        testParameterizedMethod();
    }

    protected static void testParameterizedClass(){
        System.out.println("TEST PARAMETERIZED CLASS");

        ParameterizedClass<String> a = new ParameterizedClass<>("Hello");
        a.function();

        ParameterizedClass<Integer> b = new ParameterizedClass<>(1);
        b.function();

        //NOT SPECIFIED PARAMETER WHEN CREATE
        ParameterizedClass notSpecifiedParameterWhenCreate = new ParameterizedClass();
        notSpecifiedParameterWhenCreate.setValue("Hello");
        notSpecifiedParameterWhenCreate.setValue(1);

        notSpecifiedParameterWhenCreate.function();
    }

    protected static void testParameterizedMethod(){
        System.out.println("TEST PARAMETERIZED METHOD");

        ClassWithParameterizedMethod a = new ClassWithParameterizedMethod();
        a.function("Hello");

        a.function(1);
    }

    static class ClassWithParameterizedMethod{

        public <T> void function(T t){
            System.out.println("clazz: " + t.getClass() + " => " + t);
        }
    }

    static class ParameterizedClass<T>{
        T value;

        public ParameterizedClass(){
        }

        public ParameterizedClass(T value){
            this.value = value;
        }

        public void setValue(T value){
            this.value = value;
        }
        public void function(){
            System.out.println("clazz: " + value.getClass() + " => " + value);
        }
    }
}
